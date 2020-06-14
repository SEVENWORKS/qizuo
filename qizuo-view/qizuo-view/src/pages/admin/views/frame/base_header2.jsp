<!-- 邮件图标点击头 -->
<div id="messages" class="tile drawer animated">
    <div class="listview narrow">
        <div class="media">
            <a href="#" onclick="pjaxFunc('${pageContext.request.contextPath}/jump/system/msgDo');">Send a New Message</a>
            <span class="drawer-close">&times;</span>
            
        </div>
        <div class="overflow" style="height: 254px">
            <%--<div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/1.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Nadin Jackson - 2 Hours ago</small><br>
                    <a class="t-overflow" href="">Mauris consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/2.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">David Villa - 5 Hours ago</small><br>
                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/3.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Harris worgon - On 15/12/2013</small><br>
                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/4.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Mitch Bradberry - On 14/12/2013</small><br>
                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/1.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Nadin Jackson - On 15/12/2013</small><br>
                    <a class="t-overflow" href="">Ipsum wintoo consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/2.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">David Villa - On 16/12/2013</small><br>
                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/3.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Harris worgon - On 17/12/2013</small><br>
                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/4.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Mitch Bradberry - On 18/12/2013</small><br>
                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/5.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Wendy Mitchell - On 19/12/2013</small><br>
                    <a class="t-overflow" href="">Integer a eros dapibus, vehicula quam accumsan, tincidunt purus</a>
                </div>
            </div>--%>
        </div>
        <%--<div class="media text-center whiter l-100">
            <a href=""><small>VIEW ALL</small></a>
        </div>--%>
    </div>
</div>

<!-- 例子：更新图标点击头 -->
<%--<div id="notifications" class="tile drawer animated">
    <div class="listview narrow">
        <div class="media">
            <a href="">Notification Settings</a>
            <span class="drawer-close">&times;</span>
        </div>
        <div class="overflow" style="height: 254px">
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/1.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Nadin Jackson - 2 Hours ago</small><br>
                    <a class="t-overflow" href="">Mauris consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/2.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">David Villa - 5 Hours ago</small><br>
                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/3.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Harris worgon - On 15/12/2013</small><br>
                    <a class="t-overflow" href="">Maecenas venenatis enim condimentum ultrices fringilla. Nulla eget libero rhoncus, bibendum diam eleifend, vulputate mi. Fusce non nibh pulvinar, ornare turpis id</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/4.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Mitch Bradberry - On 14/12/2013</small><br>
                    <a class="t-overflow" href="">Phasellus interdum felis enim, eu bibendum ipsum tristique vitae. Phasellus feugiat massa orci, sed viverra felis aliquet quis. Curabitur vel blandit odio. Vestibulum sagittis quis sem sit amet tristique.</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/1.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">Nadin Jackson - On 15/12/2013</small><br>
                    <a class="t-overflow" href="">Ipsum wintoo consectetur urna nec tempor adipiscing. Proin sit amet nisi ligula. Sed eu adipiscing lectus</a>
                </div>
            </div>
            <div class="media">
                <div class="pull-left">
                    <img width="40" src="${pageContext.request.contextPath}/static/img/frame/profile-pics/2.jpg" alt="">
                </div>
                <div class="media-body">
                    <small class="text-muted">David Villa - On 16/12/2013</small><br>
                    <a class="t-overflow" href="">Suspendisse in purus ut nibh placerat Cras pulvinar euismod nunc quis gravida. Suspendisse pharetra</a>
                </div>
            </div>
        </div>
        <div class="media text-center whiter l-100">
            <a href=""><small>VIEW ALL</small></a>
        </div>
    </div>
</div>--%>

<!-- 标题 -->
<ol class="breadcrumb hidden-xs">
    <li class="active"><a href="#">首页</a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
</ol>

<!-- 目录 -->
<h4 class="page-title">首页</h4>

<script>
    /** 目录和标题实时刷新函数(暂时只支持三级目录) */
    function baseHeader2_pageTitle(title,level,title2){
        //空值处理
        title=isNotBlank(title)?title:"";
        level=isNotBlank(level)?level:"";
        title2=isNotBlank(title2)?title2:"";
        var lis=$(".breadcrumb").find("li");
        //主菜单点击处理
        if(0==level){
            lis.eq(1).find("a").text("");
            lis.eq(2).find("a").text("");
        }else if(1==level){
            //右侧文字处理
            lis.eq(0).find("a").text(title2);
        }
        //右侧文字处理
        lis.eq(level).find("a").text(title);
        //左侧文字处理
        $(".page-title").text(title);
    }

    /** 消息深度加载(列表)函数 */
    function baseHeader2_msglist(msgs){
        if(isNotBlank(msgs)){
            var html='';

            //循环
            for(var i=0;i<msgs.length;i++){
                //参数
                var photo=isNotBlank(msgs[i].baseCreateUser)&&isNotBlank(msgs[i].baseCreateUser.photo)?msgs[i].baseCreateUser.photo:"${pageContext.request.contextPath}/static/img/frame/profile-pics/1.jpg";
                var name=isNotBlank(msgs[i].baseCreateUserNm)?msgs[i].baseCreateUserNm:"";
                var content=isNotBlank(msgs[i].content)?msgs[i].content:"";
                //如果有信息跳转路径就跳，如果没有默认跳转到默认的消息页面
                var href=isNotBlank(msgs[i].jumpUrl)?('${pageContext.request.contextPath}/'+msgs[i].jumpUrl):("${pageContext.request.contextPath}/jump/system/msgDo?baseId="+msgs[i].baseId);
                //html
                html+='<div class="media" id="msgTr'+i+'">\n' +
                    '     <div class="pull-left">\n' +
                    '           <img width="40" src="'+photo+'" alt="no photo">\n' +
                    '     </div>\n' +
                    '     <div class="media-body">\n' +
                    '           <small class="text-muted">'+name+'</small><br>\n' +
                    '           <a class="t-overflow" href="#" onclick="baseHeader2_msglist_click(\''+href+'\','+msgs[i].baseId+',\'#msgTr'+i+'\')">'+content+'</a>\n' +
                    '     </div>\n' +
                    '</div>';
            }

            $("#messages .overflow").append(html);
        }
    }

    /** 消息点击处理函数 */
    function baseHeader2_msglist_click(url,baseId,id){
        buttonPanel('阅读','updateDate(\''+url+'\')','删除',
            'deleteData({baseId:'+baseId+'},\'${pageContext.request.contextPath}/module/system/msg/delete\',\''+id+'\')');
    }
</script>