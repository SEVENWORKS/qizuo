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
        <el-table-column align="center" label="名称" prop="name" />
        <el-table-column align="center" label="主题" prop="theme" />
        <el-table-column align="center" label="路径" prop="url" />
        <el-table-column align="center" label="上级菜单" prop="parentId" />
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
          <el-form-item label="主题">
            <el-input v-model="form.theme"></el-input>
          </el-form-item>
          <el-form-item label="路径">
            <el-input v-model="form.url"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单">
            <el-select v-model="form.parentId" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.baseId"
                :label="item.name"
                :value="item.baseId"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>
  </div>
</template>
<!-- 执行js -->
<script>
import { getMenu, getMenus, getMenusEach, getMenusPage } from "@apis/admin";
import { doMenus, delMenus } from "@apis/admin-user";
export default {
  data() {
    return {
      form: {
        name: "",
        theme: "",
        url: "",
        parentId: "",
      },
      rules: [],
      options: [], //菜单选择
    };
  },
  methods: {
    //获取单个数据
    getData(data) {
      getMenu(data).then((response) => {
        const { result } = response;
        this.form = result;
      });
    },
    //获取基本数据
    getDatas(page) {
      getMenus(page).then((reponse) => {
        const { result } = reponse;
        this.options = result;
        this.$refs.baseTable.data = result;
      });
    },
    //删除
    deleteData(data) {
      delMenus(data).then(() => {
        window.location.reload();
      });
    },
    //新增修改
    addUpdateData() {
      if (!this.form.parentId) {
        this.form.parentId = 0;
      }
      doMenus(this.form).then(() => {
        window.location.reload();
      });
    },
  },
};
</script>
<style lang="scss" scoped></style>
