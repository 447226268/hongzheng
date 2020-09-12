import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import localStore from 'store'

Vue.config.productionTip = false

Vue.prototype.localStore = localStore //引入localstorge插件

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/style/element-variables.scss'

Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
}).$mount('#app')