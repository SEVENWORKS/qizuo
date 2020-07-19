import Vue from 'vue'
import VueRouter from 'vue-router'
import commonRouter from '@/commonRouter/index'
Vue.use(VueRouter)
const routes = [
    ...commonRouter,
    {
        path: '/',
        redirect:'/system_user'
    },
    {
        path: '/system_user',
        name: 'systemUser',
        component: () => import('./views/admins/system_user')
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
    {
        path: '/qizuo',
        name: 'qizuo',
        component: () => import('@comp/login/base_login')
    }
]


//创建路由
const createRouter = () =>
    new VueRouter({
        mode: 'hash',
        scrollBehavior: () => ({ y: 0 }),
        base: process.env.BASE_URL,
        routes
    })
const router = createRouter();

//重置路由
export function resetRouter() {
    const newRouter = createRouter();
    router.matcher = newRouter.matcher;
}

export default router
