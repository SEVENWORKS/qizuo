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
    /** url prefix */
    url_prefix: "http://localhost:9400/port",
  },
};
