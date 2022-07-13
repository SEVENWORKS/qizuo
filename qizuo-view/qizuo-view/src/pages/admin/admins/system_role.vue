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
        <el-table-column align="center" label="权限名称" prop="name" />
        <el-table-column align="center" label="资源ID集合" prop="menuIds" />
        <el-table-column align="center" label="数据操作能力（增删查改）" prop="dataScopeCds" />
        <el-table-column align="center" label="创建者" prop="baseCreateUserId" />
        <el-table-column align="center" label="创建时间" prop="baseCreateTm" />
        <el-table-column align="center" label="修改者" prop="baseUpdateUserId" />
        <el-table-column align="center" label="修改时间" prop="baseUpdateTm" />
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
          <el-form-item label="权限名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="资源ID集合">
            <el-input v-model="form.menuIds"></el-input>
          </el-form-item>
          <el-form-item label="数据操作能力（增删查改）">
            <el-input v-model="form.dataScopeCds"></el-input>
          </el-form-item>
          <el-form-item label="创建者">
            <el-input v-model="form.baseCreateUserId"></el-input>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.baseCreateTm"></el-input>
          </el-form-item>
          <el-form-item label="修改者">
            <el-input v-model="form.baseUpdateUserId"></el-input>
          </el-form-item>
          <el-form-item label="修改时间">
            <el-input v-model="form.baseUpdateTm"></el-input>
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
import { getRole, getRoles, getMenus } from "@/apis/user";
import { doRoles, delRoles } from "@/apis/user";
export default {
  data() {
    return {
      form: {
        baseId: "",
        name: "",
        menuIds: "",
        dataScopeCds: "",
        baseCreateUserId: "",
        baseCreateTm: "",
        baseUpdateUserId: "",
        baseUpdateTm: "",
        baseStatus: "",
        baseRemarks: "",
        baseCreateIp: "",
        baseUpdateIp: "",
      },
      rules: {}
    };
  },
  methods: {
    //获取单个数据
    getData(data) {
      getRole(data).then((response) => {
        const { result } = response;
        this.form = result;
      });
    },
    //获取基本数据
    getDatas(page) {
      getRoles(page).then((reponse) => {
        const { result } = reponse;
        this.$refs.baseTable.data = result;
      });
    },
    //删除
    deleteData(data) {
      delRoles(data).then(() => {
        window.location.reload();
      });
    },
    //新增修改
    addUpdateData() {
      doRoles(this.form).then(() => {
        window.location.reload();
      });
    }
  },
};
</script>

