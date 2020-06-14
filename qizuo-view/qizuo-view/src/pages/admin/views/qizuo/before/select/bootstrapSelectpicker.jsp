<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <select class="select m-b-10" multiple>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
    </select>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        $('select').selectpicker();
    })
    /** ********************************************** */
</script>
