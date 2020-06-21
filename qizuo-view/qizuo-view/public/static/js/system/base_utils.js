/** 弹出窗(重写，基于layer) */
window.alert = function (msg, func) {
  window._vm.$global.alert_msg = msg;
  if (isFunction(func)) {
    func();
  }
};

/** 判断值是否能用 */
function isNotBlank(value) {
  if (
    typeof value == "undefined" ||
    null == value ||
    value.length <= 0 ||
    "" == value
  ) {
    return false;
  } else {
    return true;
  }
}
/** 判断元素是否是函数 */
function isFunction(value) {
  if (
    "" != value &&
    null != value &&
    Object.prototype.toString.call(value) === "[object Function]"
  ) {
    return true;
  } else {
    return false;
  }
}

/** js获取项目根路径 */
function getRootPath() {
  var pathName = window.location.pathname.substring(1);
  var webName =
    pathName == "" ? "" : pathName.substring(0, pathName.indexOf("/"));
  return (
    window.location.protocol + "//" + window.location.host + "/" + webName + "/"
  );
}

/** 取模或者取余 */
function countDivision(value1, value2, type) {
  if (isNotBlank(type) && type == 1) {
    return Math.floor(value1 / value2);
  } else {
    return value1 % value2;
  }
}

/** js判断一个字符在字符串中出现次数 */
function charViewTimes(char, str) {
  var array = str.split(char);
  return isNotBlank(array) ? array.length - 1 : 0;
}

/** *******************框架自带初始化插件******************************* */
/** checkbox和radio初始化调用(icheck.js),not后面的主要是为了剔除bootstrap按钮的，这个主要是优化普通checkbox和radio样式的 */
function checkAndradio() {
  $(
    'input:checkbox:not([data-toggle="buttons"] input,.make-switch input),input:radio:not([data-toggle="buttons"] input)'
  ).iCheck({
    checkboxClass: "icheckbox_minimal",
    radioClass: "iradio_minimal",
    increaseArea: "20%",
  });
}

/** 开关按钮ckeckbox和radio使用方法，就是引用<script src="${staticPath}js/base/toggler.min.js"></script>，这个js专门为这两个开关按钮写的,这个是bootstrap switch(http://www.bootcss.com/p/bootstrap-switch/) */
function checkSwitch(dom, trueFunc, falseFunc) {
  //开关控制(默认false右边字)
  $(dom).on("switch-change", function (e, data) {
    var $el = $(data.el),
      value = data.value;
    if (value) {
      //true左边字
      if (isFunction(trueFunc)) {
        trueFunc();
      }
    } else {
      //false右边字
      if (isFunction(falseFunc)) {
        falseFunc();
      }
    }
  });
}

/** mask插件控制input格式(input-mask.js) */
function maskForInput() {
  $(".mask-date").mask("00/00/0000");
  $(".mask-time").mask("00:00:00");
  $(".mask-date_time").mask("00/00/0000 00:00:00");
  $(".mask-cep").mask("00000-000");
  $(".mask-phone").mask("0000-0000");
  $(".mask-phone_with_ddd").mask("(00) 0000-0000");
  $(".mask-phone_us").mask("(000) 000-0000");
  $(".mask-mixed").mask("AAA 000-S0S");
  $(".mask-cpf").mask("000.000.000-00", { reverse: true });
  $(".mask-money").mask("000.000.000.000.000,00", { reverse: true });
  $(".mask-money2").mask("#.##0,00", { reverse: true, maxlength: false });
  $(".mask-ip_address").mask("0ZZ.0ZZ.0ZZ.0ZZ", {
    translation: { Z: { pattern: /[0-9]/, optional: true } },
  });
  $(".mask-ip_address").mask("099.099.099.099");
  $(".mask-percent").mask("##0,00%", { reverse: true });
  $(".mask-credit_card").mask("0000 0000 0000 0000");
}

/** select类初始化类 */
function selectInit() {
  //slect插件初始化(bootstrap selectpicker)
  if ($(".select")[0]) {
    $(".select").selectpicker();
  }
  //choose插件初始化(jquery.chosen.min.js)
  if ($(".tag-select")[0]) {
    $(".tag-select").chosen();
  }
  //choose插件初始化2(jquery.chosen.min.js)
  $(".tag-select-limited").chosen({
    max_selected_options: 5,
  });
}

/** 数字加减初始化(spinner.js) */
function spinnerInit() {
  //Basic
  $(".spinner-1").spinedit();

  //Set Value
  $(".spinner-2").spinedit("setValue", 100);

  //Set Minimum
  $(".spinner-3").spinedit("setMinimum", -10);

  //Set Maximum
  $(".spinner-4").spinedit("setMaxmum", 100);

  //Set Step
  $(".spinner-5").spinedit("setStep", 10);

  //Set Number Of Decimals
  $(".spinner-6").spinedit("setNumberOfDecimals", 2);
}

/** 时间日期初始化(datetimepicker.min.js) */
function timeInit() {
  //Date Only
  if ($(".date-only")[0]) {
    $(".date-only").datetimepicker({
      pickTime: false,
    });
  }

  //Time only
  if ($(".time-only")[0]) {
    $(".time-only").datetimepicker({
      pickDate: false,
    });
  }

  //12 Hour Time
  if ($(".time-only-12")[0]) {
    $(".time-only-12").datetimepicker({
      pickDate: false,
      pick12HourFormat: true,
    });
  }

  $(".datetime-pick input:text").on("click", function () {
    $(this).closest(".datetime-pick").find(".add-on i").click();
  });
}

/** 颜色选择器初始化(colorpicker.min.js) */
function colorInit() {
  //Default - hex
  if ($(".color-picker")[0]) {
    $(".color-picker").colorpicker();
  }

  //RGB
  if ($(".color-picker-rgb")[0]) {
    $(".color-picker-rgb").colorpicker({
      format: "rgb",
    });
  }

  //RGBA
  if ($(".color-picker-rgba")[0]) {
    $(".color-picker-rgba").colorpicker({
      format: "rgba",
    });
  }

  //Output Color
  if ($('[class*="color-picker"]')[0]) {
    $('[class*="color-picker"]')
      .colorpicker()
      .on("changeColor", function (e) {
        var colorThis = $(this).val();
        $(this)
          .closest(".color-pick")
          .find(".color-preview")
          .css("background", e.color.toHex());
      });
  }
}

/** 普通textarea初始化 */
function textareaInit() {
  //扩展型(autosize.min.js，这款插件不仅仅在textarea上有体现)
  if ($(".auto-size")[0]) {
    $(".auto-size").autosize();
  }
  //超出滑动型(scroll.min.js，这款插件不仅仅在textarea上有体现)
  if ($(".overflow")[0]) {
    var overflowRegular,
      overflowInvisible = false;
    overflowRegular = $(".overflow").niceScroll();
  }
}

/** 两种富文本编辑框初始化 */
function bigTextArae() {
  //Markedown
  if ($(".markdown-editor")[0]) {
    $(".markdown-editor").markdown({
      autofocus: false,
      savable: false,
    });
  }

  //WYSIWYE Editor
  if ($(".wysiwye-editor")[0]) {
    $(".wysiwye-editor").summernote({
      height: 200,
    });
  }
}

/** 进度条初始化(slider.min.js) */
function sliderInit() {
  if ($(".input-slider")[0]) {
    $(".input-slider")
      .slider()
      .on("slide", function (ev) {
        $(this)
          .closest(".slider-container")
          .find(".slider-value")
          .val(ev.value);
      });
  }
}

/** 媒体插件初始化(media-player.min.js视屏,pirobox.min.js图片) */
function mediaInit() {
  //视屏和音频
  if ($("audio,video")[0]) {
    $("audio,video").mediaelementplayer({
      success: function (player, node) {
        $("#" + node.id + "-mode").html("mode: " + player.pluginType);
      },
    });
  }
  //图片组
  if ($(".pirobox_gall")[0]) {
    //Fix IE
    jQuery.browser = {};
    jQuery.browser.msie = false;
    jQuery.browser.version = 0;
    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
      jQuery.browser.msie = true;
      jQuery.browser.version = RegExp.$1;
    }

    //Lightbox
    $().piroBox_ext({
      piro_speed: 700,
      bg_alpha: 0.5,
      piro_scroll: true, // pirobox always positioned at the center of the page
    });
  }
}
/** *******************框架自带初始化插件******************************* */
