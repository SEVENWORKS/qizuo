export default [
    {
        path: "/",
        name: "init",
        redirect: "/system/system_user"
    },
    {
        path: "/error",
        name: "error",
        component: () => import("@/components/error"),
    },
    {
        path: "/login",
        name: "login",
        component: () => import("@/components/login"),
    }
];