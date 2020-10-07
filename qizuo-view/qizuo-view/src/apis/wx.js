import request from "@/utils/request";

//获取所有菜单信息  getSelfMenuInfo(仅自定义菜单的信息，无法查询个性化菜单信息)
export function getMenus(appid) {
    return request({
        url: `/wx/menu/${appid}/get`,
        method: "get",
    });
}

//添加菜单
export function doMenus(appid, data) {
    return request({
        url: `/wx/menu/${appid}/create`,
        method: "post",
        data,
    });
}

//删除菜单 去掉/${menuId}是删除所有
export function delMenus(appid, menuId) {
    return request({
        url: `/wx/menu/${appid}/delete/${menuId}`,
        method: "get",
    });
}

//获取所有消息配置信息
export function getMsgs() {
    return request({
        url: "/wx/msgKV/list",
        method: "post",
    });
}

//添加消息配置
export function doMsgs(data) {
    return request({
        url: "/wx/msgKV/iuDo",
        method: "post",
        data,
    });
}

//删除消息配置
export function delMsgs() {
    return request({
        url: "/wx/msgKV/delete",
        method: "post",
    });
}
