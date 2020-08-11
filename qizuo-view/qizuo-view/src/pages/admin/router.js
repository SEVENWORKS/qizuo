import router from '@/commonRouter/index'

window.routes = [
    {
        path: "/",
        component: () => import('@comp/layout'),
        redirect: "/system_user",
        meta: {
            title: "用户",
            icon: "peoples",
        },
        children: [
            {
                path: '/system_user',
                name: 'system_user',
                component: () => import('./views/admins/system_user'),
                meta: {title: "用户管理"}
            },
            {
                path: '/system_user_Do',
                name: 'system_user_Do',
                component: () => import('./views/admins/system_user_do'),
                meta: {title: "用户新增/修改"},
                hidden: true
            },
        ],
    },
    {
        path: "/role",
        component: () => import('@comp/layout'),
        redirect: "/system_role",
        meta: {
            title: "角色",
            icon: "user",
        },
        children: [
            {
                path: '/system_role',
                name: 'system_role',
                component: () => import('./views/admins/system_role'),
                meta: {title: "角色管理"}
            },
            {
                path: '/system_role_do',
                name: 'system_role_do',
                component: () => import('./views/admins/system_role_do'),
                meta: {title: "角色新增/修改"},
                hidden: true
            },
        ],
    },
    {
        path: "/menu",
        component: () => import('@comp/layout'),
        redirect: "/system_menu",
        meta: {
            title: "菜单",
            icon: "tree-table",
        },
        children: [
            {
                path: '/system_menu',
                name: 'system_menu',
                component: () => import('./views/admins/system_menu'),
                meta: {title: "菜单管理"}
            },
            {
                path: '/system_menu_do',
                name: 'system_menu_do',
                component: () => import('./views/admins/system_menu_do'),
                meta: {title: "菜单新增/修改"},
                hidden: true
            },
        ],
    }
]

router.addRoutes(window.routes)

export default router

