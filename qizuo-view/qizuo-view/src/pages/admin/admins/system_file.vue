<template>
  <div>
    <qz-table
      @getData="getData"
      @getDatas="getDatas"
      @deleteData="deleteData"
      @addUpdateData="addUpdateData"
      ref="baseTable"
    >
      <template v-slot:table>
        <el-table-column align="center" label="序号" width="80" type="index" />
        <el-table-column align="center" label="编号" prop="baseId" />
        <el-table-column align="center" label="上传资源名称" prop="resourceName" />
        <el-table-column align="center" label="文件名称" prop="name" />
        <el-table-column align="center" label="创建者" prop="baseCreateUserId" />
        <el-table-column align="center" label="创建时间" prop="baseCreateTime" />
        <el-table-column align="center" label="修改者" prop="baseUpdateUserId" />
        <el-table-column align="center" label="修改时间" prop="baseUpdateTime" />
        <el-table-column align="center" label="状态" prop="baseStatus" />
        <el-table-column align="center" label="备注" prop="baseRemarks" />
        <el-table-column align="center" label="创建ip" prop="baseCreateIp" />
        <el-table-column align="center" label="更新ip" prop="baseUpdateIp" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item label="编号">
            <el-input v-model="form.baseId"></el-input>
          </el-form-item>
          <el-form-item label="上传资源名称">
            <el-input v-model="form.resourceName"></el-input>
          </el-form-item>
          <el-form-item label="文件名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="创建者">
            <el-input v-model="form.baseCreateUserId"></el-input>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.baseCreateTime"></el-input>
          </el-form-item>
          <el-form-item label="修改者">
            <el-input v-model="form.baseUpdateUserId"></el-input>
          </el-form-item>
          <el-form-item label="修改时间">
            <el-input v-model="form.baseUpdateTime"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="form.baseStatus"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.baseRemarks"></el-input>
          </el-form-item>
          <el-form-item label="创建ip">
            <el-input v-model="form.baseCreateIp"></el-input>
          </el-form-item>
          <el-form-item label="更新ip">
            <el-input v-model="form.baseUpdateIp"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-table>

    <!-- 上传 -->
  </div>
</template>
<!-- 执行js -->
<script>
import { getFile, getFiles, getFilesPage } from "@/apis/file";
export default {
  data() {
    return {
      form: {
        baseId: "",
        resourceName: "",
        name: "",
        baseCreateUserId: "",
        baseCreateTime: "",
        baseUpdateUserId: "",
        baseUpdateTime: "",
        baseStatus: "",
        baseRemarks: "",
        baseCreateIp: "",
        baseUpdateIp: "",
      },
      rules: {},
    };
  },
  methods: {
    //获取单个数据
    getData(data) {
      getFile(data).then(({data}) => {
        if(data){
          this.form = data.result;
        }
      });
    },
    //获取基本数据
    getDatas(page) {
      getFilesPage(page).then(({data}) => {
        if(data){
          this.$refs.baseTable.data = data.result.entitys;
          this.$refs.baseTable.page = data.result;
        }
      });
    },
    //删除
    deleteData(data) {},
    //新增修改
    addUpdateData() {},
  },
};
</script>

