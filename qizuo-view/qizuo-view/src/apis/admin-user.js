import request from "@/utils/request";

//用户信息修改
export function doUsers(data) {
  return request({
    url: "/userAdmin/user/iuDo",
    method: "post",
    data,
  });
}

export function delUsers(data) {
  return request({
    url: "/userAdmin/user/delete",
    method: "post",
    data,
  });
}

//角色信息修改
export function doRoles(data) {
  return request({
    url: "/userAdmin/role/iuDo",
    method: "post",
    data,
  });
}

export function delRoles(data) {
  return request({
    url: "/userAdmin/role/delete",
    method: "post",
    data,
  });
}

//菜单信息修改
export function doMenus(data) {
  return request({
    url: "/userAdmin/menu/iuDo",
    method: "post",
    data,
  });
}

export function delMenus(data) {
  return request({
    url: "/userAdmin/menu/delete",
    method: "post",
    data,
  });
}
