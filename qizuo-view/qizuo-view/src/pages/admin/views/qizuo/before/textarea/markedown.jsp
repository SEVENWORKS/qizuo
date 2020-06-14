<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <textarea class="markdown-editor"></textarea>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        $('.markdown-editor').markdown({
            autofocus:false,
            savable:false
        });
    })
    /** ********************************************** */
</script>
