import router from '@/commonRouter/index'

window.routes = [
    {
        path: "/",
        component: () => import('@comp/layout'),
        redirect: "/system_user",
        children: [
            {
                path: '/system_user',
                name: 'systemUser',
                component: () => import('./views/admins/system_user'),
                meta: {}
            },
            {
                path: '/system_user_Do',
                name: 'systemUserDo',
                component: () => import('./views/admins/system_user_do')
            },
            {
                path: '/system_role',
                name: 'systemRole',
                component: () => import('./views/admins/system_role')
            },
            {
                path: '/system_role_do',
                name: 'systemRoleDo',
                component: () => import('./views/admins/system_role_do')
            },
            {
                path: '/system_menu',
                name: 'systemMenu',
                component: () => import('./views/admins/system_menu')
            },
            {
                path: '/system_menu_do',
                name: 'systemMenuDo',
                component: () => import('./views/admins/system_menu_do')
            },
            {
                path: '/base_frames',
                name: 'baseFrames',
                component: () => import('./views/frames/base_frames')
            },
        ],
    }
]

export default router

