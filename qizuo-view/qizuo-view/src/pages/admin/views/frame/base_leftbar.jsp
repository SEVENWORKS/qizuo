<!-- 左侧菜单 -->
<ul class="list-unstyled side-menu">
    <%--<li>
        <a class="sa-side-home" href="index.html">
            <span class="menu-item">Dashboard</span>
        </a>
    </li>
    <li>
        <a class="sa-side-typography" href="typography.html">
            <span class="menu-item">Typography</span>
        </a>
    </li>
    <li>
        <a class="sa-side-widget" href="content-widgets.html">
            <span class="menu-item">Widgets</span>
        </a>
    </li>
    <li>
        <a class="sa-side-table" href="tables.html">
            <span class="menu-item">Tables</span>
        </a>
    </li>
    <li class="dropdown">
        <a class="sa-side-form" href="">
            <span class="menu-item">Form</span>
        </a>
        <ul class="list-unstyled menu-item">
            <li><a href="form-elements.html">Basic Form Elements</a></li>
            <li><a href="form-components.html">Form Components</a></li>
            <li><a href="form-examples.html">Form Examples</a></li>
            <li><a href="form-validation.html">Form Validation</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a class="sa-side-ui" href="">
            <span class="menu-item">User Interface</span>
        </a>
        <ul class="list-unstyled menu-item">
            <li><a href="buttons.html">Buttons</a></li>
            <li><a href="labels.html">Labels</a></li>
            <li><a href="images-icons.html">Images &amp; Icons</a></li>
            <li><a href="alerts.html">Alerts</a></li>
            <li><a href="media.html">Media</a></li>
            <li><a href="components.html">Components</a></li>
            <li><a href="other-components.html">Others</a></li>
        </ul>
    </li>
    <li>
        <a class="sa-side-chart" href="charts.html">
            <span class="menu-item">CHARTS</span>
        </a>
    </li>
    <li>
        <a class="sa-side-folder" href="file-manager.html">
            <span class="menu-item">File Manager</span>
        </a>
    </li>
    <li class="active">
        <a class="sa-side-calendar" href="calendar.html">
            <span class="menu-item">Calendar</span>
        </a>
    </li>
    <li class="dropdown">
        <a class="sa-side-page" href="">
            <span class="menu-item">Pages</span>
        </a>
        <ul class="list-unstyled menu-item">
            <li><a href="list-view.html">List View</a></li>
            <li><a href="profile-page.html">Profile Page</a></li>
            <li><a href="messages.html">Messages</a></li>
            <li><a href="login.html">Login</a></li>
            <li><a href="404.jsp">404 Error</a></li>
        </ul>
    </li>--%>
</ul>
<script>
    /**
     * 暂时几种theme
     * 1.sa-side-home
     * 2.sa-side-typography
     * 3.sa-side-widget
     * 4.sa-side-table
     * 5.sa-side-form
     * 6.sa-side-ui
     * 7.sa-side-chart
     * 8.sa-side-folder
     * 9.sa-side-calendar
     * 10.sa-side-page
     */
    /** 左侧菜单列表(只支持二级菜单) */
    function baseLeftbar_menu(menuList){
        if(isNotBlank(menuList)){
            var html='';

            //一级菜单循环体
            for(var i=0;i<menuList.length;i++){
                //参数
                var name=isNotBlank(menuList[i].name)?menuList[i].name:"";
                var jumpUrl=isNotBlank(menuList[i].url)?'${pageContext.request.contextPath}/'+menuList[i].url:"";
                var theme=isNotBlank(menuList[i].theme)?menuList[i].theme:"sa-side-form";
                //html baseContainerIframe(\''+jumpUrl+'\');
                html+='<li class="dropdown">\n' +
                    '     <a class="'+theme+'" href="#" level="0" onclick="pjax(\''+jumpUrl+'\',this)">\n' +
                    '         <span class="menu-item">'+name+'</span>\n' +
                    '     </a>\n' +
                    '     <ul class="list-unstyled menu-item">\n';

                //二级菜单循环体
                if(isNotBlank(menuList[i].menuPoJos)){
                    for(var j=0;j<menuList[i].menuPoJos.length;j++){
                        //参数
                        var name2=isNotBlank(menuList[i].menuPoJos[j].name)?menuList[i].menuPoJos[j].name:"";
                        var jumpUrl2=isNotBlank(menuList[i].menuPoJos[j].url)?'${pageContext.request.contextPath}/'+menuList[i].menuPoJos[j].url:"";
                        //html baseContainerIframe(\''+jumpUrl2+'\');
                        html+='<li><a href="#" level="1" bname="'+name+'" onclick="pjax(\''+jumpUrl2+'\',this)">'+name2+'</a></li>\n';
                    }
                }

                html+='    </ul>\n' +
                    '</li>';
            }

            $(".side-menu").append(html);
        }
    }
</script>
