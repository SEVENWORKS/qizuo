<template>
  <div style="padding: 10px;">
    <!-- 内容区域 -->
    <el-table
      :data="data"
      border
      fit
      highlight-current-row
      @row-click="rowClick"
      @header-click="headerClick"
    >
      <slot name="table"></slot>
    </el-table>

    <!-- 分页 -->
    <qz-pagination
      v-show="page.totalCount > 0"
      :total="page.totalCount"
      :page.sync="page.pageNo"
      :limit.sync="page.pageSize"
      @pagination="getDatas"
      style="text-align: center;"
    />

    <!-- 表单 -->
    <qz-dialog-form ref="form" @addUpdateData="addUpdateData">
      <slot name="form"></slot>
    </qz-dialog-form>

    <!-- 按钮 -->
    <qz-dialog :show.sync="dialogShow">
      <div>
        <el-button @click="addData" v-if="isHeaderClick">新增</el-button>
        <el-button @click="updateData" v-if="!isHeaderClick">修改</el-button>
        <el-button @click="deleteData" v-if="!isHeaderClick">删除</el-button>
      </div>
    </qz-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogShow: false, //弹框是否隐藏
      isHeaderClick: false, //是否是头点击
      row: null, //当前row
      data: [], //数据
      page: {
        totalCount: 0,
        pageNo: 1,
        pageSize: 10,
        entity: {},
      }, //分页
    };
  },
  mounted() {
    this.getDatas();
  },
  methods: {
    //行点击事件
    rowClick(row, column, event) {
      this.dialogShow = true;
      this.row = row;
      this.isHeaderClick = false;
    },
    headerClick() {
      this.dialogShow = true;
      this.isHeaderClick = true;
    },
    //获取基本数据
    getDatas() {
      this.$emit("getDatas", this.page);
    },
    //删除
    deleteData() {
      this.dialogShow = false;
      this.$confirm("确定删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$emit("deleteData", this.row);
        })
        .catch((err) => {});
    },
    //新增
    addData() {
      this.dialogShow = false;
      this.$refs.form.isShow(true);
    },
    //修改
    updateData() {
      this.$emit("getData", this.row);
      this.dialogShow = false;
      this.$refs.form.isShow(true);
    },
    addUpdateData() {
      this.$emit("addUpdateData");
    },
  },
};
</script>

<style scoped>
.dialog_container {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.dialog_content {
  /* background: #fff; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
