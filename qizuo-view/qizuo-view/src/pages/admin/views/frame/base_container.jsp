<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>七作</title>
    <!-- header static tag in -->
    <%@ include file="base_tags.jsp"%>
    <!-- css base -->
    <link href="${staticPath}css/frame/bootstrap.min.css" rel="stylesheet">
    <link href="${staticPath}css/frame/calendar.css" rel="stylesheet">
    <link href="${staticPath}css/frame/style.css" rel="stylesheet">
    <link href="${staticPath}css/frame/icons.css" rel="stylesheet">
    <link href="${staticPath}css/frame/animate.min.css" rel="stylesheet">
    <link href="${staticPath}css/frame/font-awesome.min.css" rel="stylesheet">
    <link href="${staticPath}css/frame/form.css" rel="stylesheet">
    <link href="${staticPath}css/frame/generics.css" rel="stylesheet">
    <!-- ztree -->
    <link rel="stylesheet" href="${staticPath}js/ztree/css/zTreeStyle.css">


    <!-- script base -->
    <!-- jQuery -->
    <script src="${staticPath}js/frame/jquery.min.js"></script>
    <!-- layer.js -->
    <script src="${staticPath}js/layui/layer/layer.js"></script>
    <!-- script base system -->
    <script src="${staticPath}js/system/base_Global.js"></script>
    <script src="${staticPath}js/system/base_functions.js"></script>
    <script src="${staticPath}js/system/base_utils.js"></script>
    <!-- jQuery base -->
    <script src="${staticPath}js/frame/jquery-ui.min.js"></script>
    <script src="${staticPath}js/frame/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="${staticPath}js/frame/bootstrap.min.js"></script>
    <!-- Charts -->
    <script src="${staticPath}js/frame/charts/jquery.flot.js"></script> <!-- Flot Main -->
    <script src="${staticPath}js/frame/charts/jquery.flot.time.js"></script> <!-- Flot sub -->
    <script src="${staticPath}js/frame/charts/jquery.flot.animator.min.js"></script> <!-- Flot sub -->
    <script src="${staticPath}js/frame/charts/jquery.flot.resize.min.js"></script> <!-- Flot sub - for repaint when resizing the screen -->
    <script src="${staticPath}js/frame/sparkline.min.js"></script> <!-- Sparkline - Tiny charts -->
    <script src="${staticPath}js/frame/easypiechart.js"></script> <!-- EasyPieChart - Animated Pie Charts -->
    <script src="${staticPath}js/frame/charts.js"></script> <!-- All the above chart related functions -->
    <!-- Map -->
    <script src="${staticPath}js/frame/maps/jvectormap.min.js"></script> <!-- jVectorMap main library -->
    <script src="${staticPath}js/frame/maps/usa.js"></script> <!-- USA Map for jVectorMap -->
    <!-- DateTime -->
    <script src="${staticPath}js/frame/datetimepicker.min.js"></script>
    <!-- Color -->
    <script src="${staticPath}js/frame/colorpicker.min.js"></script>
    <!-- Textare autosize -->
    <script src="${staticPath}js/frame/autosize.min.js"></script>
    <!-- TextArea Editor -->
    <script src="${staticPath}js/frame/editor2.min.js"></script> <!-- WYSIWYG Editor -->
    <script src="${staticPath}js/frame/markdown.min.js"></script> <!-- Markdown Editor -->
    <!-- Spinner智能数字加减 -->
    <script src="${staticPath}js/frame/spinner.min.js"></script>
    <!-- Input进度条 -->
    <script src="${staticPath}js/frame/slider.min.js"></script>
    <!-- File Upload -->
    <script src="${staticPath}js/frame/fileupload.min.js"></script>
    <!-- Checkbox + Radio -->
    <script src="${staticPath}js/frame/icheck.js"></script>
    <!-- Media -->
    <script src="${staticPath}js/frame/media-player.min.js"></script> <!-- Video Player -->
    <script src="${staticPath}js/frame/pirobox.min.js"></script> <!-- Lightbox -->
    <!-- select -->
    <script src="${staticPath}js/frame/select.min.js"></script><!-- Custom Select(select插件) -->
    <script src="${staticPath}js/frame/chosen.min.js"></script><!-- Custom Multi Select(select插件) -->
    <!-- scroll -->
    <script src="${staticPath}js/frame/scroll.min.js"></script>
    <!--  Form validate -->
    <script src="${staticPath}js/frame/validation/validate.min.js"></script>
    <script src="${staticPath}js/frame/validation/validationEngine.min.js"></script>
    <!-- Input Mask(格式化input输入格式的) -->
    <script src="${staticPath}js/frame/input-mask.min.js"></script>
    <!-- Other -->
    <script src="${staticPath}js/frame/calendar.min.js"></script>
    <script src="${staticPath}js/frame/feeds.min.js"></script>
    <!-- base frame functions -->
    <script src="${staticPath}js/frame/functions.js"></script>
    <!-- pjax -->
    <script src="${staticPath}js/pjax/jquery.pjax.js"></script>
    <!-- art-template -->
    <script src="${staticPath}js/template/art-template.js"></script>
    <!-- base page -->
    <script src="${staticPath}js/system/base_page.js"></script>
    <!-- ztree -->
    <script src="${staticPath}js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="${staticPath}js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <!-- layui -->
    <script src="${staticPath}js/layui/layui.js"></script>

    <!-- css base -->
    <!-- base -->
    <link href="${staticPath}css/system/base.css" rel="stylesheet">
</head>
<!-- id代表皮肤 -->
<body id="skin-tectile">
    <!-- 头部分 -->
    <header id="header" class="media">
        <jsp:include page="base_header.jsp"></jsp:include>
    </header>
    <div class="clearfix"></div>

    <!-- 左侧菜单栏和右侧iframe主体 -->
    <section id="main" class="p-relative" role="main">
        <!-- 左侧菜单栏 -->
        <aside id="sidebar">
            <jsp:include page="base_leftbar.jsp"></jsp:include>
            <jsp:include page="base_leftbar2.jsp"></jsp:include>
        </aside>
        <!-- 头部分2 -->
        <section id="content" class="container">
            <jsp:include page="base_header2.jsp"></jsp:include>
            <!--iframe主体 -->
            <section id="baseFrame">
                <%--<iframe id="baseContainerIframe" src="" style="width:100%;height: 91%" scrolling="auto" frameborder="no"></iframe>--%>
            </section>
        </section>

    </section>

    <!-- 脚部分 -->
    <footer>

    </footer>

    <!-- 框架基本函数执行 -->
    <script>
        /** 核心加载函数(消息/用户信息/菜单列表) */
        $(function(){
            $.post('${modulePath}system/login/frameData',{},function(data){
                backResult(data,function(data){
                    //消息
                    baseHeader_msg(data.baseHeader_msgList);
                    //菜单列表
                    baseLeftbar_menu(isNotBlank(data.userMsg)?data.userMsg.menuPoJos:null);
                    //用户信息
                    baseLeftBar2_user(data.userMsg);
                })
            })
            //头搜索
            baseHeader_mainSearch();
            //用户信息
            baseLeftBar2_userMenu(userMenus);
        })

        /** 左侧菜单点击，异步获取jsp */
        function pjax(url,dom){
            //ajax方式
            var level=$(dom).attr('level');
            //菜单
            var title='';
            var title2='';
            if(0==level){
                title=$(dom).find("span").text();
            }else{
                title2=$(dom).attr('bname');
                title=$(dom).text();
            }
            baseHeader2_pageTitle(title,level,title2);
            //跳转
            if(isNotBlank(url)){
                //菜单url存储
                global$frameUrl=url;
                //跳转判断
                if(-1!=url.indexOf(global$openWindow)){
                    url=url.split(global$openWindow).join("");
                    //框架页面
                    window.open(url);
                    return false;
                }else{
                    //按钮清除
                    buttonRemove();
                    //普通
                    pjaxFunc(url);
                }
            }else{
                return false;
            }

            //pjax方式(弃用)
            /*$(document).on('click', '.side-menu a', function(event) {
                var url=$(this).prop('href');
                var level=$(this).attr('level');
                //菜单
                var title='';
                var title2='';
                if(0==level){
                    title=$(this).find("span").text();
                }else{
                    title2=$(this).attr('bname');
                    title=$(this).text();
                }
                baseHeader2_pageTitle(title,level,title2);
                //跳转
                if(isNotBlank(url)){
                    //菜单url存储
                    global$frameUrl=url;
                    //跳转判断
                    if(-1!=url.indexOf(global$openWindow)){
                        url=url.split(global$openWindow).join("");
                        //框架页面
                        window.open(url);
                        return false;
                    }else{
                        //按钮清除
                        buttonRemove();
                        //普通
                        pjaxFunc(url);
                        $.pjax.click(event, {push:false,replace:false,container: global$frameContainer});
                    }
                }else{
                    return false;
                }
            })*/
        }

        /** 主体iframe跳转(用iframe或者其他异步的时候放开这个方法，同时放开leftbar中这个方法，暂时弃用) */
        function baseContainerIframe(url){
            //iframe跳转
            /*if(isNotBlank(url)){
                if(-1!=url.indexOf(global$openWindow)){
                    url=url.split(global$openWindow).join("");
                    //框架页面
                    window.open(url);
                }else{
                    //普通
                    $("#baseContainerIframe").prop('src',url);
                }
            }*/
            //异步加载跳转
            //ajaxHtml(url,'#baseFrame');
        }

    </script>
</body>
</html>
