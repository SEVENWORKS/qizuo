<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <ul class="ztree" id="tree">

    </ul>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        function ztree(list,treeContainer,name) {
            var setting = {
                data: {
                    simpleData: {
                        enable: true,//这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
                        rootPId: 0 //根节点代表的数据，默认Null
                    }
                },
                view: {
                    fontCss: {color: "#FFFFFF"},//字体颜色
                    showIcon: false //设置是否显示图标
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        //去掉背景色
                        $(".curSelectedNode").removeClass("curSelectedNode");
                    }
                }
            }
            //节点配置
            var zNodes = [];
            for (var o = 0; o < list.length; o++) {
                //id和pId是setting中data中simpleData的设定，name和open则是treeNode本身的属性设置
                var html = {id: list[o].baseId, pId: list[o].parentId, name: list[o][name], open: 1};
                zNodes.push(html);
            }
            //初始化
            return $.fn.zTree.init($(treeContainer), setting, zNodes);
        }

        //示例
        var list=[
            {baseId:1,parentId:1,name:'树'},
            {baseId:2,parentId:2,name:'树2'},
            {baseId:3,parentId:3,name:'树3'},
            {baseId:4,parentId:4,name:'树4'}
        ]
        ztree(list,'#tree','name');
    })
    /** ********************************************** */
</script>
