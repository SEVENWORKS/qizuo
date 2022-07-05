<template>
  <div class="base_login" ref="base_login">
    <header></header>
    <section id="seo">
      <el-input
        class="form-control"
        v-model="value"
        :style="{ display: display }"
      />
    </section>
    <footer>
      <div id="fo_tag">qizuo</div>
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "",
      display: "none",
      redirect: undefined, //跳转地址
      otherQuery: {}, //跳转参数
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true,
    },
  },
  mounted() {
    //路由跳转页面如果不在app元素中会出现空白页面的问题，所以这句话不能加！
    // document.getElementsByTagName("body")[0].appendChild(this.$refs.base_login);
    //监听键盘事件
    const vm = this;
    var openP = new Array();
    const handel = function (e) {
      //密码存储
      if (27 == e.keyCode) {
        //清除键盘数字
        openP = [];
      } else {
        //存储键盘数字
        openP.push(e.keyCode);
      }
      //判断是否显现
      if (20 == openP.length) {
        if (70 == openP[0] && 73 == openP[19]) {
          document.removeEventListener("keydown", handel, false);
          vm.coolSty();
        }
      }
    };
    document.addEventListener("keydown", handel, false);
  },
  methods: {
    /**显现样式*/
    coolSty() {
      const vm = this;
      setTimeout(() => {
        this.display = "block";
        //二次绑定
        document.addEventListener(
          "keydown",
          function (e) {
            if (88 == e.keyCode && e.ctrlKey) {
              //发送
              vm.send();
            }
          },
          false
        );
      }, 100);
    },
    /**发送*/
    send() {
      const value = this.value;
      if (null != value && "" != value) {
        this.$store
          .dispatch("user/queryToken")
          .then(() => {
            const fd = new FormData();
            fd.append("key", this.value);
            this.$store
              .dispatch("user/login", fd)
              .then(() => {
                this.$router.push({
                  path: this.redirect || "/",
                  query: this.otherQuery,
                });
              })
              .catch(() => {
                alert("登录失败");
              });
          })
          .catch(() => {
            alert("验证失败");
          });
      }
    },
    //重定向参数
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    },
  },
};
</script>
<style scoped>
.base_login {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  z-index: 888;
  background: url("~@/assets/img/body-theme/qizuo-backgroudimg.png");
}

header {
  height: 40%;
}

footer {
  height: 50%;
}

#seo {
  height: 10%;
}

.form-control {
  width: 30%;
  margin: 0 auto;
  display: none;
}

#fo_tag {
  width: 30%;
  text-align: center;
  margin: 0 auto;
}
</style>
