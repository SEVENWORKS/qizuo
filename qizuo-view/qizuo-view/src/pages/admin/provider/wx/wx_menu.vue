<template>
  <div>
    <el-input v-model="appid"></el-input>
    <qz-base-table
      @getData="getData"
      @getDatas="getDatas"
      @deleteData="deleteData"
      @addUpdateData="addUpdateData"
      ref="baseTable"
    >
      <template v-slot:table>
        <el-table-column align="center" label="序号" width="80" type="index" />
        <el-table-column align="center" label="类别" prop="type" />
        <el-table-column align="center" label="名称" prop="name" />
        <el-table-column
          align="center"
          label="标识(click等点击类型必须)"
          prop="key"
        />
        <el-table-column
          align="center"
          label="图标标识(需要先上传)"
          prop="mediaId"
        />
        <el-table-column align="center" label="跳转路径" prop="url" />
        <el-table-column align="center" label="小程序的appId" prop="appId" />
        <el-table-column
          align="center"
          label="小程序的页面路径"
          prop="pagePath"
        />
        <el-table-column align="center" label="子菜单" prop="subButtons" />
        <el-table-column align="center" label="用户标签的id" prop="tagId" />
        <el-table-column
          align="center"
          label="性别：男（1）女（2）"
          prop="sex"
        />
        <el-table-column align="center" label="国家信息" prop="country" />
        <el-table-column align="center" label="省份信息" prop="province" />
        <el-table-column align="center" label="城市信息" prop="city" />
        <el-table-column
          align="center"
          label="客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)"
          prop="clientPlatformType"
        />
        <el-table-column align="center" label="语言信息" prop="language" />
      </template>
      <template v-slot:form>
        <el-form
          ref="form"
          :rules="rules"
          :model="form"
          label-position="left"
          label-width="70px"
        >
          <el-form-item
            label="类别(view表示网页类型，click表示点击类型，miniprogram表示小程序类型)"
          >
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="标识(click等点击类型必须,事件推送时标识)">
            <el-input v-model="form.key"></el-input>
          </el-form-item>
          <el-form-item label="图标标识(需要先上传)">
            <el-input v-model="form.mediaId"></el-input>
          </el-form-item>
          <el-form-item label="跳转路径">
            <el-input v-model="form.url"></el-input>
          </el-form-item>
          <el-form-item label="小程序的appId">
            <el-input v-model="form.appId"></el-input>
          </el-form-item>
          <el-form-item label="小程序的页面路径">
            <el-input v-model="form.pagePath"></el-input>
          </el-form-item>
          <el-form-item label="子菜单">
            <el-input v-model="form.subButtons"></el-input>
          </el-form-item>
          <el-form-item label="用户标签的id">
            <el-input v-model="form.rule.tagId"></el-input>
          </el-form-item>
          <el-form-item label="性别：男（1）女（2）">
            <el-input v-model="form.rule.sex"></el-input>
          </el-form-item>
          <el-form-item label="国家信息">
            <el-input v-model="form.rule.country"></el-input>
          </el-form-item>
          <el-form-item label="省份信息">
            <el-input v-model="form.rule.province"></el-input>
          </el-form-item>
          <el-form-item label="城市信息">
            <el-input v-model="form.rule.city"></el-input>
          </el-form-item>
          <el-form-item
            label="客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)"
          >
            <el-input v-model="form.rule.clientPlatformType"></el-input>
          </el-form-item>
          <el-form-item label="语言信息">
            <el-input v-model="form.rule.language"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </qz-base-table>
    <el-button @click="commit">提交</el-button>
  </div>
</template>
<!-- 执行js -->
<script>
import { getMenus, doMenus, delMenus } from "@/apis/wx";
export default {
  data() {
    return {
      appid: "wx27ab938b99409162", //"wx8d37841ba6e03714", //appid
      formCommit: {
        buttons: [], //按钮
        matchRule: null, //匹配规则
      },
      form: {
        type: "",
        name: "",
        key: "",
        mediaId: "",
        appId: "",
        url: "",
        pagePath: "",
        subButtons: "",
        rule: {
          tagId: "",
          sex: "",
          country: "",
          province: "",
          city: "",
          clientPlatformType: "",
          language: "",
        },
      },
      rules: [],
    };
  },
  methods: {
    //获取单个数据
    getData(data) {},
    //获取基本数据
    getDatas(page) {
      getMenus(this.appid).then((reponse) => {
        const { result } = reponse;
        const back = [];
        //自定义菜单
        if (result.menu && result.menu.buttons) {
          result.menu.buttons.forEach((key) => {
            key.menuId = result.menu.menuId;
            if (result.menu.rule) {
              Object.assign(key, result.menu.rule);
            }
            //二级菜单
            let subButton = "";
            if (key.subButtons) {
              key.subButtons.forEach((item) => {
                subButton += item.name + "|";
              });
            }
            if (subButton) {
              key.subButtons = subButton.substring(0, subButton.length - 1);
            }
            back.push(key);
          });
        }

        //个性化菜单
        if (result.conditionalMenu) {
          result.conditionalMenu.forEach((key) => {
            key.buttons.forEach((key2) => {
              key2.menuId = key.menuId;
              if (key.rule) {
                Object.assign(key2, key.rule);
              }
              let subButton = "";
              if (key2.subButtons) {
                key2.subButtons.forEach((item) => {
                  subButton += item.name + "|";
                });
              }
              if (subButton) {
                key2.subButtons = subButton.substring(0, subButton.length - 1);
              }
              back.push(key2);
            });
          });
        }

        //选项
        this.$refs.baseTable.data = back;
      });
    },
    //删除
    deleteData(data) {
      delMenus(this.appid, data.menuId).then(() => {
        window.location.reload();
      });
    },
    //新增修改
    addUpdateData() {
      //个性菜单匹配规则
      if (this.form.subButtons == 0) {
        if (
          this.form.rule.tagId ||
          this.form.rule.sex ||
          this.form.rule.country ||
          this.form.rule.province ||
          this.form.rule.city ||
          this.form.rule.clientPlatformType ||
          this.form.rule.language
        ) {
          this.formCommit.matchRule = this.form.rule;
        }
      }
      //填入
      if (this.form.subButtons.includes("-")) {
        const subButtons = this.form.subButtons.split("-");
        const subButtonsArr = this.formCommit.buttons[parseInt(subButtons[0])]
          .subButtons[parseInt(subButtons[1])];
        console.log(subButtons);
        if (!subButtonsArr) {
          this.formCommit.buttons[parseInt(subButtons[0])].subButtons[
            parseInt(subButtons[1])
          ] = new Array();
        }
        this.formCommit.buttons[parseInt(subButtons[0])].subButtons[
          parseInt(subButtons[1])
        ] = Object.assign({}, this.form, { subButtons: [], rule: null });
      } else {
        this.formCommit.buttons.push(
          Object.assign({}, this.form, { subButtons: [], rule: null })
        );
      }
    },
    //提交所有
    commit() {
      doMenus(this.appid, this.formCommit).then(() => {
        window.location.reload();
      });
    },
  },
};
</script>

