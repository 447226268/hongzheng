import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getall = () => ajax("/room/getall", {}, "get")

export const insert = data => ajax("/room/insert", data, "post")

export const update = data => ajax("/room/update", data, "post")

export const remove = data => ajax("/room/delete", data, "get")

export const updatestate = data => ajax("/room/updatestate", data, "get")

export const getadminall = () => ajax("/admin/getall", {}, "get")



