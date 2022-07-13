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
        <el-table-column align="center" label="账号" prop="userName" />
        <el-table-column align="center" label="密码" prop="password" />
        <el-table-column align="center" label="加密盐" prop="salt" />
        <el-table-column align="center" label="权限编号集合" prop="roleIds" />
        <el-table-column align="center" label="姓名" prop="name" />
        <el-table-column align="center" label="性别" prop="sexCd" />
        <el-table-column align="center" label="用户编号（身份证）" prop="idCard" />
        <el-table-column align="center" label="电话号码" prop="phone" />
        <el-table-column align="center" label="邮箱" prop="email" />
        <el-table-column align="center" label="头像" prop="photo" />
        <el-table-column align="center" label="地址" prop="address" />
        <el-table-column align="center" label="登录时间" prop="loginDate" />
        <el-table-column align="center" label="第三方key" prop="outmutualKey" />
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
          <el-form-item label="账号">
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="加密盐">
            <el-input v-model="form.salt"></el-input>
          </el-form-item>
          <el-form-item label="权限编号集合">
            <el-input v-model="form.roleIds"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.sexCd"></el-input>
          </el-form-item>
          <el-form-item label="用户编号（身份证）">
            <el-input v-model="form.idCard"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="头像">
            <el-input v-model="form.photo"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="登录时间">
            <el-input v-model="form.loginDate"></el-input>
          </el-form-item>
          <el-form-item label="第三方key">
            <el-input v-model="form.outmutualKey"></el-input>
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
import { getUser, getUsers, getUsersPage, getRoles } from "@/apis/user";
import { doUsers, delUsers } from "@/apis/user";
export default {
  data() {
    return {
      form: {
        baseId: "",
        userName: "",
        password: "",
        salt: "",
        roleIds:"",
        name:"",
        sexCd:"",
        idCard:"",
        phone:"",
        email:"",
        photo:"",
        loginDate:"",
        outmutualKey:"",
        baseCreateUserId: "",
        baseCreateTm: "",
        baseUpdateUserId: "",
        baseUpdateTm: "",
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
      getUser(data).then((response) => {
        const { result } = response;
        this.form = result;
      });
    },
    //获取基本数据
    getDatas(page) {
      getUsersPage(page).then((reponse) => {
        const { result } = reponse;
        this.$refs.baseTable.data = result.entitys;
        this.$refs.baseTable.page = result;
      });
    },
    //删除
    deleteData(data) {
      delUsers(data).then(() => {
        window.location.reload();
      });
    },
    //新增修改
    addUpdateData() {
      doUsers(this.form).then(() => {
        window.location.reload();
      });
    },
  },
};
</script>

