<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <textarea class="form-control auto-size m-b-10"></textarea>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        $('textarea').autosize();
    })
    /** ********************************************** */
</script>
