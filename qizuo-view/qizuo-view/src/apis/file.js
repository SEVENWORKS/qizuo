import request from "@/utils/request";

//当前上传文件信息
export function getFile(data) {
  return request({
    url: "/file/file/query",
    method: "post",
    data,
  });
}

export function getFiles(data) {
  return request({
    url: "/file/file/list",
    method: "post",
    data,
  });
}

export function getFilesPage(data) {
  return request({
    url: "/file/file/page",
    method: "post",
    data,
  });
}

//获取上传文件日志信息
export function getFileLog(data) {
  return request({
    url: "/file/fileLog/query",
    method: "post",
    data,
  });
}

export function getFileLogs(data) {
  return request({
    url: "/file/fileLog/list",
    method: "post",
    data,
  });
}

export function getFileLogsPage(data) {
  return request({
    url: "/file/fileLog/page",
    method: "post",
    data,
  });
}
