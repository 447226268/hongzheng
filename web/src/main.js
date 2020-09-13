import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import localStore from 'store'
import moment from 'moment'//导入文件 


import VCharts from 'v-charts'
Vue.use(VCharts)


import {
  check,
} from "@/api/user.js"

Vue.config.productionTip = false

Vue.prototype.localStore = localStore //引入localstorge插件

Vue.prototype.$moment = moment;//赋值使用

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/style/element-variables.scss'

Vue.use(ElementUI, {
  size: 'small', // 默认样式的大小
});

router.beforeEach(async (to, from, next) => {
  if (to.name !== 'Login') {
    let data = await check()
    if (data.code === 100) {
      next()
    } else {
      next({
        name: 'Login'
      })
    }
  } else {
    next()
  }
})


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
}).$mount('#app')