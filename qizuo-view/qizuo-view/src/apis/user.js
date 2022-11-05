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
    url: "/oauth/token",
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

//修改

//用户信息修改
export function doUsers(data) {
  return request({
    url: "/user/user/iuDo",
    method: "post",
    data,
  });
}

export function delUsers(data) {
  return request({
    url: "/user/user/delete",
    method: "post",
    data,
  });
}

//角色信息修改
export function doRoles(data) {
  return request({
    url: "/user/role/iuDo",
    method: "post",
    data,
  });
}

export function delRoles(data) {
  return request({
    url: "/user/role/delete",
    method: "post",
    data,
  });
}

//菜单信息修改
export function doMenus(data) {
  return request({
    url: "/user/menu/iuDo",
    method: "post",
    data,
  });
}

export function delMenus(data) {
  return request({
    url: "/user/menu/delete",
    method: "post",
    data,
  });
}

