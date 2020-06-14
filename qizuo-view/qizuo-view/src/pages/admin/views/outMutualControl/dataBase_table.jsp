<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<!-- 有悬浮效果的表格 -->
<div class="block-area" id="tableHover">
	<%--<h3 class="block-title">Table with Hover Style</h3>--%>
	<div class="table-responsive overflow">
		<table class="table table-bordered table-hover tile" id="dataContainer">
			<thead>
				<tr>
					<th width="5%">序号</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<!-- 分页 -->
<div class="block-area">
	<div class="row">
		<div id="pageContainer" class="col-md-12 text-center">
			<ul class="pagination" id="page_ul">

			</ul>
		</div>
	</div>
</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	{{if null!=$data&&$data.length>0}}
	{{each $data}}
	<tr id="tr{{$index+1}}" onclick="buttonPanel('修改','updateDate(\'${jumpPath}system/dataBaseTableDo?baseId={{$value.BASE_ID}}&tName=${tName}\')','删除',
			'deleteData({conditions:\'BASE_ID={{$value.BASE_ID}}\',really:false},\'${modulePath}outMutual/${tName}/dOutMutual\',\'#tr{{$index+1}}\')');">
		<td>{{$index+1}}</td>
		{{each $value as item2 index2}}
			{{if index2!='BASE_ID'}}
			<td>{{item2}}</td>
			{{/if}}
		{{/each}}
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
		//头字段查询
        qData();
        //查询数据
        function qData(){
            //只有存在的时候才去查找数据
            if(isNotBlank('${tName}')){
                //头名称
                $.post('${modulePath}outMutual/${tName}/qOutMutualDatabase',{sign:false},function(data){
                    backResult(data,function(data){
						if(null!=data&&data.length>0){
                            //头名称数据
						    var title_th="";
						    for(var i=0;i<data.length;i++){
								title_th+="<th>"+data[i].column_name+"</th>";
							}
                            $("#dataContainer thead tr").append(title_th);

							//下面数据
                            //分页(传入获取分页数据的方法,可传分页size和分页no)
                            pageHtml(qPage,10,1);
                            //分页数据查询(都默认一个函数)
                            function qPage(func,pageNo,pageSize){
                                $.post('${modulePath}outMutual/${tName}/qOutMutual',{pageNo:pageNo,pageSize:pageSize},function(data){
                                    backResult(data,function(data){
                                        //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                                        tplFuncTable(data.entitys);
                                        //执行分页
                                        func(data);
                                    })
                                })
                            }
						}else{
                            tplFuncTable();
                        }
                    })
                })
            }else{
                tplFuncTable();
            }
        }

		/** ************************************************************ */
    })
    /** ********************************************** */
</script>
