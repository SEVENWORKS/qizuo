import { login, logout, token, getInfo } from "@/apis/admin";
import { getToken, setToken, removeToken } from "@/utils/frames/auth";
import router, { resetRouter } from "@router";

const state = {
  token: getToken(),
  tokenTime: 0,
  tokenType: "bearer",
  name: "",
  avatar: "",
  introduction: "",
  roles: [],
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_TOKEN_TIME: (state, tokentTime) => {
    state.tokenTime = tokentTime;
  },
  SET_TOKEN_TYPE: (state, type) => {
    state.tokenType = type || "bearer";
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles;
  },
};

const actions = {
  // user login
  login({ commit }, userInfo) {
    return new Promise((resolve, reject) => {
      login(userInfo)
        .then((response) => {
          const { code } = response;
          if (code == window.$global.success) {
            resolve();
          } else {
            reject();
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo()
        .then((response) => {
          const { result } = response;

          if (!result) {
            reject("Verification failed, please Login again.");
          }

          const { roleIds, name, avatar, introduction } = result;

          // roles must be a non-empty array
          if (!roleIds || roleIds.length <= 0) {
            reject("getInfo: roles must be a non-null array!");
          }

          commit("SET_ROLES", roleIds);
          commit("SET_NAME", name);
          commit("SET_AVATAR", avatar);
          commit("SET_INTRODUCTION", introduction);
          resolve(result);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout()
        .then(() => {
          commit("SET_TOKEN", "");
          commit("SET_ROLES", []);
          removeToken();
          resetRouter();

          // reset visited views and cached views
          // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
          dispatch("tagsView/delAllViews", null, { root: true });

          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  //获取token
  queryToken({ commit }) {
    return new Promise((resolve, reject) => {
      const fd = new FormData();
      fd.append("grant_type", "client_credentials");
      fd.append("client_id", "qizuo");
      fd.append("client_secret", "qizuo");
      token(fd)
        .then((response) => {
          const { access_token, token_type, expires_in } = response;
          if (access_token) {
            commit("SET_TOKEN", access_token);
            commit("SET_TOKEN_TIME", expires_in);
            commit("SET_TOKEN_TYPE", token_type);
            setToken(access_token);
            resolve();
          } else {
            reject();
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      commit("SET_TOKEN", "");
      commit("SET_ROLES", []);
      removeToken();
      resolve();
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
