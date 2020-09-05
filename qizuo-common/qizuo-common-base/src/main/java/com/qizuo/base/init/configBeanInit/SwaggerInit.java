/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.base.init.configBeanInit;

import com.qizuo.config.properties.QizuoConfigPropertiesGY;
import com.qizuo.config.properties.otherProperties.SwaggerPropertiesGY;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger初始化. @Api：用在类上，说明该类的作用 @ApiOperation：用在方法上，说明方法的作用，标注在具体请求上，value和notes的作用差不多，都是对请求进行说明；tags则是对请求进行分类的，比如你有好几个controller，分别属于不同的功能模块，那这里我们就可以使用tags来区分了，看上去很有条理 @ApiImplicitParams：用在方法上包含一组参数说明 @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *
 * <p>　　paramType：参数放在哪个地方
 *
 * <p>　　header 请求参数的获取：@RequestHeader
 *
 * <p>　　query 请求参数的获取：@RequestParam
 *
 * <p>　　path（用于restful接口） 请求参数的获取：@PathVariable
 *
 * <p>　　body（不常用）
 *
 * <p>　　form（不常用）
 *
 * <p>　　name：参数名
 *
 * <p>　　dataType：参数类型
 *
 * <p>　　required：参数是否必须传
 *
 * <p>　　value：参数的意思
 *
 * <p>　　defaultValue：参数的默认值 @ApiResponses：用于表示一组响应 @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *
 * <p>　　code：数字，例如400
 *
 * <p>　　message：信息，例如"请求参数没填好"
 *
 * <p>　　response：抛出异常的类 @ApiModel：两种场景，描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）表明这是一个被swagger框架管理的model，用于class上 @ApiModelProperty
 * 这里顾名思义，描述一个model的属性，就是标注在被标注了@ApiModel的class的属性上，这里的value是对字段的描述，example是取值例子，注意这里的example很有用，对于前后端开发工程师理解文档起到了关键的作用，因为会在api文档页面上显示出这些取值来；这个注解还有一些字段取值，可以自己研究，举例说一个：position，表明字段在model中的顺序
 */

/**
 * @ResponseBody @RequestMapping("/updatePassword") @ApiOperation(value="修改用户密码",
 * notes="根据用户id修改密码") @ApiImplicitParams({ @ApiImplicitParam(paramType="query", name = "userId",
 * value = "用户ID", required = true, dataType = "Integer"), @ApiImplicitParam(paramType="query", name
 * = "password", value = "旧密码", required = true, dataType =
 * "String"), @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required =
 * true, dataType = "String") }) @ApiResponses({ @ApiResponse(code = 400, message = "业务逻辑异常",
 * response = BackResult.class), @ApiResponse(code = 407, message = "XX异常", response =
 * BackResult.class), @ApiResponse(code = 500, message = "服务器内部错误", response = BackResult.class) })
 * 其它具体的response定义需要在实体类中去用apimodel去定义或者request中有相关实体类的也需要这样子去定义
 */
@Configuration
// 开启Swagger2Configuration 就算没有这个也会默认开启的
@EnableSwagger2
public class SwaggerInit {
  @Resource private QizuoConfigPropertiesGY qizuoConfigProperties;

  /**
   * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，当前配置是只有ApiOperation才会显示
   *
   * @return the docket
   */
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build()
        // 配置鉴权信息(就是页面上可以输入token,try测试的需要这个,就是去掉这个后不会有header)
        .securitySchemes(securitySchemes())
        .securityContexts(securityContexts())
        .enable(true);
  }

  /**
   * 创建该API的基本信息（这些基本信息会展现在文档页面中） 访问地址：http://项目实际地址/swagger-ui.html
   *
   * @return
   */
  private ApiInfo apiInfo() {
    SwaggerPropertiesGY swagger = qizuoConfigProperties.getSwagger();
    return new ApiInfoBuilder()
        .title(swagger.getTitle())
        .description(swagger.getDescription())
        .version(swagger.getVersion())
        .license(swagger.getLicense())
        .licenseUrl(swagger.getLicenseUrl())
        .contact(
            new Contact(
                swagger.getContactName(), swagger.getContactUrl(), swagger.getContactEmail()))
        .build();
  }

  // 通过Swagger2的securitySchemes配置全局参数
  private List<ApiKey> securitySchemes() {
    return new ArrayList(
        Collections.singleton(new ApiKey("Authorization", "Authorization", "header")));
  }

  // 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，type为“header”的参数。
  private List<SecurityContext> securityContexts() {
    return new ArrayList(
        Collections.singleton(
            SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build()));
  }

  // 配置鉴权信息
  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return new ArrayList(
        Collections.singleton(new SecurityReference("Authorization", authorizationScopes)));
  }
}
