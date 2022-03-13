/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

/**
 * @author: fangl
 * @description: http工具
 * @date: 8:57 2019/1/14
 */
@Slf4j
public class HttpBaseUtil {
  /**
   * *****************************************HttpServletRequest操作*****************************************
   */
  /**
   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
   *
   * <p>可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
   *
   * <p>如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
   *
   * <p>用户真实IP为： 192.168.1.110
   */
  public static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
  /*public static String getIpAddress(HttpServletRequest request){
      String Xip = request.getHeader("X-Real-IP");
      String XFor = request.getHeader("X-Forwarded-For");
      if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
          //多次反向代理后会有多个ip值，第一个ip才是真实ip
          int index = XFor.indexOf(",");
          if(index != -1){
              return XFor.substring(0,index);
          }else{
              return XFor;
          }
      }
      XFor = Xip;
      if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
          return XFor;
      }
      if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
          XFor = request.getHeader("Proxy-Client-IP");
      }
      if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
          XFor = request.getHeader("WL-Proxy-Client-IP");
      }
      if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
          XFor = request.getHeader("HTTP_CLIENT_IP");
      }
      if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
          XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
          XFor = request.getRemoteAddr();
      }
      return XFor;
  }*/

  /**
   * @author: fangl
   * @description: 举例
   * @date: 19:48 2019/2/13
   */
  public void requestForExample(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 返回请求行中的资源名称
    String uri = request.getRequestURI();
    // 获得客户端发送请求的完整url
    String url = request.getRequestURL().toString();
    // 返回发出请求的IP地址
    String ip = request.getRemoteAddr();
    // 返回请求行中的参数部分
    String params = request.getQueryString();
    // 返回发出请求的客户机的主机名
    String host = request.getRemoteHost();
    // 返回发出请求的客户机的端口号
    int port = request.getRemotePort();
  }

  /** 获取所有请求头 */
  public static Map<String, String> getHeaders(HttpServletRequest request) {
    Map<String, String> map = new HashMap<>(16);
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }
    return map;
  }

  public static String getHeadersChar(HttpServletRequest request) {
    String headers = "";
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      headers += key + "=" + value + "\r\n";
    }
    return headers;
  }

  /** 获取method （大写） */
  public static String getRequestMethod(HttpServletRequest request) {
    String sMethod = request.getMethod();
    return sMethod.toUpperCase();
  }

  /** 获取参数(相当于请求体) */
  public static Map<String, String> getParams(HttpServletRequest request) {
    TreeMap<String, String> values = new TreeMap<String, String>();
    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String name = (String) paramNames.nextElement();
      String value = request.getParameter(name);
      values.put(name, value);
    }
    return values;
  }

  public static String getParamsChar(HttpServletRequest request) {
    String params = "";
    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String name = (String) paramNames.nextElement();
      String value = request.getParameter(name);
      params += "&" + name + "=" + value;
    }
    return params;
  }

  /** 请求体二进制读取 */
  public static InputStream getRequestBody(HttpServletRequest request) {
    InputStream requestEntity = null;
    try {
      requestEntity = request.getInputStream();
    } catch (IOException ex) {
    }
    return requestEntity;
  }

  /** 请求体字符串读取 */
  public static String getRequestBodyChar(HttpServletRequest request) {
    try {
      BufferedReader br = request.getReader();
      String str, wholeStr = "";
      while ((str = br.readLine()) != null) {
        wholeStr += str;
      }
      return wholeStr;
    } catch (Exception e) {

    }
    return "";
  }

  /**
   * *****************************************httpclient请求发送*****************************************
   */
  /**
   * 1.主要操作对象有四个MinimalHttpClient/InternalHttpClient/HttpClientBuilder/HttpClients
   * 2.MinimalHttpClient 和 InternalHttpClient。其中 MinimalHttpClient 是一个极简版的实现，是核心设计的最直接简单的提现。而
   * InternalHttpClient 则是一个完全可配置版本，提供最丰富完善的功能。 3.HttpClients
   * 是一个工厂类，专门生产各种HttpClient和Builder。而HttpClientBuilder用于生成 InternalHttpClient，让配置更加规范易用。
   */
  /**
   * @author: fangl
   * @description: 远程访问http
   * @date: 14:35 2018/12/25
   */
  public String sendHttp(String action, String url, Map<String, String> map) {
    String respContent = "";
    try {
      // 返回对象
      HttpEntity entity = null;

      // 发送器
      HttpClient client = HttpClients.createDefault();

      if ("get".equals(action)) {
        // 发送对象+发送对象数据
        HttpGet request = new HttpGet(url + map.get("get"));
        request.setHeader("Accept", "application/json");
        // 发送
        HttpResponse response = client.execute(request);
        // 返回
        entity = response.getEntity();
      } else if ("postForm".equals(action)) {
        // 发送对象
        HttpPost request = new HttpPost(url);
        // 发送对象数据
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> me : map.entrySet()) {
          nvps.add(new BasicNameValuePair(me.getKey(), me.getValue()));
        }
        // 发送
        HttpResponse response2 = client.execute(request);
        // 返回
        entity = response2.getEntity();
      } else if ("postRaw".equals(action)) {
        // 发送对象
        HttpPost request = new HttpPost(url);
        // 发送对象数据
        //                JSONObject jsonObject = new JSONObject();
        //                for (Map.Entry<String, String> me : map.entrySet()) {
        //                    jsonObject.put(me.getKey(), me.getValue());
        //                }
        //                request.setEntity(new StringEntity(jsonObject.toString(), "utf-8"));
        // 发送
        HttpResponse response2 = client.execute(request);
        // 返回
        entity = response2.getEntity();
      }

      // 解析
      respContent = EntityUtils.toString(entity, "UTF-8");

    } catch (IOException e) {
      log.error("异常={}", e.getMessage(), e);
    }
    return respContent;
  }

  /** ************** 简单封装 ***************** */

  /** 编码格式。发送编码格式统一用UTF-8 */
  private static final String ENCODING = "UTF-8";

  /** 设置连接超时时间，单位毫秒 */
  private static final int CONNECT_TIMEOUT = 6000;

  /** 请求获取数据的超时时间(即响应时间)，单位毫秒 */
  private static final int SOCKET_TIMEOUT = 6000;

  /**
   * 发送get请求；不带请求头和请求参数
   *
   * @param url 请求地址
   * @return
   * @throws Exception
   */
  public static String doGet(String url) throws Exception {
    return doGet(url, null, null);
  }

  /**
   * 发送get请求；带请求参数
   *
   * @param url 请求地址
   * @param params 请求参数集合
   * @return
   * @throws Exception
   */
  public static String doGet(String url, Map<String, String> params) throws Exception {
    return doGet(url, null, params);
  }

  /**
   * 发送get请求；带请求头和请求参数
   *
   * @param url 请求地址
   * @param headers 请求头集合
   * @param params 请求参数集合
   * @return
   * @throws Exception
   */
  public static String doGet(String url, Map<String, String> headers, Map<String, String> params)
      throws Exception {
    // 创建httpClient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();

    // 创建访问的地址
    URIBuilder uriBuilder = new URIBuilder(url);
    if (params != null) {
      Set<Map.Entry<String, String>> entrySet = params.entrySet();
      for (Map.Entry<String, String> entry : entrySet) {
        uriBuilder.setParameter(entry.getKey(), entry.getValue());
      }
    }

    // 创建http对象
    HttpGet httpGet = new HttpGet(uriBuilder.build());
    /**
     * setConnectTimeout：设置连接超时时间，单位毫秒。 setConnectionRequestTimeout：设置从connect
     * Manager(连接池)获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     */
    RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
    httpGet.setConfig(requestConfig);

    // 设置请求头
    packageHeader(headers, httpGet);

    // 创建httpResponse对象
    CloseableHttpResponse httpResponse = null;

    try {
      // 执行请求并获得响应结果
      return getHttpClientResult(httpResponse, httpClient, httpGet);
    } finally {
      // 释放资源
      release(httpResponse, httpClient);
    }
  }

  /**
   * 发送post请求；不带请求头和请求参数
   *
   * @param url 请求地址
   * @return
   * @throws Exception
   */
  public static String doPost(String url) throws Exception {
    return doPost(url, null, null);
  }

  /**
   * 发送post请求；带请求参数
   *
   * @param url 请求地址
   * @param params 参数集合
   * @return
   * @throws Exception
   */
  public static String doPost(String url, Map<String, String> params) throws Exception {
    return doPost(url, null, params);
  }

  /**
   * 发送post请求；带请求头和请求参数
   *
   * @param url 请求地址
   * @param headers 请求头集合
   * @param params 请求参数集合
   * @return
   * @throws Exception
   */
  public static String doPost(String url, Map<String, String> headers, Map<String, String> params)
      throws Exception {
    // 创建httpClient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();

    // 创建http对象
    HttpPost httpPost = new HttpPost(url);
    /**
     * setConnectTimeout：设置连接超时时间，单位毫秒。 setConnectionRequestTimeout：设置从connect
     * Manager(连接池)获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     */
    RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
    httpPost.setConfig(requestConfig);
    // 设置请求头
    /*httpPost.setHeader("Cookie", "");
    httpPost.setHeader("Connection", "keep-alive");
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
    httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");*/
    packageHeader(headers, httpPost);

    // 封装请求参数
    packageParam(params, httpPost);

    // 创建httpResponse对象
    CloseableHttpResponse httpResponse = null;

    try {
      // 执行请求并获得响应结果
      return getHttpClientResult(httpResponse, httpClient, httpPost);
    } finally {
      // 释放资源
      release(httpResponse, httpClient);
    }
  }

  /**
   * 发送put请求；不带请求参数
   *
   * @param url 请求地址
   * @return
   * @throws Exception
   */
  public static String doPut(String url) throws Exception {
    return doPut(url);
  }

  /**
   * 发送put请求；带请求参数
   *
   * @param url 请求地址
   * @param params 参数集合
   * @return
   * @throws Exception
   */
  public static String doPut(String url, Map<String, String> params) throws Exception {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut(url);
    RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
    httpPut.setConfig(requestConfig);

    packageParam(params, httpPut);

    CloseableHttpResponse httpResponse = null;

    try {
      return getHttpClientResult(httpResponse, httpClient, httpPut);
    } finally {
      release(httpResponse, httpClient);
    }
  }

  /**
   * 发送delete请求；不带请求参数
   *
   * @param url 请求地址
   * @return
   * @throws Exception
   */
  public static String doDelete(String url) throws Exception {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpDelete httpDelete = new HttpDelete(url);
    RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
    httpDelete.setConfig(requestConfig);

    CloseableHttpResponse httpResponse = null;
    try {
      return getHttpClientResult(httpResponse, httpClient, httpDelete);
    } finally {
      release(httpResponse, httpClient);
    }
  }

  /**
   * 发送delete请求；带请求参数
   *
   * @param url 请求地址
   * @param params 参数集合
   * @return
   * @throws Exception
   */
  public static String doDelete(String url, Map<String, String> params) throws Exception {
    if (params == null) {
      params = new HashMap<String, String>();
    }

    params.put("_method", "delete");
    return doPost(url, params);
  }

  /** ******************************* */

  /**
   * Description: 封装请求头(核心)
   *
   * @param params
   * @param httpMethod
   */
  public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
    // 封装请求头
    if (params != null) {
      Set<Map.Entry<String, String>> entrySet = params.entrySet();
      for (Map.Entry<String, String> entry : entrySet) {
        // 设置到请求头到HttpRequestBase对象中
        httpMethod.setHeader(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Description: 封装请求参数(核心)(这个封装会有问题)
   *
   * @param params
   * @param httpMethod
   * @throws UnsupportedEncodingException
   */
  public static void packageParam(
      Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
      throws UnsupportedEncodingException {
    // 封装请求参数
    if (params != null) {
      List<NameValuePair> nvps = new ArrayList<>();
      Set<Map.Entry<String, String>> entrySet = params.entrySet();
      for (Map.Entry<String, String> entry : entrySet) {
        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }

      // 设置到请求的http对象中
      httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
    }
  }

  /**
   * Description: 获得响应结果
   *
   * @param httpResponse
   * @param httpClient
   * @param httpMethod
   * @return
   * @throws Exception
   */
  public static String getHttpClientResult(
      CloseableHttpResponse httpResponse,
      CloseableHttpClient httpClient,
      HttpRequestBase httpMethod)
      throws Exception {
    // 执行请求
    httpResponse = httpClient.execute(httpMethod);

    // 获取返回结果
    String content = "";
    if (httpResponse != null && httpResponse.getStatusLine() != null) {
      if (httpResponse.getEntity() != null) {
        content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
      }
    }
    return content;
  }

  /**
   * Description: 释放资源
   *
   * @param httpResponse
   * @param httpClient
   * @throws IOException
   */
  public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient)
      throws IOException {
    // 释放资源
    if (httpResponse != null) {
      httpResponse.close();
    }
    if (httpClient != null) {
      httpClient.close();
    }
  }

  /**
   * *****************************************soap请求，用于webservice平台*****************************************
   */
  /**
   * @author: fangl
   * @description: 发送soap请求
   * @date: 10:52 2019/3/1
   */
  private static String soapSend(Map<String, Object> map) throws Exception {
    // 拼接请求报文，这个是根据要发送格式来的
    String sendMsg = soapConcat(map);
    String respContent = "";
    try {
      // 发送容器
      HttpClient client = HttpClients.createDefault();
      // 发送对象
      HttpPost request2 = new HttpPost((String) map.get("url"));
      request2.setEntity(new StringEntity(sendMsg, "utf-8"));
      request2.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
      // 注意这个，和webservice不同的是，这个可能会用上，注意，对应的值是这个接口的名称
      // 这个bug是因为用http的post发送方式引起的，它是用底层协议发送，并没有指定具体要发送服务器和接口
      request2.setHeader("SOAPAction", "http://192.168.1.239:8091/service/sendSms");
      // 发送
      HttpResponse response2 = client.execute(request2);
      // 相应
      HttpEntity entity = response2.getEntity();
      if (entity != null) {
        respContent = EntityUtils.toString(entity, "UTF-8");
        // System.out.println(respContent);
      }
    } catch (IOException e) {
      log.error("异常={}", e.getMessage(), e);
    }
    return respContent;
  }

  /**
   * @author: fangl
   * @description: soap请求内容拼接示例(具体的拼接格式webservice接口后面拼接wsdl就会有这个接口的发送文档存在的;
   *     或者使用soapui这个工具来操作一遍，会自动根据文档产生这个接口的发送数据)
   * @date: 10:53 2019/3/1
   */
  private static String soapConcat(Map<String, Object> map) {
    StringBuffer sb = new StringBuffer();
    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
    sb.append(
        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sms=\"http://www.csapi.org/schema/sms\">\n");
    sb.append(" <soapenv:Header/>\n");
    sb.append(" <soapenv:Body>\n");

    sb.append(" <sms:sendSmsRequest>\n");
    sb.append(" <ApplicationID></ApplicationID>\n");
    sb.append(" <!--Zero or more repetitions:-->\n");
    sb.append(" <DestinationAddresses>tel:18260093234</DestinationAddresses>\n");
    sb.append(" <DestinationAddresses>tel:18762137837</DestinationAddresses>\n");
    sb.append(" <ExtendCode>18</ExtendCode>\n");
    sb.append(" <Message>" + map.get("content") + "</Message>\n");
    sb.append(" <MessageFormat>GB2312</MessageFormat>\n");
    sb.append(" <SendMethod>Normal</SendMethod>\n");
    sb.append(" <DeliveryResultRequest>True</DeliveryResultRequest>\n");
    sb.append(" </sms:sendSmsRequest>\n");

    sb.append(" </soapenv:Body>\n");
    sb.append("</soapenv:Envelope>");
    return sb.toString();
  }

  /**
   * *****************************************http的websevice请求，俺觉得本质上还是http，只不过内容变成xml而已*****************************************
   */

  /**
   * 发送方法
   *
   * @throws IOException
   * @author fangl 2017-11-21 下午11:11:50
   */
  public static String postData(Map<String, String> map, String url) throws IOException {
    // 发送器
    HttpClient httpclient = HttpClients.createDefault();
    // 发送体
    HttpPost httppost = new HttpPost(url);
    // 参数封装
    List<NameValuePair> formparams = new ArrayList<>();
    for (Map.Entry<String, String> me : map.entrySet()) {
      // formparams.add(new BasicNameValuePair("XMLStr",(String)map.get("xml")));
      formparams.add(new BasicNameValuePair(me.getKey(), me.getValue()));
    }
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
    // entity.setContentType("text/xml; charset=GBK");
    httppost.setEntity(entity);
    // 发送
    HttpResponse response = httpclient.execute(httppost);
    // 返回
    HttpEntity resEntity = response.getEntity();

    // 解析
    String alength = EntityUtils.toString(resEntity, "UTF-8");
    /*InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
    //数据处理
    char[] buff = new char[1024];
    int length = 0;
    String alength="";
    while ((length = reader.read(buff)) != -1) {
        alength=new String(buff, 0, length);
        httpclient.getConnectionManager().shutdown();
    }*/
    return alength;
  }

  /**
   * @author: fangl
   * @description: pojo转换成xml
   * @date: 11:14 2019/3/1
   */
  public static String convertToXml(Object obj, String encoding) throws Exception {
    JAXBContext context = JAXBContext.newInstance(obj.getClass());
    Marshaller marshaller = context.createMarshaller();
    // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
    StringWriter writer = new StringWriter();
    marshaller.marshal(obj, writer);
    String result = writer.toString().replace(" standalone=\"yes\"", "");
    return result;
  }

  /**
   * @author: fangl
   * @description: xml转换成JavaBean
   * @date: 11:15 2019/3/1
   */
  @SuppressWarnings("unchecked")
  public static <T> T convertToJavaBean(String xml, Class<T> t) throws Exception {
    JAXBContext context = JAXBContext.newInstance(t);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    T obj = (T) unmarshaller.unmarshal(new StringReader(xml));
    return obj;
  }
}
