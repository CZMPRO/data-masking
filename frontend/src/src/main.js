import { createApp } from "vue";
import App from "./App.vue";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "./router";
import axios from 'axios'
import Vue from 'vue'

createApp(App)
    .use(router)
    .use(ElementPlus)
    .mount("#app");


// axios.defaults.baseURL = 'http://localhost:8085/api'
// Vue.prototype.$http = axios


// main.js
const observerErr = 'ResizeObserver loop completed with undelivered notifications.'
window.addEventListener('error', e => {
  if (e.message === observerErr) {
    e.stopImmediatePropagation()
  }
})

// axios.defaults.baseURL = '/api';
// axios.defaults.proxy = {
//   host: 'http://localhost',
//   port: 8085,
//   protocol: 'http'
// };

// 屏蔽 ResizeObserver 警告（开发环境用）
const realConsoleError = console.error
console.error = (...args) => {
  if (
    typeof args[0] === 'string' &&
    args[0].includes('ResizeObserver loop completed with undelivered notifications')
  ) {
    return
  }
  realConsoleError(...args)
}