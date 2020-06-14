<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<!-- 有悬浮效果的表格 -->
<div class="block-area" id="tableHover">
	<%--<h3 class="block-title">Table with Hover Style</h3>--%>
	<div class="table-responsive overflow">
		<table class="table table-bordered table-hover tile" id="dataContainer" >
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th>名称</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	{{if null!=$data&&$data.length>0}}
	{{each $data}}
	<tr id="tr{{$index+1}}" onclick="buttonPanel('查看','updateDate(\'${jumpPath}system/dataBaseDo?tName={{$value.table_name}}\')');">
		<td>{{$index+1}}</td>
		<td>{{$value.table_name}}</td>
	</tr>
	{{/each}}
	{{else}}
	<tr><td colspan="{{$imports.th_length}}" style="text-align: center">暂无数据</td></tr>
	{{/if}}
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
		/** ************************************************************ */
		/** ************************************************************ */
        qData();
        //查询数据
        function qData(){
			$.post('${modulePath}outMutual/qizuo/qOutMutualDatabase',{sign:true},function(data){
				backResult(data,function(data){
					//模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
					tplFuncTable(data);
				})
			})
        }
		/** ************************************************************ */
    })
    /** ********************************************** */
</script>
