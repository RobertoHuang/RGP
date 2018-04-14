<template>
  <el-form :model="loginForm" :rules="loginFormRule" ref="loginForm" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="login" :loading="logining">登录</el-button>
      <!-- <el-button @click.native.prevent="handleReset2">重置</el-button> -->
    </el-form-item>
  </el-form>
</template>

<script type="text/ecmascript-6">
  import {requestLogin} from '../../api/index';

  export default {
    data() {
      return {
        checked: true,
        logining: false,
        loginForm: {
          username: 'admin',
          password: '123456'
        },
        loginFormRule: {
          username: [
            {
              required: true,
              message: '请输入账号',
              trigger: 'blur'
            },
            //{ validator: validaePass }
          ],
          password: [
            {
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            //{ validator: validaePass2 }
          ]
        }
      };
    },
    methods: {
      login() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            var loginParams = {
              username: this.loginForm.username,
              password: this.loginForm.password
            };
            this.logining = true;
            requestLogin(loginParams).then(response => {
              if (response.status == 200) {
                console.log("登录成功");
              } else {
                console.log(response.status);
              }
              this.logining = false;
            });
          } else {
            console.log('error submit!!!');
            return false;
          }
        });
      }
    },
    reset() {
      this.$refs.loginForm.resetFields();
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .login-container {
    width: 350px;
    background: #fff;
    margin: 180px auto;
    border-radius: 5px;
    -moz-border-radius: 5px;
    border: 1px solid #eaeaea;
    -webkit-border-radius: 5px;
    background-clip: padding-box;
    padding: 35px 35px 15px 35px;
    box-shadow: 0 0 25px #cac6c6;

    .title {
      color: #505458;
      text-align: center;
      margin: 0px auto 40px auto;
    }

    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>
