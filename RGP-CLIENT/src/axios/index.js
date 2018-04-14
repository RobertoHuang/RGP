import axios from 'axios'
import { Message } from 'element-ui'

// axios全局超时设置
axios.defaults.timeout = 60000;

// 全局错误状态码响应处理
axios.interceptors.response.use(
  response => {
    // let token = response.headers.token;
    // store.commit(types.TOKEN, token);
    return response;
  },
  error => {
    if (error.response) {
      // 请求已发出，但服务器使用非2xx的状态码进行响应
      switch (error.response.status) {
        // 认证异常:消除token并跳转登录页面
        case 401:
          if(error.response.data.message) {
            Message.error({
              message: error.response.data.message
            })
          }
          // 认证异常:消除token并跳转登录页面
          break;
        case 504:
          Message.error({
            message: '服务器异常，请稍后重试'
          });
          break;
        default:
          // 其他状态码处理
          if(error.response.data.message) {
            Message.error({
              message: "服务器去撩妹啦，请稍后重试"
            })
          }
      }
    } else {
      Message.error({
        message: '无法获取服务器数据，请稍后重试'
      })
    }
    console.log(error);
    // 返回业务catch块处理
    return Promise.reject(error);
  });

export default axios;
