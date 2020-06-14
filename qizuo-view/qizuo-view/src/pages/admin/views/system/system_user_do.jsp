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
		<select name="sexCd" class="select">
			<option value="0">男</option>
			<option value="1">女</option>
		</select>
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

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">角色</h3>
		<select name="roleIds" class="select2" multiple>

		</select>
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">qizuo</h3>
		<input type="text" class="form-control" name="outMutualKey" value="{{outMutualKey==null?'':outMutualKey}}" placeholder="填写...">
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
                            //slect插件初始化(select.min.js)
                            if($('.select')[0]) {
                                $('.select').selectpicker({
                                    noneSelectedText:'单选...'//设置没有任何选择时候的文字显示
                                });
                            }
                            //选择
                            if($('.select')[0]) {
                                var dataScopeCdsArr=isNotBlank(data.sexCd)?data.sexCd.split(","):null;
                                if(isNotBlank(dataScopeCdsArr)){
                                    //这个地方如果是多个初始化就放数组，如果是单个就放单个值就行了(bootstrap selectpicker)，记住一定要refresh
                                    $('.select').selectpicker('val',dataScopeCdsArr);
                                    $('.select').selectpicker('refresh');
                                }
                            }
                            //角色select
                            roleSelect(data.roleIds);
                        }
                    })
                })
            }else{
                tplFunc();
                //slect插件初始化(select.min.js)
                if($('.select')[0]) {
                    $('.select').selectpicker({
                        noneSelectedText:'单选...'//设置没有任何选择时候的文字显示
                    });
                }
                //角色select
                roleSelect();
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
        //获取角色select数据
		function roleSelect(sData){
			$.post('${modulePath}system/role/list',{},function(data){
                backResult(data,function(data){
                    if(isNotBlank(data)){
                        //填充
						var selectOptions="";
						for(var i=0;i<data.length;i++){
                            selectOptions+='<option value="'+data[i].baseId+'">'+data[i].name+'</option>';
						}
						$(".select2").append(selectOptions);

                        //select渲染
                        if($('.select2')[0]) {
                            $('.select2').selectpicker({
                                noneSelectedText:'多选...'//设置没有任何选择时候的文字显示
                            });
                        }

                        //数据回溯
						if(isNotBlank(sData)){
                            if($('.select2')[0]) {
                                var dataScopeCdsArr=isNotBlank(sData)?sData.split(","):null;
                                if(isNotBlank(dataScopeCdsArr)){
                                    //这个地方如果是多个初始化就放数组，如果是单个就放单个值就行了(bootstrap selectpicker)，记住一定要refresh
                                    $('.select2').selectpicker('val',dataScopeCdsArr);
                                    $('.select2').selectpicker('refresh');
                                }
                            }
						}
					}
				})
			})
        }
    })
    /** ********************************************** */
</script>
