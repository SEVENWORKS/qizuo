window.$global = {
  /** 全局有效 */
  success: 0,
  /** 全局无效 */
  error: -1,
  base: {
    /** public路径 */
    publicPath: process.env.BASE_URL,
    /** pagesize */
    pageSize: 10,
    /** url current prefix */
    url_current_prefix:
      window.location.protocol + "//" + window.location.host + "/",
    /** url prefix */
    url_prefix: "http://81.68.193.56:9300/zuul", //http://47.114.138.216:8091/port
  },
};
