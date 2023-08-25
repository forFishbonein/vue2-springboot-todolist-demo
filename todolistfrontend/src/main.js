//引入Vue
import Vue from "vue";
//引入App
import App from "./App";
//引入element-ui组件库
import ElementUI from 'element-ui';
//引入element全部样式
import 'element-ui/lib/theme-chalk/index.css';
//使用element-ui
Vue.use(ElementUI)

//关闭Vue的生产提示
Vue.config.productionTip = false;

new Vue({
  el: "#app",
  render: (h) => h(App),
  beforeCreate() {
    //事件总线
    Vue.prototype.$bus = this;
  },
});
