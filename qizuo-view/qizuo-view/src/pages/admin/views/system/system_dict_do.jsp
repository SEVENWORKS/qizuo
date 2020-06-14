<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer">

</form>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<!-- list -->
	<div class="block-area m-b-10 m-l-15 m-r-15">
		<table class="table table-bordered table-hover tile m-b-10">
			<thead>
				<tr id="templateDom" style="display: none">
					<td>
						<input type="text" class="form-control" name="dictItemPoJos.value" value="" placeholder="填写...">
					</td>
					<td>
						<input type="text" class="form-control" name="dictItemPoJos.label" value="" placeholder="填写...">
					</td>
					<td>
						<button class="btn btn-alt btn-sm" onclick="removeDataTr(this)" type="button">删除</button>
					</td>
				</tr>

				<tr>
					<th>值</th>
					<th>名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="dataDetailContainer">
			{{if null!=$data.dictItemPoJos&&$data.dictItemPoJos.length>0}}
				{{each $data.dictItemPoJos}}
					<tr>
						<td>
							<input type="text" class="form-control" name="dictItemPoJos.value" value="{{$value.value==null?'':$value.value}}" placeholder="填写...">
						</td>
						<td>
							<input type="text" class="form-control" name="dictItemPoJos.label" value="{{$value.label==null?'':$value.label}}" placeholder="填写...">
						</td>
						<td>
							<button class="btn btn-alt btn-sm" onclick="removeDataTr(this)" type="button">删除</button>
						</td>
					</tr>
				{{/each}}
			{{/if}}
			</tbody>
		</table>
	</div>

	<!-- button -->
	<div class="row m-b-10 m-l-15 m-r-15 text-center">
		<button class="btn btn-alt btn-sm" onclick="addDataTr()" type="button">添加</button>
	</div>

	<!-- main -->
	<div class="row m-b-10">
		<h3 class="block-title m-b-5">字典值</h3>
		<input type="text" class="form-control" name="value" value="{{value==null?'':value}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">字典名称</h3>
		<input type="text" class="form-control" name="label" value="{{label==null?'':label}}" placeholder="填写...">
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
            pjaxFunc('${jumpPath}system/dict');
        },'55%');
        /** ************************************************************ */
        qData();
        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}system/dict/query',{baseId:'${baseId}'},function(data){
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
                //List对象参数的时候需要对应下标
                formListSer();
                //保存
                $.post('${modulePath}system/dict/iuDo',$('#dataContainer').serialize(),function(data){
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
