import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const insert = data => ajax("/student/insert", data, "post")

export const getall = () => ajax("/room/getall", {}, "get")