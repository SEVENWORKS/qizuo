<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<!-- 父节点 -->
	<input type="hidden" name="parentId" value="${parentId}">
	<div class="row m-b-10">
		<h3 class="block-title m-b-5">菜单名称</h3>
		<input type="text" class="form-control" name="name" value="{{name==null?'':name}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">菜单跳转路径</h3>
		<input type="text" class="form-control" name="url" value="{{url==null?'':url}}" placeholder="举例:jump/system/sys/role">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">菜单主题</h3>
		<input type="text" class="form-control" name="theme" value="{{theme==null?'':theme}}" placeholder="举例:sa-side-home/sa-side-typography/sa-side-widget/sa-side-table/sa-side-form/sa-side-ui/sa-side-chart/sa-side-folder/sa-side-calendar/sa-side-page">
	</div>
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
            pjaxFunc('${jumpPath}system/sys/menu');
        },'55%');
        /** ************************************************************ */
        qData();
        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}system/menu/query',{baseId:'${baseId}'},function(data){
                    backResult(data,function(data){
                        if(isNotBlank(data)){
                            //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                            tplFunc(data);
                        }
                    })
                })
            }else{
                tplFunc();
            }
        }
        //添加或者修改
        function iuFunc(){
            if(formValid()){
                $.post('${modulePath}system/menu/iuDo',$('#dataContainer').serialize(),function(data){
                    backResultAlert(data,function(data){
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
