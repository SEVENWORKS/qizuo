<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">

</div>
<!-- 数据模板 -->
<script id="demo" type="text/html">
    <div style="margin-bottom:10px;">
    　　<span>${ID}</span>
    　　<span style="margin-left:10px;">{{= Name}}</span>
    　　<span style="margin-left:10px;">${Number(Num)+1}</span>
    　　<span style="margin-left:10px;">${Status}</span>
    </div>
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        var users = [{ ID: 'think8848', Name: 'Joseph Chan', Num: '1', Status: 1 }, { ID: 'aCloud', Name: 'Mary Cheung', Num: '2'}];
        $("#demo").tmpl(users).appendTo('#div_demo');
    })
    /** ********************************************** */
</script>
