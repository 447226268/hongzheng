import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */


export const getRoomList = () => ajax("/room/getall", {}, "get")

export const getBStudentNum = data => ajax("/bstudent/number", data, "get")

export const getBStudentData = data => ajax("/bstudent/getRange", data, "get")

export const insert = data => ajax("/bstudent/insert", data, "post")

export const update = data => ajax("/bstudent/update", data, "post")

export const deleteBStudent = data => ajax("/bstudent/delete", data, "get")

export const getbyid = data => ajax("/bstudent/getbyid", data, "get")

export const getTable1 = data => ajax("/home/table1", data, "get")

export const getall = data => ajax("/chart/getall", data, "get")





