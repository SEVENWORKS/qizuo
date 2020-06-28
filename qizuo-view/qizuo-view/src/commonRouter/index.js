//公共路由
export default [
  {
    path: "/error",
    name: "error",
    component: () => import("@comp/error/404"),
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@comp/error/404"),
  },
];
