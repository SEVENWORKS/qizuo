export default [
    {
        path: "/error",
        name: "error",
        component: () => import("@comp/error/404"),
    },
];