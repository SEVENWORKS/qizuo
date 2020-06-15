<!-- 最左侧和左侧头像部分 -->
<div class="side-widgets overflow">
    <!-- 头像部分 -->
    <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
        <!-- 头像 -->
        <a href="" data-toggle="dropdown">
            <img class="profile-pic animated" src="" alt="">
        </a>
        <!-- 头像点击菜单 -->
        <ul class="dropdown-menu profile-menu">

        </ul>
        <!-- 头像下文字 -->
        <h4 class="m-0">qizuo</h4>
    </div>

    <!-- 日历 -->
    <div class="s-widget m-b-25">
        <div id="sidebar-calendar"></div>
    </div>

    <!-- 更多模块：日历下的新闻 -->
    <%--<div class="s-widget m-b-25">
        <h2 class="tile-title">
           News Feeds
        </h2>

        <div class="s-widget-body">
            <div id="news-feed"></div>
        </div>
    </div>--%>

    <!-- 更多模块：新闻的公告栏 -->
    <%--<div class="s-widget m-b-25">
        <h2 class="tile-title">
            Projects on going
        </h2>

        <div class="s-widget-body">
            <div class="side-border">
                <small>Joomla Website</small>
                <div class="progress progress-small">
                     <a href="#" data-toggle="tooltip" title="" class="progress-bar tooltips progress-bar-danger" style="width: 60%;" data-original-title="60%">
                          <span class="sr-only">60% Complete</span>
                     </a>
                </div>
            </div>
            <div class="side-border">
                <small>Opencart E-Commerce Website</small>
                <div class="progress progress-small">
                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-info" style="width: 43%;" data-original-title="43%">
                          <span class="sr-only">43% Complete</span>
                     </a>
                </div>
            </div>
            <div class="side-border">
                <small>Social Media API</small>
                <div class="progress progress-small">
                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-warning" style="width: 81%;" data-original-title="81%">
                          <span class="sr-only">81% Complete</span>
                     </a>
                </div>
            </div>
            <div class="side-border">
                <small>VB.Net Software Package</small>
                <div class="progress progress-small">
                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-success" style="width: 10%;" data-original-title="10%">
                          <span class="sr-only">10% Complete</span>
                     </a>
                </div>
            </div>
            <div class="side-border">
                <small>Chrome Extension</small>
                <div class="progress progress-small">
                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-success" style="width: 95%;" data-original-title="95%">
                          <span class="sr-only">95% Complete</span>
                     </a>
                </div>
            </div>
        </div>
    </div>--%>
</div>

<script>
    /** 登录人信息 */
    function baseLeftBar2_user(user){
        //参数
        var photo=isNotBlank(user)&&isNotBlank(user.photo)?user.photo:"${pageContext.request.contextPath}/static/img/frame/profile-pic.jpg";
        var name=isNotBlank(user)&&isNotBlank(user.name)?user.name:"";
        //头像
        $("#profile-menu a img").prop("src",photo);
        //姓名
        $("#profile-menu h4").text(name);
    }

    /** 登录人菜单配置(读取全局配置) */
    function baseLeftBar2_userMenu(userMenus){
        if(isNotBlank(userMenus)){
            var html='';
            for(var i=0;i<userMenus.length;i++){
                //参数
                var title=isNotBlank(userMenus[i].title)?userMenus[i].title:"";
                var url=isNotBlank(userMenus[i].url)?'${pageContext.request.contextPath}/'+userMenus[i].url:"";
                var type=isNotBlank(userMenus[i].type)?userMenus[i].type:"";
                var func=userMenus[i].func;
                //html
                html+='<li><a href="#" onclick="baseLeftBar2_userFunc(\''+url+'\','+type+','+func+')">'+title+'</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i>';
            }
            $("#profile-menu ul").append(html);
        }
    }

    /** 登录人菜单触发(配合上方函数) */
    function baseLeftBar2_userFunc(url,type,func){
        if(isNotBlank(url)&&isNotBlank(type)){
            if(type==global$urlGetType){
                window.open(url);
            }else{
                $.post(url,{},function(data){
                    backResultAlert(data,function(){
                        if(isFunction(func)){
                            func();
                        }
                    });
                })
            }
        }
    }
</script>
