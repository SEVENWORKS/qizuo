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
        <el-table-column align="center" label="上传数据类型" prop="dataType" />
        <el-table-column align="center" label="关联数据id" prop="dataId" />
        <el-table-column align="center" label="关联字段" prop="dataColumn" />
        <el-table-column align="center" label="上传路径" prop="resourceName" />
        <el-table-column align="center" label="上传名称" prop="name" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item label="上传数据类型">
            <el-input v-model="form.dataType"></el-input>
          </el-form-item>
          <el-form-item label="关联数据id">
            <el-input v-model="form.dataId"></el-input>
          </el-form-item>
          <el-form-item label="关联字段">
            <el-input v-model="form.dataColumn"></el-input>
          </el-form-item>
          <el-form-item label="上传路径">
            <el-input v-model="form.resourceName"></el-input>
          </el-form-item>
          <el-form-item label="上传名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>

    <!-- 上传 -->
  </div>
</template>
<!-- 执行js -->
<script>
import { getFile, getFiles, getFilesPage } from "@apis/file";
export default {
  data() {
    return {
      form: {
        dataType: "",
        dataId: "",
        dataColumn: "",
        resourceName: "",
        name: "",
      },
      rules: [],
    };
  },
  methods: {
    //获取单个数据
    getData(data) {
      getFile(data).then((response) => {
        const { result } = response;
        this.form = result;
      });
    },
    //获取基本数据
    getDatas(page) {
      getFilesPage(page).then((reponse) => {
        const { result } = reponse;
        this.options = result.entitys;
        this.$refs.baseTable.data = result.entitys;
        this.$refs.baseTable.page = result;
      });
    },
    //删除
    deleteData(data) {},
    //新增修改
    addUpdateData() {},
  },
};
</script>
<style lang="scss" scoped></style>
