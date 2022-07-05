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
            <el-select
              v-model="form.menus"
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
        </el-form>
      </template>
    </qz-base-table>
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
        name: "",
        menus: [],
      },
      rules: [],
      options: [], //菜单选择
    };
  },
  created() {
    this.getOptions();
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
        this.options = result;
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
    },
    //获取options
    getOptions() {
      getMenus().then((reponse) => {
        const { result } = reponse;
        this.options = result;
      });
    },
  },
};
</script>

