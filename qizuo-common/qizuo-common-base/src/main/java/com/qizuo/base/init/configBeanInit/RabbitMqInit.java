/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.init.configBeanInit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * RabbitMq初始化.
 *
 Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 Queue:消息的载体,每个消息都会被投到一个或多个队列。
 Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 Producer:消息生产者,就是投递消息的程序.
 Consumer:消息消费者,就是接受消息的程序.
 Channel:消息通道,在客户端的每个连接里,可建立多个channel.channel一些常用api https://www.cnblogs.com/piaolingzxh/p/5448927.html
 */
/**交换机种类
 Direct Exchange

 直连型交换机，根据消息携带的路由键将消息投递给对应队列。

 大致流程，有一个队列绑定到一个直连交换机上，同时赋予一个路由键 routing key 。
 然后当一个消息携带着路由值为X，这个消息通过生产者发送给交换机时，交换机就会根据这个路由值X去寻找绑定值也是X的队列。

 Fanout Exchange

 扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。

 Topic Exchange

 主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
 简单地介绍下规则：

 *  (星号) 用来表示一个单词 (必须出现的)
 #  (井号) 用来表示任意数量（零个或多个）单词
 通配的绑定键是跟队列进行绑定的，举个小例子
 队列Q1 绑定键为 *.TT.*          队列Q2绑定键为  TT.#
 如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
 如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；
 */

/**
 * Qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的，添加@Qualifier注解，需要注意的是@Qualifier的参数名称为我们之前定义@Service注解的名称之一。
 * @autowired的时候如果有多个实现者的情况下可以用这个，类似于@resource的name
 */
@Configuration
@Slf4j
public class RabbitMqInit {
	@Autowired
	private CachingConnectionFactory connectionFactory;

	/**
	 * @bean的name可以当多个同样实现类的时候，用来区分不同的类型
	 */
	@Bean
	public Queue directQueue() {
		return new Queue("directQueue");
	}

	@Bean
	public Queue topicQueue() {
		return new Queue("topicQueue");
	}

	@Bean
	public Queue fanoutQueue() {
		return new Queue("fanoutQueue");
	}

	@Bean
	DirectExchange directExchange(){
		return new DirectExchange("directExchange");
	}
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	Binding bindingExchangeDirect(Queue directQueue, DirectExchange directExchange){
		//使用with绑定routing_key
		return  BindingBuilder.bind(directQueue).to(directExchange).with("direct");
	}

	@Bean
	Binding bindingExchangeMessage(Queue topicQueue, TopicExchange exchange) {
		//使用with绑定routing_key
		return BindingBuilder.bind(topicQueue).to(exchange).with("topic");
	}

	@Bean
	Binding bindingExchangeC(Queue fanoutQueue, FanoutExchange fanoutExchange) {
		//使用with绑定routing_key
		return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
	}

	/**
	 * 若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
	 * 每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
	 * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(){
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        /**
         * 如果消息没有到exchange,则confirm回调,ack=false
         * 如果消息到达exchange,则confirm回调,ack=true
         */
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if(ack){
					log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
				}else{
					log.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
				}
			}
		});

		/**
		 * exchange到queue成功,则不回调return
		 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
		 */
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
			}
		});
		return rabbitTemplate;
	}

	//生产者
//	public void sendMessage(Employee employee){
//		//CorrelationData作用是作为消息的附加消息传递，通常我们用它来保存消息的自定义id，用来表示当前消息唯一性。
//		//这个在ConfirmCallback的时候会用到，用到确认问题
//		CorrelationData correlationData = new CorrelationData(employee.getEmpno()+"-"+new Date().getTime());
//
//		//如果没有创建交换机的话，在投递消息的时候会投递失败
//		//第一个参数，交换机的名称
//		//第二个参数，路由key值
//		//第三个参数，消息主题对应的类
//		//第四个参数，消息的附件消息
//		rabbitTemplate.convertAndSend("directExchange", "direct", employee, correlationData);
//	}

	//消费者
	//指定目标方法来作为消费消息的方法
	//可以直接用queue简单使用
//	@RabbitListener(
//			bindings = @QueueBinding(
//					//队列的信息，当队列不存在的时候，会自动创建一个队列，并绑定下面的交换机
//					//如过将queue的持久化标识durable设置为true,则代表是一个持久的队列，那么在服务重启之后，也会存在
//					value = @Queue(value = "directQueue", durable = "true"),
//					//交换机的信息
//					exchange = @Exchange(value = "directExchange", durable = "true", type = "direct"),
//					//路由规则
//					key = "#"
//			)
//	)
//	/**
//	 * RabbitHandler注解，通知SpringBoot该方法用于接收消息,这个方法运行后将处于等待状态，
//	 * 有新的消息进来就会自动触发该方法处理消息
//	 * 使用 @Payload 和 @Headers 注解可以消息中的 body 与 headers 信息
//	 */
//	@RabbitHandler
//	public void handleMessage(Employee employee, Channel channel,
//							  Map<String, Object> headers){
//		long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
//		try {
//			//第一个参数，tag
//			//第二个参数，是否批量接收
//			channel.basicAck(tag, false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
