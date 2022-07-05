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

export default {
    alert,
    isNotBlank,
    isFunction
}