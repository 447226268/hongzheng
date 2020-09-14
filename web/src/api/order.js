import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getbyname = data => ajax("/student/getbyname", data, "get")

export const getbyid = data => ajax("/student/getbyid", data, "get")

export const insert = data => ajax("/order/insert", data, "post")

export const remove = data => ajax("/order/delete", data, "get")

export const updatestate = data => ajax("/order/updatestate", data, "get")

export const number = () => ajax("/order/number", {}, "get")

export const getrange = data => ajax("/order/getRange", data, "get")

export const getorderbyid = data => ajax("/order/getbyid", data, "get")

export const update = data => ajax("/order/update", data, "post")