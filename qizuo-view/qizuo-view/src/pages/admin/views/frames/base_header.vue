<!-- 最上层一行的头 -->
<!-- 控制最左侧菜单的总菜单 -->
<a href="" id="menu-toggle"></a>
<!-- 项目名称 -->
<a class="logo pull-left" href="" onclick="window.location.reload()">上辈子是条鱼</a>

<!-- 这一行剩下的右侧部分 -->
<div class="media-body">
    <div class="media" id="top-menu">
        <!-- 邮件图标 -->
        <div class="pull-left tm-icon">
            <a data-drawer="messages" class="drawer-toggle" href="">
                <i class="sa-top-message"></i>
                <i class="n-count animated">0</i>
                <span>Messages</span>
            </a>
        </div>
        <!-- 例子：更新图标 -->
        <%--<div class="pull-left tm-icon">
            <a data-drawer="notifications" class="drawer-toggle" href="">
                <i class="sa-top-updates"></i>
                <i class="n-count animated">9</i>
                <span>Updates</span>
            </a>
        </div>--%>

        <!-- 日期 -->
        <div id="time" class="pull-right">
            <span id="hours"></span>
            :
            <span id="min"></span>
            :
            <span id="sec"></span>
        </div>

        <!-- 搜索 -->
        <div class="media-body">
            <input type="text" class="main-search">
        </div>
    </div>
</div>

<script>
    /** 消息初步加载函数 */
    function baseHeader_msg(msgs){
        if(isNotBlank(msgs)){
            //第一部分：数量
            var count=0;
            if(isNotBlank(msgs)){
                for(var i=0;i<msgs.length;i++){
                    //未读
                    if(msgs[i].isRead==0){
                        count++;
                    }
                }
            }
            $(".drawer-toggle i").eq(1).text(count);
            //第二部分
            baseHeader2_msglist(msgs);
        }
    }

    /** 头搜索函数 */
    function baseHeader_mainSearch(func){
        var mainSearchVal=$(".main-search").val();
        if(isFunction(func)){
            //自定义搜索
            func(mainSearchVal);
        }else{
            //百度搜索

        }
    }
</script>