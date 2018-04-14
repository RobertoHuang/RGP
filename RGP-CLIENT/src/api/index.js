import axios from 'axios';

let base = '';

/****************************** user-center相关API Start ******************************/
/* 请求登录*/ export const requestLogin = params => { return axios.get(`${base}/api/login`, params).then(response => response.data); };
/****************************** user-center相关API End ******************************/
