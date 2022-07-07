export default [
    {
        path: "/system",
        component: () => import('@/components/layout'),
        redirect: "/system/system_user",
        meta: {title: "用户"},
        children: [
            {
                path: 'system_user',
                name: 'system_user',
                component: () => import('@adminP/admins/system_user'),
                meta: {title: "用户管理"}
            },
            {
                path: 'system_role',
                name: 'system_role',
                component: () => import('@adminP/admins/system_role'),
                meta: {title: "角色管理"}
            },
            {
                path: 'system_menu',
                name: 'system_menu',
                component: () => import('@adminP/admins/system_menu'),
                meta: {title: "菜单管理"}
            }
        ],
    },
    {
        path: "/file",
        component: () => import('@/components/layout'),
        redirect: "/file/system_file",
        meta: {title: "文件"},
        children: [
            {
                path: 'system_file',
                name: 'system_file',
                component: () => import('@adminP/admins/system_file'),
                meta: {title: "文件列表"}
            },
            {
                path: 'system_file_log',
                name: 'system_file_log',
                component: () => import('@adminP/admins/system_file_log'),
                meta: {title: "文件日志"}
            }
        ],
    },
    {
        path: "/wx",
        component: () => import('@/components/layout'),
        redirect: "/wx/wx_menu",
        meta: {title: "微信"},
        children: [
            {
                path: 'wx_menu',
                name: 'wx_menu',
                component: () => import('@adminP/provider/wx/wx_menu'),
                meta: {title: "微信菜单"}
            },
            {
                path: 'wx_msg',
                name: 'wx_msg',
                component: () => import('@adminP/provider/wx/wx_msg'),
                meta: {title: "微信信息"}
            }
        ],
    },
    {
        path: "/spider",
        component: () => import('@/components/layout'),
        redirect: "/spider/spider_run",
        meta: {title: "爬虫"},
        children: [
            {
                path: 'spider_run',
                name: 'spider_run',
                component: () => import('@adminP/provider/spider/spider_run'),
                meta: {title: "爬虫"}
            },
            {
                path: 'spider_data',
                name: 'spider_data',
                component: () => import('@adminP/provider/spider/spider_data'),
                meta: {title: "爬虫数据"}
            }
        ],
    },
    {
        path: "/qrcode",
        component: () => import('@/components/layout'),
        redirect: "/qrcode/qrcode_make",
        meta: {title: "二维码"},
        children: [
            {
                path: 'qrcode_make',
                name: 'qrcode_make',
                component: () => import('@adminP/provider/qrcode/qrcode_make'),
                meta: {title: "二维码制作"}
            }
        ],
    }
]

