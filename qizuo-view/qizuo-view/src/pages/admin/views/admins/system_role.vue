<template>
  <div>
    <!-- 内容区域 -->
    <!-- 有悬浮效果的表格 -->
    <div class="block-area" id="tableHover">
      <div class="table-responsive overflow">
        <table class="table table-bordered table-hover tile" id="dataContainer">
          <thead>
            <tr>
              <th>序号</th>
              <th>角色名称</th>
              <th>首页路径</th>
              <th>跳转路径</th>
            </tr>
          </thead>
          <tbody>
            <!--<tr-->
            <!--id="tr{{index+1}}"-->
            <!--@click="-->
            <!--buttonPanel(-->
            <!--'修改',-->
            <!--'updateDate(\'${jumpPath}system/roleDo?baseId={{$value.baseId}}\')',-->
            <!--'删除',-->
            <!--'deleteData({baseId:{{$value.baseId}}},\'${modulePath}system/role/delete\',\'#tr{{$index+1}}\')'-->
            <!--)-->
            <!--"-->
            <!--&gt;-->
            <!--<td>{{ index + 1 }}</td>-->
            <!--<td>{{ value.name }}</td>-->
            <!--<td>{{ value.indexUrl }}</td>-->
            <!--<td>{{ value.jumpUrl }}</td>-->
            <!--</tr>-->
            <tr>
              <td style="text-align: center;">
                暂无数据
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 分页 -->
    <div class="block-area">
      <div class="row">
        <div id="pageContainer" class="col-md-12 text-center">
          <ul class="pagination" id="page_ul"></ul>
        </div>
      </div>
    </div>
  </div>
</template>
<!-- 执行js -->
<script>
export default {
  data() {
    return {};
  },
  methods: {
    init() {
      /** ************************************************************ */
      //新增按钮(这个函数第二个参数可以传入复杂函数)
      buttonOne("新增", function () {
        pjaxFunc("${jumpPath}system/roleDo");
      });
      /** ************************************************************ */
      //分页(传入获取分页数据的方法,可传分页size和分页no)
      pageHtml(qPage, 10, 1);
      //分页数据查询(都默认一个函数)
      function qPage(func) {
        $.post("${modulePath}system/role/list", {}, function (data) {
          backResult(data, function (data) {
            //模板(数据，容器，模板)(当出现不在返回元素中值的时候，可以往对象中添加数据，毕竟从java返回过来后就是一个js对象)
            tplFuncTable(data);
            //执行分页
            func(data);
          });
        });
      }
    },
  },
};
</script>
<style lang="scss" scoped></style>
