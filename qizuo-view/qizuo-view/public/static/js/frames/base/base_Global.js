export default {
  base: {
    /** 全局yes */
    yes: 1,
    /** 全局no */
    no: 0,
    /** 全局有效 */
    statusYes: 0,
    /** 全局无效 */
    statusNo: 1,
    /** 返回成功码 */
    backSuccessCode: 0,
    /** 返回异常码 */
    backErrorCode: -9,
    /** public路径 */
    publicPath: process.env.BASE_URL,
    /** pagesize */
    pageSize: 10,
  },
};
