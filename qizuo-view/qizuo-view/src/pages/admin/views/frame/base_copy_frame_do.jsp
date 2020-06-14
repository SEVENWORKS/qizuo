<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<div class="row m-b-10">
		<h3 class="block-title m-b-5">登录名</h3>
		<input type="text" class="form-control" name="userName" value="{{userName==null?'':userName}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">密码</h3>
		<input type="text" class="form-control" name="passWord" value="{{passWord==null?'':passWord}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">姓名</h3>
		<input type="text" class="form-control" name="name" value="{{name==null?'':name}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">性别</h3>
		<input type="text" class="form-control" name="sexCd" value="{{sexCd==null?'':sexCd}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">身份证</h3>
		<input type="text" class="form-control" name="idCard" value="{{idCard==null?'':idCard}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">电话</h3>
		<input type="text" class="form-control" name="phone" value="{{phone==null?'':phone}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">邮件</h3>
		<input type="text" class="form-control" name="email" value="{{email==null?'':email}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">住址</h3>
		<input type="text" class="form-control" name="address" value="{{address==null?'':address}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">照片</h3>
		<input type="text" class="form-control" name="photo" value="{{photo==null?'':photo}}" placeholder="填写...">
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
            pjaxFunc('${jumpPath}system/user');
        },'55%');
        /** ************************************************************ */
        qData();
        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}system/user/query',{baseId:'${baseId}'},function(data){
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
                $.post('${modulePath}system/user/iuDo',$('#dataContainer').serialize(),function(data){
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
