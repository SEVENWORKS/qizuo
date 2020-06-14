<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
    <div class="row m-b-10">
        <h3 class="block-title m-b-5">类型</h3>
        <input type="text" class="form-control" name="name" value="{{typeCd==null?'':typeCd}}" placeholder="Default">
    </div>
    <div class="row m-b-10">
        <h3 class="block-title m-b-5">创建人</h3>
        <input type="text" class="form-control" name="indexUrl" value="{{baseCreateUserId==null?'':baseCreateUserId}}" placeholder="Default">
    </div>
    <div class="row m-b-10">
        <h3 class="block-title m-b-5">创建时间</h3>
        <input type="text" class="form-control" name="jumpUrl" value="{{baseCreateTime==null?'':baseCreateTime}}" placeholder="Default">
    </div>
    <div class="row m-b-10">
        <h3 class="block-title m-b-5">创建IP</h3>
        <input type="text" class="form-control" name="jumpUrl" value="{{baseCreateIp==null?'':baseCreateIp}}" placeholder="Default">
    </div>
    <div class="row m-b-10">
        <h3 class="block-title m-b-5">日志内容</h3>
        <textarea class="form-control auto-size m-b-10" name="content" placeholder="Default">{{content==null?'':content}}</textarea>
    </div>
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        /** ************************************************************ */
        /** ************************************************************ */
        qData();
        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}system/log/query',{baseId:'${baseId}'},function(data){
                    backResult(data,function(data){
                        if(isNotBlank(data)){
                            //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                            tplFunc(data);
                            //扩展型(autosize.min.js，这款插件不仅仅在textarea上有体现)
                            if($('.auto-size')[0]) {
                                $('.auto-size').autosize();
                            }
                        }
                    })
                })
            }else{
                tplFunc();
                //扩展型(autosize.min.js，这款插件不仅仅在textarea上有体现)
                if($('.auto-size')[0]) {
                    $('.auto-size').autosize();
                }
            }

        }
        //添加或者修改
        //表单验证
        /** ************************************************************ */
    })
    /** ********************************************** */
</script>
