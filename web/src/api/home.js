import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */


export const getRoomList = () => ajax("/room/getall", {}, "get")

export const insert = data => ajax("/student/insert", data, "post")



