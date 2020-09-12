import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getbyname = data => ajax("/student/getbyname", data, "get")

export const getbyid = data => ajax("/student/getbyid", data, "get")


