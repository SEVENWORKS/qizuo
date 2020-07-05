<template>
  <div>
    <!-- 内容区域 -->
    <qz-table :header="header" :data="data" @doClick="doClick"></qz-table>
    <!--<tr-->
    <!--id="tr{{index+1}}"-->
    <!--@click="-->
    <!--buttonPanel(-->
    <!--'修改',-->
    <!--'updateDate(\'${jumpPath}base/menuDo?baseId={{$value.baseId}}\')',-->
    <!--'删除',-->
    <!--'deleteDataReload({baseId:{{$value.baseId}}},\'${modulePath}base/menu/delete\')'-->
    <!--)-->
    <!--"-->
    <!--&gt;-->
    <!--<td>{{ index + 1 }}</td>-->
    <!--<td>{{ value.name }}</td>-->
    <!--<td>{{ value.url }}</td>-->
    <!--</tr>-->
    <!-- tree -->
    <qz-tree :data="treeData" ref="ztree"></qz-tree>
    <!-- 分页 -->
    <qz-page
      @pageChange="queryData"
      :pageCount="pageData.totalCount"
      ref="page"
    ></qz-page>
  </div>
</template>
<!-- 执行js -->
<script>
export default {
  data() {
    return {
      header: ["菜单名称", "跳转路径"], //表格头
      data: [
        {
          dd: 11,
        },
      ], //表格体
      doClick: [
        {
          name: "",
          dofunc: () => {},
        },
      ], //表格操作
      treeData: [
        { name: "hehe", baseId: "11", parentId: "0" },
        { name: "hehe", baseId: "2", parentId: "0" },
      ], //树状数据
      pageData: {
        totalCount: 20,
      }, //分页数据
    };
  },
  components: {},
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.queryTree();
      vm.queryData();
    }); //跳转
  },
  mounted() {
    this.$refs.ztree.init();
  },
  methods: {
    queryTree() {
      this.pageData.totalCount += 10;
      //ztree
      //   $.post("${modulePath}base/menu/list", {}, function (data) {
      //     backResult(data, function (data) {
      //       if (isNotBlank(data)) {
      //         var setting = {
      //           data: {
      //             simpleData: {
      //               enable: true, //这个开启代表简单参数的搭配，即无需再使用json嵌套形式，直接用数组对象list就行
      //               rootPId: 0, //根节点代表的数据，默认Null
      //             },
      //           },
      //           view: {
      //             fontCss: { color: "#FFFFFF" }, //字体颜色
      //             showIcon: false, //设置是否显示图标
      //           },
      //           callback: {
      //             onClick: function (event, treeId, treeNode) {
      //               //去掉背景色
      //               $(".curSelectedNode").removeClass("curSelectedNode");
      //               //父节点改变
      //               baseId = treeNode.id;
      //               //查询数据
      //               pageHtml(this.qPage, 10, 1);
      //               //当前节点所处等级
      //               curLevel = treeNode.level;
      //             },
      //           },
      //         };
      //         //加载树
      //         treeMenu(data, "name", setting);
      //       }
      //     });
      //   });
      // },
    },
    queryData(pageNo) {
      // //分页数据查询(都默认一个函数)
      // qPage(func, pageNo, pageSize) {
      //     $.post(
      //         "${modulePath}base/menu/page",
      //         { pageNo: pageNo, pageSize: pageSize, baseId: baseId },
      //         function (data) {
      //             backResult(data, function (data) {
      //                 //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
      //                 tplFuncTable(data.entitys);
      //                 //执行分页
      //                 func(data);
      //             });
      //         }
      //     );
      // },
    },
  },
};
</script>
<style lang="scss" scoped></style>
