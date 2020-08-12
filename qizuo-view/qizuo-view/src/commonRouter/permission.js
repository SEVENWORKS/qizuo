import router from "@router";
import store from "@store";
import { Message } from "element-ui";
import NProgress from "nprogress"; // progress bar
import "nprogress/nprogress.css"; // progress bar style
import { getToken } from "@/utils/frames/auth"; // get token from cookie

//config
NProgress.configure({ showSpinner: false }); // NProgress Configuration
const whiteList = ["/login"]; // no redirect whitelist

//路由守卫
router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start();

  // set page title
  document.title = to.meta.title || "QIZUO";

  // token处理
  const hasToken = getToken();
  //有token
  if (hasToken) {
    //存在token的情况下直接跳转到对应页面就行
    if (to.path === "/login") {
      next({ path: "/" });
      NProgress.done();
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
          Message.error(error || "Has Error");
          next(`/login?redirect=${to.path}`);
          NProgress.done();
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
      NProgress.done();
    }
  }
});
//最终不管如何都done一下
router.afterEach(() => {
  // finish progress bar
  NProgress.done();
});
