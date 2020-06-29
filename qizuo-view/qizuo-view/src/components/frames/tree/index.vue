<template>
  <div
    class="block-area"
    style="width: 15%; margin-top: 100px; margin-left: 10%;"
  >
    <ul class="ztree" ref="ztree" id="ztree"></ul>
  </div>
</template>

<script>
import "@static/js/frame/ztree/css/zTreeStyle.css";
import "@static/js/frame/ztree/js/jquery.ztree.core-3.5.min.js";
import "@static/js/frame/ztree/js/jquery.ztree.excheck-3.5.min.js";
export default {
  props: ["data"],
  methods: {
    //初始化ztree
    init() {
      const vm = this;
      //普通
      const setting = {
        data: {
          simpleData: {
            enable: true, //这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
            rootPId: 0, //根节点代表的数据，默认Null
          },
        },
        view: {
          fontCss: { color: "#FFFFFF" }, //字体颜色
          showIcon: false, //设置是否显示图标
        },
        callback: {
          onClick: function (event, treeId, treeNode) {
            //去掉背景色
            document
              .getElementsByClassName("curSelectedNode")[0]
              .classList.remove("curSelectedNode");
          },
        },
      };
      //节点配置
      var zNodes = [];
      for (var o = 0; o < this.data.length; o++) {
        //id和pId是setting中data中simpleData的设定，name和open则是treeNode本身的属性设置
        var html = {
          id: this.data[o].baseId,
          pId: this.data[o].parentId,
          name: this.data[o].name,
          open: 1,
          checked: this.data[o].checked,
        };
        zNodes.push(html);
      }
      //初始化
      return $.fn.zTree.init($("#ztree"), setting, zNodes);
    },
  },
};
</script>

<style scoped></style>
