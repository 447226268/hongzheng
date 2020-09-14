import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getStudentInfo = () => ajax("/student/getall", {}, "get")

export const getStudentNum = () => ajax("/student/number", {}, "get")

export const getStudentData = data => ajax("/student/getRange", data, "get")

export const getStudentInsert = () => ajax("/student/insert", {}, "post")

export const deleteStudent = data => ajax("/student/delete", data, "get")