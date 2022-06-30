export default [
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
            icon: "documentation",
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
    },
    {
        path: "/wx",
        component: () => import('@comp/layout'),
        redirect: "/wx_menu",
        meta: {
            title: "微信",
            icon: "wechat",
        },
        children: [
            {
                path: '/wx_menu',
                name: 'wx_menu',
                component: () => import('./views/provider/wx/wx_menu'),
                meta: {title: "微信菜单"}
            },
            {
                path: '/wx_msg',
                name: 'wx_msg',
                component: () => import('./views/provider/wx/wx_msg'),
                meta: {title: "微信信息"}
            }
        ],
    },
    {
        path: "/spider",
        component: () => import('@comp/layout'),
        redirect: "/spider_run",
        meta: {
            title: "爬虫",
            icon: "bug",
        },
        children: [
            {
                path: '/spider_run',
                name: 'spider_run',
                component: () => import('./views/provider/spider/spider_run'),
                meta: {title: "爬虫"}
            },
            {
                path: '/spider_data',
                name: 'spider_data',
                component: () => import('./views/provider/spider/spider_data'),
                meta: {title: "爬虫数据"}
            }
        ],
    },
    {
        path: "/qrcode",
        component: () => import('@comp/layout'),
        redirect: "/qrcode_make",
        meta: {
            title: "二维码",
            icon: "search",
        },
        children: [
            {
                path: '/qrcode_make',
                name: 'qrcode_make',
                component: () => import('./views/provider/qrcode/qrcode_make'),
                meta: {title: "二维码制作"}
            }
        ],
    }
]

