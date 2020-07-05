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
    /** title */
    title: "Vue Element Admin",
    /**
     * @type {boolean} true | false
     * @description Whether show the settings right-panel
     */
    showSettings: true,
    /**
     * @type {boolean} true | false
     * @description Whether need tagsView
     */
    tagsView: true,
    /**
     * @type {boolean} true | false
     * @description Whether fix the header
     */
    fixedHeader: false,
    /**
     * @type {boolean} true | false
     * @description Whether show the logo in sidebar
     */
    sidebarLogo: false,
    /**
     * @type {string | array} 'production' | ['production', 'development']
     * @description Need show err logs component.
     * The default is only used in the production env
     * If you want to also use it in dev, you can pass ['production', 'development']
     */
    errorLog: "production",
  },
};
