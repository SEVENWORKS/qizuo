<template>
  <div>
    <qz-base-table
      @getData="getData"
      @deleteData="deleteData"
      @addUpdateData="addUpdateData"
    >
      <template v-slot:table>
        <el-table-column align="center" label="序号" width="80" type="index" />
        <el-table-column align="center" label="姓名" prop="name" />
        <el-table-column align="center" label="ID" prop="idCard" />
        <el-table-column align="center" label="角色" prop="roles" />
        <el-table-column align="center" label="头像" prop="photo" />
        <el-table-column align="center" label="简介" prop="remarks" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item label="名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="ID">
            <el-input v-model="form.idCard"></el-input>
          </el-form-item>
          <el-form-item label="角色">
            <el-input v-model="form.roles"></el-input>
          </el-form-item>
          <el-form-item label="头像">
            <el-input v-model="form.photo"></el-input>
          </el-form-item>
          <el-form-item label="简介">
            <el-input v-model="form.remarks"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>
  </div>
</template>
<!-- 执行js -->
<script>
export default {
  data() {
    return {
      form: {
        name: "",
        idCard: "",
        roles: "",
        photo: "",
        remarks: "",
      },
      rules: [],
    };
  },
  created() {},
  methods: {
    //获取基本数据
    getData() {},
    //删除
    deleteData(data) {},
    //新增修改
    addUpdateData() {},
  },
};
</script>
<style lang="scss" scoped></style>
