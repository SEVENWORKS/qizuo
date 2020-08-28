import axios from "axios";
import { MessageBox, Message } from "element-ui";
import store from "@store";
import { getToken } from "@/utils/frames/auth";

// create an axios instance
const service = axios.create({
  baseURL: "http://127.0.0.1:9300/", // process.env.VUE_APP_BASE_API
  timeout: 5000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    //对token进行处理
    if (store.getters.token) {
      config.headers["X-Token"] = getToken();
    }
    return config;
  },
  (error) => {
    //发送异常处理
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  (response) => {
    const res = response.data;
    //错误异常处理
    if (res.code !== 20000) {
      Message({
        message: res.message || "Error",
        type: "error",
        duration: 5 * 1000,
      });
      // token相关异常处理:50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        //重新登录弹框
        MessageBox.confirm(
          "You have been logged out, you can cancel to stay on this page, or log in again",
          "Confirm logout",
          {
            confirmButtonText: "重新登录",
            cancelButtonText: "取消",
            type: "warning",
          }
        ).then(() => {
          store.dispatch("user/resetToken").then(() => {
            location.reload();
          });
        });
      }
      return Promise.reject(new Error(res.message || "Error"));
    } else {
      //正确返回
      return res;
    }
  },
  (error) => {
    //返回异常处理
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000,
    });
    return Promise.reject(error);
  }
);

export default service;
