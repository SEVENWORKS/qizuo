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
        <el-table-column align="center" label="消息名称" prop="title" />
        <el-table-column align="center" label="消息类型" prop="type" />
        <el-table-column align="center" label="消息内容" prop="content" />
        <el-table-column align="center" label="接收人id(一般对应人员id)" prop="sendUserId" />
        <el-table-column align="center" label="接收人TYPE(一般对应权限表)" prop="sendType" />
        <el-table-column align="center" label="是否发送" prop="isSend" />
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
          <el-form-item label="消息名称">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="消息类型">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="消息内容">
            <el-input v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item label="接收人id(一般对应人员id)">
            <el-input v-model="form.sendType"></el-input>
          </el-form-item>
          <el-form-item label="接收人TYPE(一般对应权限表)">
            <el-input v-model="form.sendUserId"></el-input>
          </el-form-item>
          <el-form-item label="是否发送">
            <el-input v-model="form.isSend"></el-input>
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
        title: "",
        type: "",
        content: "",
        sendType:"",
        sendUserId:"",
        isSend:"",
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

