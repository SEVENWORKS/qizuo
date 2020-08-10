/** 弹出窗(重写，基于layer) */
window.alert = function (msg, func) {
  //更新store消息
  window._vm.$message(msg + "");
  //函数执行
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
