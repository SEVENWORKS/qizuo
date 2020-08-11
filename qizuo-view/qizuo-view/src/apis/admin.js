import request from "@/utils/request";
//登录登出
export function login(data) {
  return request({
    url: "/vue-element-admin/user/login",
    method: "post",
    data,
  });
}

export function logout() {
  return request({
    url: "/vue-element-admin/user/logout",
    method: "post",
  });
}
