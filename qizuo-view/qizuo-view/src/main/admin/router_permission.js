import router from "./router.js";
import store from "./store.js";
import { token } from "@/apis/user"; // get token from cookie

//config
const whiteList = ["/login"]; // no redirect whitelist
//路由守卫
router.beforeEach(async (to, from, next) => {
  // set page title
  document.title = to.meta.title || "QIZUO";

  // token处理
  const hasToken = token();
  //有token
  if (hasToken) {
    //存在token的情况下直接跳转到对应页面就行
    if (to.path === "/login") {
      next({ path: "/" });
    } else {
      //判断是否有角色，即在有token的情况下，也可能会出现重新登录这种情况，或者过期的情况
      const hasRoles = store.getters.roles && store.getters.roles.length > 0;
      if (hasRoles) {
        next();
      } else {
        //重新获取用户信息
        try {
          // get user info
          // note: roles must be a object array! such as: ['admin'] or ,['developer','editor']
          const { roles } = await store.dispatch("user/getInfo");

          // generate accessible routes map based on roles
          const accessRoutes = await store.dispatch(
            "routes/generateRoutes",
            roles
          );

          // dynamically add accessible routes，这个地方动态路由的时候，不能在路由中配置*的路由，要不然就会自动先转向*的路由
          router.addRoutes(accessRoutes);

          // hack method to ensure that addRoutes is complete
          // set the replace: true, so the navigation will not leave a history record
          next({ ...to, replace: true });
        } catch (error) {
          //中间有差错，这地方弥补下，重新登录
          await store.dispatch("user/resetToken");
          next(`/login?redirect=${to.path}`);
        }
      }
    }
  } else {
    //没有token
    //判断白名单
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next();
    } else {
      //不在白名单直接登录
      next(`/login?redirect=${to.path}`);
    }
  }
});