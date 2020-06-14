<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area" style="height: 100%;width: 100%">
    <!-- 编辑器 -->
    <script id="container" name="content" type="text/plain">这里写你的初始化内容</script>
</div>

<script type="text/javascript" src="${staticPath}js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${staticPath}js/ueditor/ueditor.all.js"></script>
<!-- 执行js -->
<script type="text/javascript">
    globalJs(function(){
        var ue = UE.getEditor('container');
        ue.ready(function() {
            //this是当前创建的编辑器实例
            this.setOpt( {
                'initContent': '欢迎使用编辑器'
            });
        })
    })
    /** ********************************************** */
</script>
