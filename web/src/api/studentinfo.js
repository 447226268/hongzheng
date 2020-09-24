/*
 * @Author: ld
 * @Date: 2020-09-15 10:07:11
 * @LastEditors: ld
 * @LastEditTime: 2020-09-16 14:29:27
 * @Description: file content
 * @FilePath: \hongzheng\web\src\api\studentinfo.js
 */
import ajax from './ajax';

/**
 * ajax(url,data,type)
 * @param url  请求地址
 * @param data  请求数据
 * @param type  请求方式
 */

export const getStudentNum = data => ajax("/student/number", data, "get")

export const getStudentData = data => ajax("/student/getRange", data, "get")

export const getStudentData1 = data => ajax("/student/statesearch", data, "get")

export const getRoomList = () => ajax("/room/getall", {}, "get")

export const getStudentInsert = () => ajax("/student/insert", {}, "post")

export const deleteStudent = data => ajax("/student/delete", data, "get")

export const queren = data => ajax("/student/queren", data, "get")

export const gettable = data => ajax("/home/table1", data, "get")


