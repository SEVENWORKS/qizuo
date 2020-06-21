<template>
  <div class="base_login" ref="base_login">
    <header></header>
    <section id="seo">
      <input class="form-control" type="password" value="" />
    </section>
    <footer>
      <div id="fo_tag">qizuo</div>
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted() {
    document.getElementsByTagName("body")[0].appendChild(this.$refs.base_login);
    //监听键盘事件
    const vm = this;
    var openP = new Array();
    $(document).keydown(function (e) {
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
          $(document).unbind();
          vm.coolSty();
        }
      }
    });
  },

  methods: {
    /**显现样式*/
    coolSty() {
      const vm = this;
      setTimeout(() => {
        $("#seo input").css("display", "block");
        $("#seo input").focus();
        //二次绑定
        $(document).keydown(function (e) {
          if (88 == e.keyCode && e.ctrlKey) {
            //发送
            vm.send();
          }
        });
      }, 100);
    },
    /**发送*/
    send() {
      var value = $("#seo input").val();
      if (null != value && "" != value) {
        alert("发送");
      }
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
  z-index: 9999;
  background: url("/static/img/frame/body/qizuo-backgroudimg.png");
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

#seo input {
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
