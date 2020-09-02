import request from "@/utils/request";

//登录
export function login(data) {
  return request({
    url: "/user/login/login",
    method: "post",
    data,
  });
}

//登出
export function logout() {
  return request({
    url: "/user/login/logout",
    method: "post",
  });
}

//token
export function token(data) {
  return request({
    url: "/user/oauth/token",
    method: "post",
    data,
  });
}

//token
export function author(data) {
  return request({
    url: "/user/oauth/authorize",
    method: "post",
    data,
  });
}

//获取用户信息
export function getInfo() {
  return request({
    url: "/user/user/single",
    method: "post",
  });
}

export function getUser(data) {
  return request({
    url: "/user/user/query",
    method: "post",
    data,
  });
}

export function getUsers(data) {
  return request({
    url: "/user/user/list",
    method: "post",
    data,
  });
}

export function getUsersPage(data) {
  return request({
    url: "/user/user/page",
    method: "post",
    data,
  });
}

//获取菜单信息
export function getMenu(data) {
  return request({
    url: "/user/menu/query",
    method: "post",
    data,
  });
}

export function getMenus(data) {
  return request({
    url: "/user/menu/list",
    method: "post",
    data,
  });
}

export function getMenusEach(data) {
  return request({
    url: "/user/menu/qEachList",
    method: "post",
    data,
  });
}

export function getMenusPage(data) {
  return request({
    url: "/user/menu/page",
    method: "post",
    data,
  });
}

//获取角色信息
export function getRole(data) {
  return request({
    url: "/user/role/query",
    method: "post",
    data,
  });
}

export function getRoles(data) {
  return request({
    url: "/user/role/list",
    method: "post",
    data,
  });
}

//图片验证码
export function getImgCheck(data) {
  return request({
    url: "/user/validateCode/imgCheck",
    method: "post",
    data,
  });
}
