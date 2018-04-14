import Vue from 'vue'
import App from './App'
import axios from './axios'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI);

// 设置为false关闭生产模式下给出的提示
Vue.config.productionTip = false;

new Vue({
  el: '#app',
  axios: axios,
  router: router,
  components: { App },
  template: '<App/>'
});
