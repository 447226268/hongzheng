//简单的封装一下axios请求
// import localStore from "store";
import axios from 'axios'

import {
  Message,
  MessageBox
} from 'element-ui'

const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API //默认所有请求地址添加baseurl
  baseURL: "/front",
})


// 请求前需要处理的参数  -- 比如：token
service.interceptors.request.use(
  config => {
    // let Token = store.getters.token;
    // config.headers['Authorization'] = Token;
    return config;
  },
  error => {
    Promise.reject(error);
  }
);
// 响应后需要处理的参数  -- 比如：判断token失效
service.interceptors.response.use(
  response => { //响应成功后处理xx
    let code = response.data.code;
    if (response.status === 200 && (code === 100 || code === 0)) {
      return Promise.resolve(response.data);
    }
    if (code == -100) {
      MessageBox.alert('用户信息错误，请重新登陆。', '提示', {
        confirmButtonText: '知道了',
        showClose: false,
        callback: () => {
          window.location.href = '/login';
        }
      });
    } else {
      Message.error(response.data.msg);
    }
    return Promise.reject(response.data.msg);
  },
  error => { //响应失败后处理xx
    return Promise.reject(error);
  }
);

//外部使用axios
export default function ajax(url, data = {}, type = 'get') {
  if ((type).toLocaleLowerCase() === "get") { //get请求
    return service({
      url,
      method: type,
      params: data
    })
  } else if ((type).toLocaleLowerCase() === "post") { //post请求
    return service({
      url,
      method: type,
      data
    })
  } else { //delete/put请求
    return service({
      url,
      method: type,
      params: data
    })
  }

}