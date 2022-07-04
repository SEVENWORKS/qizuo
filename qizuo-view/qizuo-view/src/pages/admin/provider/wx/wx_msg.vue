<template>
  <div>
    <qz-base-table
      @getData="getData"
      @getDatas="getDatas"
      @deleteData="deleteData"
      @addUpdateData="addUpdateData"
      ref="baseTable"
    >
      <template v-slot:table>
        <el-table-column align="center" label="序号" width="80" type="index" />
        <el-table-column align="center" label="key" prop="key" />
        <el-table-column align="center" label="value" prop="value" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item label="key">
            <el-input v-model="form.key"></el-input>
          </el-form-item>
          <el-form-item label="value">
            <el-input v-model="form.value"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>
  </div>
</template>
<!-- 执行js -->
<script>
import { getMsgs, doMsgs, delMsgs } from "@/apis/wx";
export default {
  data() {
    return {
      form: {
        key: "",
        value: "",
      },
      rules: [],
    };
  },
  methods: {
    //获取单个数据
    getData() {},
    //获取基本数据
    getDatas() {
      getMsgs().then((reponse) => {
        const { result } = reponse;
        const back = [];
        result.forEach((key) => {
          key = JSON.parse(key);
          for (let i in key) {
            back.push({ key: i, value: key[i] });
          }
        });
        this.$refs.baseTable.data = back;
      });
    },
    //删除
    deleteData() {
      delMsgs().then(() => {
        window.location.reload();
      });
    },
    //新增修改
    addUpdateData() {
      doMsgs(this.form).then(() => {
        window.location.reload();
      });
    },
  },
};
</script>
<style lang="scss" scoped></style>
