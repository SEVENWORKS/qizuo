<%@ include file="../frame/base_tags.jsp"%>
<!-- 内容区域 -->
<form class="block-area" id="dataContainer" style="width: 75%">

</form>

<!-- ztree -->
<div class="block-area" style="width: 15%;margin-top: 100px;margin-left: 10%">
	<ul class="ztree" id="tree">

	</ul>
</div>

<!-- 数据模板 -->
<script id="baseTpl" type="text/html">
	<!-- 主键 -->
	<input type="hidden" name="baseId" value="${baseId}">
	<!-- 权限集合 -->
	<input type="hidden" name="menuIds" value="{{menuIds==null?'':menuIds}}"/>
	<div class="row m-b-10">
		<h3 class="block-title m-b-5">角色名称</h3>
		<input type="text" class="form-control" name="name" value="{{name==null?'':name}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">首页路径</h3>
		<input type="text" class="form-control" name="indexUrl" value="{{indexUrl==null?'':indexUrl}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">跳转路径</h3>
		<input type="text" class="form-control" name="jumpUrl" value="{{jumpUrl==null?'':jumpUrl}}" placeholder="填写...">
	</div>

	<div class="row m-b-10">
		<h3 class="block-title m-b-5">数据权限</h3>
		<select name="dataScopeCds" class="select" multiple>
			<option value="0">增</option>
			<option value="1">删</option>
			<option value="2">查</option>
			<option value="3">改</option>
		</select>
	</div>
</script>
<!-- 执行js -->
<script>
    globalJs(function(){
        //ztree树对象
        var treeObj;
        /** ************************************************************ */
        //新增按钮(这个函数第二个参数可以传入复杂函数)
        buttonOne('保存',function(){
            iuFunc();
        },'45%');
        buttonOne('返回',function(){
            pjaxFunc('${jumpPath}system/role');
        },'55%');
        /** ************************************************************ */
        qData();
        //查询数据
        function qData(){
            //只有更新的时候才去查找数据
            if(isNotBlank('${baseId}')){
                $.post('${modulePath}system/role/query',{baseId:'${baseId}'},function(data){
                    backResult(data,function(data){
                        if(isNotBlank(data)){
                            //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
                            tplFunc(data);
                            //slect插件初始化(select.min.js)
                            if($('.select')[0]) {
                                $('.select').selectpicker({
                                    noneSelectedText:'多选...'//设置没有任何选择时候的文字显示
                                });
                            }
                            //树
                            ztree(data.menuIds);
                            //多选
                            if($('.select')[0]) {
                                var dataScopeCdsArr=isNotBlank(data.dataScopeCds)?data.dataScopeCds.split(","):null;
                                if(isNotBlank(dataScopeCdsArr)){
                                    //这个地方如果是多个初始化就放数组，如果是单个就放单个值就行了(bootstrap selectpicker)，记住一定要refresh
                                    $('.select').selectpicker('val',dataScopeCdsArr);
                                    $('.select').selectpicker('refresh');
                                }
                            }
                        }
                    })
                })
            }else{
                //模板
                tplFunc();
                //slect插件初始化(select.min.js)
                if($('.select')[0]) {
                    $('.select').selectpicker({
                        noneSelectedText:'多选...'//设置没有任何选择时候的文字显示
                    });
                }
                //树
                ztree();
            }
        }
        //添加或者修改
        function iuFunc(){
            if(formValid()){
                //菜单id集合
                if(isNotBlank(treeObj)){
                    var menuIds='';
                    $(treeObj.getCheckedNodes(true)).each(function(){
                        menuIds+=this.id+",";
                    })
                    if(isNotBlank(menuIds)){
                        $("input[name='menuIds']").val(menuIds.substring(0,menuIds.length-1));
                    }
                }
                //保存
                $.post('${modulePath}system/role/iuDo',$('#dataContainer').serialize(),function(data){
                    backResultAlert(data,function(){
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
        //ztree
        function ztree(dataString){
            $.post('${modulePath}system/menu/list',{},function(data){
                backResult(data,function(data){
                    if(isNotBlank(data)){
                        var setting = {
                            check: {
                                enable: true,//是否开启check或者radio
                                chkStyle: "checkbox",//设置选择类型是check还是radio
                                autoCheckTrigger: false,//设置事件是否自动关联到下方子节点
                                chkboxType: { "Y": "p", "N": "p" }//Y 属性定义 checkbox 被勾选后的情况；N 属性定义 checkbox 取消勾选后的情况； "p" 表示操作会影响父级节点；"s" 表示操作会影响子级节点。
                            },
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
                                },
                                onCheck : function(event, treeId, treeNode) {

                                }
                            }
                        }
                        //加载树
                        treeObj=treeMenu(data,'name',setting,null,dataString);
                    }
                })
            })
        }
    })
    /** ********************************************** */
</script>
