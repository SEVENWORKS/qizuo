<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 选择发送人区域 -->
<div id="sendObj" style="display: none" class="block-area">
	<!-- 单选开关 -->
	<div class="row m-b-10 text-center">
		<div class="make-switch switch-mini" id="mySwitch" style="height: 25px" data-on-label="人员" data-off-label="类别">
			<input type="checkbox">
		</div>
	</div>
	<!-- 单个 -->
	<div class="row m-b-10 sendObj_left" style="display: none">
		<h3 class="block-title m-b-5">请选择发送人</h3>
		<select id="sendUserId" class="select" multiple>

		</select>
	</div>
	<!-- 某一类 -->
	<div class="row m-b-10 sendObj_right">
		<h3 class="block-title m-b-5">类别</h3>
		<input type="text" class="form-control" id="type" value="" placeholder="填写...">
	</div>
	<div class="row m-b-10 sendObj_right">
		<h3 class="block-title m-b-5">类别细分</h3>
		<input type="text" class="form-control" id="sendTypeId" value="" placeholder="填写...">
	</div>
	<div class="row m-b-10 text-center">
		<button class="btn btn-sm btn-alt" type="button">确定</button>
	</div>
</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<!-- 单个人 -->
	<input type="hidden" name="sendUserIds" value="{{sendUserId==null?'':sendUserId}}">
	<!-- 某一类 -->
	<input type="hidden" name="type" value="{{type==null?'':type}}">
	<input type="hidden" name="sendTypeId" value="{{sendTypeId==null?'':sendTypeId}}">
	<div class="row m-b-10">
		<h3 class="block-title m-b-5">消息</h3>
		<textarea class="form-control auto-size m-b-10" name="content" placeholder="请填写...">{{content==null?'':content}}</textarea>
	</div>
</script>

<!-- 按钮开关 -->
<script src="${staticPath}js/frame/toggler.min.js"></script>
<!-- 执行js -->
<script>
    globalJs(function(){
		/** ************************************************************ */
		//新增按钮(这个函数第二个参数可以传入复杂函数)
		if(!isNotBlank('${baseId}')){
			buttonOne('发送',function(){
				//选择发送对象
				sendObj();
			},'45%');
			buttonOne('取消',function(){
				//去掉上个页面多余的元素
				buttonRemove();
				$(global$frameContainer).empty();
			},'55%');
		}
		/** ************************************************************ */
		qData();
		//查询数据
		function qData(){
			//只有更新的时候才去查找数据
			if(isNotBlank('${baseId}')){
				$.post('${modulePath}system/msg/query',{baseId:'${baseId}'},function(data){
					backResult(data,function(data){
						if(isNotBlank(data)){
							//模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
							tplFunc(data);
							//变成已读
							if(data.isRead==0){
								$.post('${modulePath}system/msg/uRead',{baseId:'${baseId}'},function(data){
									backResult(data,function(){
										//数量减一
										$(".drawer-toggle i").eq(1).text(parseInt($(".drawer-toggle i").eq(1).text())-1);
									})
								})
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
		function iuFunc(){
			if(formValid()){
				$.post('${modulePath}system/msg/iuDo',$('#dataContainer').serialize(),function(data){
					backResultAlert(data,function(data){
						window.location.reload();
					})
				})
			}
		}
		//表单验证
		function formValid(){
			return true;
		}
		/** ************************************************************ */
		//选择发送对象
		function sendObj(){
			modelBase('#sendObj','40%','auto');
		}
		//确定发送对象
		$("#sendObj button").on("click",function(){
			//塞入数据
			$('input[name="sendUserIds"]').val($('#sendUserId').val());
			$('input[name="type"]').val($('#type').val());
			$('input[name="sendTypeId"]').val($('#sendTypeId').val());
			//发送
			iuFunc();
			//关闭窗口
			layerClose();
		})
		//开关控制
		checkSwitch('#mySwitch',function(){
			$(".sendObj_left").show();
			$(".sendObj_right").hide();
		},function(){
			$(".sendObj_left").hide();
			$(".sendObj_right").show();
		});
		//获取人员列表
		$.post('${modulePath}system/user/list',{},function(data){
			backResult(data,function(bt){
				var html='';
				//人员
				if(isNotBlank(bt)){
					for(var i=0;i<bt.length;i++){
						html+='<option value="'+bt[i].baseId+'">'+bt[i].name+'</option>';
					}
				}else{
					html='<option value="">暂无更多人员</option>';
				}
				$("#sendUserId").html(html);
				//slect插件初始化(select.min.js)
				if($('.select')[0]) {
					$('.select').selectpicker({
						//设置没有任何选择时候的文字显示
						noneSelectedText:'多选...'
					});
				}
			})
		})
    })
    /** ********************************************** */
</script>
