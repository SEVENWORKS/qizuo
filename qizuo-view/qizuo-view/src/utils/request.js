import axios from "axios";

// create an axios instance
const service = axios.create({
  baseURL: window.$global.url_prefix, // process.env.VUE_APP_BASE_API
  timeout: 10000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    //对token进行处理
    const token = window._vm.$store.getters.user.token;
    if (token) {
      config.headers["X-QIZUO"] = token;
      //spring security的token必须要加上token的类别，通常是bearer
      config.headers["Authorization"] =
        window._vm.$store.state.user.tokenType + " " + token;
    }

    //url特殊处理标识
    if(config.url.includes(window.$global.responseNotDo)){
      config.url=config.url.replace(window.$global.responseNotDo,"");
      config[window.$global.responseNotDo]=true;
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
      //正确返回
      
      //角色判断
      roleDo(response);

      //错误判断
      const code=errorDo(response);
      if(code==-1){
        return {}
      }
      return response;
  },
  (error) => {
    //返回异常处理

    //角色判断
    roleDo(error);

    //错误判断
    const code=errorDo(error);
    if(code==-1){
      return {}
    }
    return Promise.reject(error);
  }
);

//角色处理
function roleDo(res){
  const response=res?.response??res;
  if(response&&(response.status==401||response.status==402||response.status==403||response.status==498||response.status==502)){
    window._vm.$utils.alert("权限异常");
    //登出
    window._vm.$store.dispatch("user/logout");
  }
}

//错误处理
function errorDo(res){
  let back=0;

  const response=res?.response??res;

  //非200状态判断
  if(!response.config[window.$global.responseNotDo]&&response.status!=200){
    back=-1
    window._vm.$utils.alert("服务异常");
    return back;
  }

  //非正确code判断
  const resultCode=response?.data?.code;
  if(!response.config[window.$global.responseNotDo]&&resultCode!=0){
    back=-1
    window._vm.$utils.alert(response.data.message);
    return back;
  }

  return back;
}

export default service;
