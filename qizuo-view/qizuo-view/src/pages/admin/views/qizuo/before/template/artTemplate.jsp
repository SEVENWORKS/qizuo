<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">

</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
    {{if null!=$data&&$data.length>0}}
    {{each $data}}
    <tr id="tr{{$index+1}}" onclick="buttonPanel('修改','updateDate(\'${jumpPath}system/sys/userDo?baseId={{$value.baseId}}\')','删除',
            'deleteData({baseId:{{$value.baseId}}},\'${modulePath}system/user/delete\',\'#tr{{$index+1}}\')');">
        <td>{{$index+1}}</td>
        <td>{{$value.name}}</td>
        <td>{{$value.sexCd}}</td>
        <td>{{$value.idCard}}</td>
    </tr>
    {{/each}}
    {{else}}
    <tr><td colspan="{{$imports.th_length}}" style="text-align: center">暂无数据</td></tr>
    {{/if}}
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        var tplHtml=template(baseTpl, data);
        //模板添加
        $(container).append(tplHtml);
    })
    /** ********************************************** */
</script>
