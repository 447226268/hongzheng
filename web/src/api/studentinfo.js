/*
 * @Author: ld
 * @Date: 2020-09-15 10:07:11
 * @LastEditors: ld
 * @LastEditTime: 2020-09-16 11:36:27
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

export const getStudentInfo = () => ajax("/student/getall", {}, "get")

export const getStudentNum = data => ajax("/student/number", data, "get")

export const getStudentData = data => ajax("/student/getRange", data, "get")

export const getRoomList = () => ajax("/room/getall", {}, "get")

export const getStudentInsert = () => ajax("/student/insert", {}, "post")

export const deleteStudent = data => ajax("/student/delete", data, "get")