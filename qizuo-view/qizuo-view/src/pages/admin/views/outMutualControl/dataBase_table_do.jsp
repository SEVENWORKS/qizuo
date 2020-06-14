<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<input type="hidden" name="conditions" id="conditions" value="">
	{{each $data}}
		<div class="row m-b-10">
			<h3 class="block-title m-b-5">{{$value.column_name}}</h3>
			<input type="text" class="form-control" id="{{$value.column_name}}" name="{{$value.column_name}}" value="" placeholder="填写...">
		</div>
	{{/each}}
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        /** ************************************************************ */
        //新增按钮(这个函数第二个参数可以传入复杂函数)
        buttonOne('保存',function(){
            iuFunc();
        },'45%');
        buttonOne('返回',function(){
            pjaxFunc('${jumpPath}system/dataBase');
        },'55%');
        /** ************************************************************ */
        //数据头
        //头字段查询
        qDataTitle();
        //查询数据
        function qDataTitle(){
            //只有存在的时候才去查找数据
            if(isNotBlank('${tName}')){
                //头名称
                $.post('${modulePath}outMutual/${tName}/qOutMutualDatabase',{sign:false},function(data){
                    backResult(data,function(data){
                        if(null!=data&&data.length>0){
                            //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                            tplFunc(data);

                            //更新数据查询
                            qData();
                        }
                    })
                })
            }
        }

        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}outMutual/${tName}/qOutMutual',{conditions:'base_id=${baseId}'},function(data){
                    backResult(data,function(data){
                        if(isNotBlank(data)){
                            data=data[0];
                            //填充数据
							for(var key in data){
							    $("#"+key).val(data[key]);
							}
                        }
                    })
                })
            }
        }

        //添加或者修改
        function iuFunc(){
            if(formValid()){
                //更新条件
				if(isNotBlank('${baseId}')){
				    $("#conditions").val("BASE_ID=\'${baseId}\'");
                }
                //操作
                $.post('${modulePath}outMutual/${tName}/iuOutMutual',$('#dataContainer').serialize(),function(data){
                    backResultAlert(data,function(data){
                        //刷新
                        f5();
                    })
                })
            }
        }

        //表单验证
        function formValid(){
            return true;
        }
        /** ************************************************************ */
    })
    /** ********************************************** */
</script>
