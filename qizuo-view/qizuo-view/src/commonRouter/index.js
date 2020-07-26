//公共路由
export default [
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
