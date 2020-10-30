//简单的封装一下axios请求
import axios from 'axios'

import {
    Message,
    // MessageBox
    Loading
} from 'element-ui'

let loading
let needLoadingRequestCount = 0 // 声明一个对象用于存储请求个数
function startLoading() {
    loading = Loading.service({
        lock: true,
        text: '努力加载中...',
        spinner: 'gauge-loader',
        background: 'hsla(0,0%,100%,.8)',
        target: document.querySelector('.loading-area') // 设置加载动画区域
    })
}

function endLoading() {
    loading.close()
}

function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading()
    }
    needLoadingRequestCount++
}

function hideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return
    needLoadingRequestCount--
    if (needLoadingRequestCount === 0) {
        endLoading()
    }
}

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API //默认所有请求地址添加baseurl
    // baseURL: "/front",
})


// 请求前需要处理的参数  -- 比如：token
service.interceptors.request.use(
    config => {
        showFullScreenLoading()
        return config;
    },
    error => {
        hideFullScreenLoading()
        Promise.reject(error);
    }
);
// 响应后需要处理的参数  -- 比如：判断token失效
service.interceptors.response.use(
    response => { //响应成功后处理xx
        let code = response.data.code;
        if (response.status === 200 && (code === 100 || code === 0)) {
            setTimeout(() => {
                hideFullScreenLoading()
            }, 500)
            return Promise.resolve(response.data);
        }
        if (code == -100) {
            window.location.href = '/login';
        } else {
            Message.error(response.data.msg);
        }
        setTimeout(() => {
            hideFullScreenLoading()
        }, 500)
        return Promise.reject(response.data.msg);
    },
    error => { //响应失败后处理xx
        setTimeout(() => {
            hideFullScreenLoading()
        }, 500)
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