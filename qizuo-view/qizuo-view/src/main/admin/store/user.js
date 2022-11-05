import { login, logout, token, getInfo } from "@/apis/user";

const state = {
  token: "",
  tokenTime: 0,
  tokenType: "bearer",
  id:'',
  name: "",
  avatar: "",
  introduction: "",
  roles: [],
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
    window.localStorage.setItem(window.$global.tokenName,token);
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
  SET_ID: (state, id) => {
    state.id = id;
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
  login({ commit,dispatch }, userInfo) {
    return new Promise((resolve, reject) => {
      //登录成功，直接solve
      if(state.id){
        resolve();
        return;
      }
      //不成功就需要登录、获取用户信息
      login(userInfo)
        .then(({data}) => {
          if(data){
            //查询用户信息
            dispatch("getInfo").then(()=>{
              //定时获取token
              setTimeout(() => {
                dispatch("queryToken");
              }, 250000);
            })
            resolve();
          }else{
            reject();
          }
        })
    });
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo()
        .then(({data}) => {
          if(data){
            const { roleIds, baseId, userName, photo, remarks } = data.result;
            commit("SET_ROLES", roleIds);
            commit("SET_ID", baseId);
            commit("SET_NAME", userName);
            commit("SET_AVATAR", photo);
            commit("SET_INTRODUCTION", remarks);
            resolve();
          }else{
            reject();
          }
        })
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
        .then(({data}) => {
          if(data){
            const { access_token, token_type, expires_in } = data.result;
            commit("SET_TOKEN", access_token);
            commit("SET_TOKEN_TIME", expires_in);
            commit("SET_TOKEN_TYPE", token_type);
            resolve();
          }else{
            reject();
          }
        })
    });
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout()
        .then(({data}) => {
          if(data){
            commit("SET_TOKEN", "");
            commit("SET_ROLES", []);
            window.localStorage.setItem(window.$global.tokenName,'');
            resolve();
          }else{
            reject();
          }
        })
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};