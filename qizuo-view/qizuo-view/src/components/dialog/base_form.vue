<template>
  <el-dialog title="新增/修改" :visible.sync="show">
    <slot></slot>

    <div slot="footer" class="dialog-footer" style="text-align: center;">
      <el-button type="primary" @click="confim">
        提交
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      form: {},
    };
  },
  methods: {
    //提交
    confim() {
      this.$emit("addUpdateData");
    },
    //是否隐藏
    isShow(val) {
      this.show = val;
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
