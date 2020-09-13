import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const login = data => ajax("/admin/login", data, "post")

export const check = () => ajax("/admin/state", {}, "get")

export const logout = () => ajax("/admin/layout", {}, "get")

