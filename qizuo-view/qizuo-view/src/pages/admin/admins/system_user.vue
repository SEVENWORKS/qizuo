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
        <el-table-column align="center" label="key" prop="userName" />
        <el-table-column align="center" label="姓名" prop="name" />
        <el-table-column align="center" label="ID" prop="idCard" />
        <el-table-column align="center" label="角色" prop="roles" />
        <el-table-column align="center" label="头像" prop="photo" />
        <el-table-column align="center" label="简介" prop="baseRemarks" />
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
            <el-input v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="ID">
            <el-input v-model="form.idCard"></el-input>
          </el-form-item>
          <el-form-item label="角色">
            <el-select
              v-model="form.roles"
              multiple
              filterable
              placeholder="请选择"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="头像">
            <el-input v-model="form.photo"></el-input>
          </el-form-item>
          <el-form-item label="简介">
            <el-input v-model="form.baseRemarks"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>
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
        userName: "",
        name: "",
        idCard: "",
        roles: [],
        photo: "",
        baseRemarks: "",
      },
      rules: [],
      options: [], //角色选择
    };
  },
  created() {
    this.getOptions();
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
        this.options = result.entitys;
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
    //获取options
    getOptions() {
      getRoles().then((reponse) => {
        const { result } = reponse;
        this.options = result;
      });
    },
  },
};
</script>

