import request from "@/utils/request";
//获取用户信息
export function getInfo(token) {
  return request({
    url: "/vue-element-admin/user/info",
    method: "get",
    params: { token },
  });
}
