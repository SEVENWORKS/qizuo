import router from '@router'

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
            }
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
            }
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
            }
        ],
    },
    {
        path: "/file",
        component: () => import('@comp/layout'),
        redirect: "/system_file",
        meta: {
            title: "文件",
            icon: "tree-table",
        },
        children: [
            {
                path: '/system_file',
                name: 'system_file',
                component: () => import('./views/admins/system_file'),
                meta: {title: "文件列表"}
            },
            {
                path: '/system_file_log',
                name: 'system_file_log',
                component: () => import('./views/admins/system_file_log'),
                meta: {title: "文件日志"}
            }
        ],
    }
]

export default router

