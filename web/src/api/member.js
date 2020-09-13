import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getall = () => ajax("/admin/getall", {}, "get")

export const insert = data => ajax("/admin/insert", data, "post")

export const update = data => ajax("/admin/update", data, "post")

export const remove = data => ajax("/admin/delete", data, "get")

export const updatestate = data => ajax("/admin/updatestate", data, "get")