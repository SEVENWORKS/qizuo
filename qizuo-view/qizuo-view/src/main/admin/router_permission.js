import router from "./router.js";
import store from "./store.js";

const accessToken="access_token"
const code="code"
//路由守卫
router.beforeEach(async (to, from, next) => {
  //刷新页面，token重新获取
  const atToken=window.localStorage[window.$global.tokenName]
  if(atToken){
    store.commit("user/SET_TOKEN",atToken);
  }

  //判断
  const href=window.location.href;
  if(store.getters.user.token||href.includes("error")){
    //网页存在token或者服务器错误
    //判断用户是否登录
    store.dispatch("user/login").then(()=>{
      next()
    })
  }else if(href.includes(code)||href.includes(accessToken)){
    //服务器返回token或者返回code
    backAfterStoreToken(href,next)
  }else{
    //不存在则进行登录(暂时只支持token,code可做后续拓展)
    window.location.href=window.$global.url_prefix+"/oauth/authorize?response_type=token&client_id=qizuo&redirect_uri="+href
  }
});

//登录后token处理(暂时只支持token)
function backAfterStoreToken(href,next){
  //服务器返回token或者返回code

  //存储token
  const token=href.substring(href.indexOf(accessToken+"=")+(accessToken+"=").length,
  href.indexOf("&"))
  store.commit("user/SET_TOKEN",token);
  //登录记录
  store.dispatch("user/login").then(()=>{
    next()
  })
}