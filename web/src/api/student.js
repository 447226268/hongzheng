
import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const insert = data => ajax("/student/insert", data, "post")

export const getall = () => ajax("/room/getall", {}, "get")

export const getbyid = data => ajax("/student/getbyid", data, "get")

export const change = data => ajax("/bstudent/change", data, "get")

export const update = data => ajax("/student/update", data, "post")

export const updatePic = process.env.VUE_APP_BASE_API + "/student/popo"

