<template>
  <div>
    <qz-base-table
      @getData="getData"
      @deleteData="deleteData"
      @addUpdateData="addUpdateData"
    >
      <template v-slot:table>
        <el-table-column align="center" label="序号" width="80" type="index" />
        <el-table-column align="center" label="角色" prop="name" />
        <el-table-column align="center" label="菜单" prop="menus" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item label="角色">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="菜单">
            <el-input v-model="form.menus"></el-input>
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
        menus: "",
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
