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
        <el-table-column align="center" label="资源名称" prop="name" />
        <el-table-column align="center" label="资源路径" prop="url" />
        <el-table-column align="center" label="父编号" prop="parentId" />
        <el-table-column align="center" label="主题" prop="theme" />
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
          <el-form-item label="资源名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="资源路径">
            <el-input v-model="form.url"></el-input>
          </el-form-item>
          <el-form-item label="父编号">
            <el-input v-model="form.parentId"></el-input>
          </el-form-item>
          <el-form-item label="主题">
            <el-input v-model="form.theme"></el-input>
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
  </div>
</template>
<!-- 执行js -->
<script>
import { getMenu, getMenus, getMenusEach, getMenusPage } from "@/apis/user";
import { doMenus, delMenus } from "@/apis/user";
export default {
  data() {
    return {
      form: {
        baseId: "",
        name: "",
        url: "",
        parentId: "",
        theme:"",
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
      getMenu(data).then(({data}) => {
        if(data){
          this.form = data.result;
        }
      });
    },
    //获取基本数据
    getDatas(page) {
      getMenus(page).then(({data}) => {
        if(data){
          this.$refs.baseTable.data = data.result;
        }
      });
    },
    //删除
    deleteData(data) {
      delMenus(data).then(({data}) => {
        if(data){
          window.location.reload();
        }
      });
    },
    //新增修改
    addUpdateData() {
      if (!this.form.parentId) {
        this.form.parentId = 0;
      }
      doMenus(this.form).then(({data}) => {
        if(data){
          window.location.reload();
        }
      });
    },
  },
};
</script>

