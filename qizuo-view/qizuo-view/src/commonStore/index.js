//公共store
export default {
  state: {
    alertMsg: "",
  },
  mutations: {
    updateAlertMsg(state, alertMsg) {
      state.alertMsg = alertMsg;
    },
  },
  actions: {},
};
