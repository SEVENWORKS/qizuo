import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);

//公共路由
export const baseRoutes = [
  {
    path: "/error",
    name: "error",
    component: () => import("@comp/error/404"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@comp/login/base_login"),
  },
  {
    path: "/redirect",
    component: () => import("@comp/layout"),
    hidden: true,
    children: [
      {
        path: "/redirect/:path(.*)",
        component: () => import("@comp/redirect"),
      },
    ],
  },
];

//创建路由
const createRouter = () =>
  new VueRouter({
    mode: "hash",
    scrollBehavior: () => ({ y: 0 }),
    base: process.env.BASE_URL,
    routes: baseRoutes,
  });

//重置路由
const router = createRouter();
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher;
}

export default router;
