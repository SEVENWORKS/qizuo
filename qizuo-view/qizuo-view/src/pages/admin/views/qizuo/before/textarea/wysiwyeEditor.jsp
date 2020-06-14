<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <div class="wysiwye-editor"></div>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        $('.wysiwye-editor').summernote({
            height: 200
        });
    })
    /** ********************************************** */
</script>
