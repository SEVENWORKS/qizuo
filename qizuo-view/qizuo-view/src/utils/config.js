window.$global = {
  /** 全局有效 */
  success: 0,
  /** 全局无效 */
  error: -1,
  /** url prefix */
  url_prefix: "http://81.68.193.56:8091/port", //"http://81.68.193.56:9300/zuul", //http://47.114.138.216:8091/port
  /** 主menu菜单 */
  sysMenu:[
    {name:'用户',icon:'el-icon-user',url:'',child:[{name:'用户管理',icon:'',url:'/system/system_user',child:[]},{name:'角色管理',icon:'',url:'/system/system_role',child:[]},{name:'菜单管理',icon:'',url:'/system/system_menu',child:[]}]},
    {name:'文件',icon:'el-icon-folder',url:'',child:[{name:'文件列表',icon:'',url:'/file/system_file',child:[]},{name:'文件日志',icon:'',url:'/file/system_file_log',child:[]}]},
    {name:'微信',icon:'el-icon-chat-round',url:'',child:[{name:'微信菜单',icon:'',url:'/wx/wx_menu',child:[]},{name:'微信菜单',icon:'',url:'/wx/wx_msg',child:[]}]},
    {name:'爬虫',icon:'el-icon-soccer',url:'',child:[{name:'爬虫',icon:'',url:'/spider/spider_run',child:[]},{name:'爬虫数据',icon:'',url:'/spider/spider_data',child:[]}]},
    {name:'二维码',icon:'el-icon-cpu',url:'',child:[{name:'二维码',icon:'',url:'/qrcode/qrcode_make',child:[]}]}
  ]
};
