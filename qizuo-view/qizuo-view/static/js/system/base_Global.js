/** 基本配置js global$ */
/** 全局yes */
var global$yes=1;
/** 全局no */
var global$no=0;
/** 全局有效 */
var global$statusYes=0;
/** 全局无效 */
var global$statusNo=1;
/** 返回成功码 */
var global$backSuccessCode=1;
/** 返回异常码 */
var global$backErrorCode=-9;
/** 树形菜单首节点配置 */
var global$treeFirst=-1;
/** url类型为同步 */
var global$urlGetType=0;
/** url类型为ajax */
var global$urlPostType=1;
/** url标识是否需要新页面打开 */
var global$openWindow='windowOpen/';
/** 标识全局容器(对立于iframe) */
var global$frameContainer='#baseFrame';
/** 标识当前跳转主题页路径 */
var global$frameUrl='';
/** 用户菜单配置(图像下方) */
var userMenus=[
    {
        title:'退出登录',
        type:1,//type=0为跳转方法,type=1为ajax方法
        url:'module/system/login/loginOut',
        func:function(){
            window.location.href=getRootPath()+'jump/system/base/login';
        }
    }
]