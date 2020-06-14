<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<!-- 有悬浮效果的表格 -->
<div class="block-area" id="tableHover" style="width: 75%">
	<%--<h3 class="block-title">Table with Hover Style</h3>--%>
	<div class="table-responsive overflow">
		<table class="table table-bordered table-hover tile" id="dataContainer">
			<thead>
			<tr>
				<th width="10%">序号</th>
				<th>菜单名称</th>
				<th>跳转路径</th>
			</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<!-- tree -->
<div class="block-area" style="width: 15%;margin-top: 100px;margin-left: 10%">
	<ul class="ztree" id="tree">

	</ul>
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
	<tr id="tr{{$index+1}}" onclick="buttonPanel('修改','updateDate(\'${jumpPath}system/menuDo?baseId={{$value.baseId}}\')','删除',
			'deleteDataReload({baseId:{{$value.baseId}}},\'${modulePath}system/menu/delete\')');">
		<td>{{$index+1}}</td>
		<td>{{$value.name}}</td>
		<td>{{$value.url}}</td>
	</tr>
	{{/each}}
	{{else}}
	<tr><td colspan="{{$imports.th_length}}" style="text-align: center">暂无数据</td></tr>
	{{/if}}
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
//父节点
        var baseId=0;
        var curLevel=0;
        /** ************************************************************ */
        //新增按钮(这个函数第二个参数可以传入复杂函数)
        buttonOne('新增',function(){
            if(curLevel==0){
                pjaxFunc('${jumpPath}system/menuDo?parentId='+baseId);
            }else{
                alert('暂时菜单只允许存在二级');
            }
        });
        /** ************************************************************ */
        //分页(传入获取分页数据的方法,可传分页size和分页no)
        pageHtml(qPage,10,1);
        //分页数据查询(都默认一个函数)
        function qPage(func,pageNo,pageSize){
            $.post('${modulePath}system/menu/page',{pageNo:pageNo,pageSize:pageSize,'baseId':baseId},function(data){
                backResult(data,function(data){
                    //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                    tplFuncTable(data.entitys);
                    //执行分页
                    func(data);
                })
            })
        }
        /** ************************************************************ */
        //ztree
        (function(){
            $.post('${modulePath}system/menu/list',{},function(data){
                backResult(data,function(data){
                    if(isNotBlank(data)){
                        var setting = {
                            data: {
                                simpleData: {
                                    enable: true,//这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
                                    rootPId: 0 //根节点代表的数据，默认Null
                                }
                            },
                            view: {
                                fontCss : {color:"#FFFFFF"},//字体颜色
                                showIcon: false //设置是否显示图标
                            },
                            callback : {
                                onClick : function(event, treeId, treeNode) {
                                    //去掉背景色
                                    $(".curSelectedNode").removeClass("curSelectedNode");
                                    //父节点改变
                                    baseId=treeNode.id;
                                    //查询数据
                                    pageHtml(qPage,10,1);
                                    //当前节点所处等级
                                    curLevel=treeNode.level;
                                }
                            }
                        }
                        //加载树
                        treeMenu(data,'name',setting);
                    }
                })
            })
        })()
    })
    /** ********************************************** */
</script>
