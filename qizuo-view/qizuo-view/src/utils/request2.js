import axios from "axios";
import { MessageBox, Message } from "element-ui";
import store from "@store";
import { getToken } from "@/utils/frames/auth";
import "@utils/base_global";

// create an axios instance
const service = axios.create({
  baseURL: "http://127.0.0.1:9940/", // process.env.VUE_APP_BASE_API
  timeout: 10000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    //对token进行处理
    config.headers["qizuo"] = 2222;
    if (store.getters.token) {
      config.headers["X-QIZUO"] = getToken();
      //spring security的token必须要加上token的类别，通常是bearer
      config.headers["Authorization"] =
        window._vm.$store.state.user.tokenType + " " + getToken();
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
    //请求头判断，是否需要重新请求token
    const isNewtoken = response.headers["QIZUO-Renew-Header"];
    //错误异常处理
    if (response.status !== 200) {
      Message({
        message: res.message || "Error",
        type: "error",
        duration: 5 * 1000,
      });
      // token相关异常处理:50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (
        response.status === 401 ||
        response.status === 403 ||
        response.status === 402
      ) {
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
      } else {
        //重新请求token
        if (isNewtoken) {
          window._vm.$store.dispatch("user/queryToken");
        }
      }
      return Promise.reject(new Error(res.message || "Error"));
    } else {
      //重新请求token
      if (isNewtoken) {
        window._vm.$store.dispatch("user/queryToken");
      }
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
    //token过期
    if (error.response.status === 401) {
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
    return Promise.reject(error);
  }
);

export default service;
