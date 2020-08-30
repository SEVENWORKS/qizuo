import request from "@/utils/request";
//登录
export function login(data) {
  return request({
    url: "/user/login",
    method: "post",
    data,
  });
}

//登出
export function logout(data) {
  return request({
    url: "/user/logout",
    method: "post",
    data,
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
