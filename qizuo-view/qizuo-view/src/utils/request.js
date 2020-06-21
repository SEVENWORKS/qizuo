import axios from "axios";

// 创建axios实例
const service = axios.create({
  //这里baseurl就是刚开始配置的开发环境和线上环境地址，webpack会自动读取无需手动再改
  baseURL: process.env.BASE_URL, //baseurl
  timeout: 5000, // 请求超时时间
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    if (
      config.method.toLocaleLowerCase() === "post" ||
      config.method.toLocaleLowerCase() === "put" ||
      config.method.toLocaleLowerCase() === "delete"
    ) {
      config.data = qs.stringify(config.data);
    }
    return config;
  },
  (error) => {
    return Promise.reject(error); // 在调用的那边可以拿到(catch)你想返回的错误信息
  }
);

// respone拦截器
service.interceptors.response.use(
  (response) => response,
  (error) => {}
);
export default service;
