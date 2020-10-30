<template>
  <div class="login">
    <div class="form">
      <div class="first">
      </div>
      <div class="seconed">
        <div class="title">后台登录<div>Welcome!</div>
        </div>

        <div class="main">
          <el-form ref="form" :model="form">
            <el-form-item>
              <el-input v-model="form.username" placeholder="用户名" prefix-icon="el-icon-user"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="form.password" placeholder="密码" prefix-icon="el-icon-lock" show-password>
              </el-input>
            </el-form-item>
          </el-form>
          <el-button type="primary" @click="onSubmit">登 陆</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import {
    login
  } from "@/api/user.js"
  export default {
    name: 'login',
    data() {
      return {
        form: {
          username: '',
          password: '',
        }
      }
    },
    created() {
      var _self = this;
      document.onkeydown = function (e) {
        console.log(e)
        var key = window.event.keyCode;
        if (key == 13) {
          _self.onSubmit();
        }
      }
    },
    methods: {
      async Login() {
        let result = (await login(this.form)).result
        this.$store.dispatch('userName', result.name)
        this.$store.dispatch('token', result.level)
        this.$router.push('/')
      },
      onSubmit() {
        this.Login()
      }
    }
  }
</script>

<style scoped lang="scss">
  .login {
    width: 100vw;
    height: 100vh;
    background: url('../assets/images/login.jpg') no-repeat 100%;
    background-size: cover;

    .form {
      position: fixed;
      left: 50%;
      top: 50%;
      -ms-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      width: 900px;
      height: 600px;
      display: flex;

      .first {
        height: 100%;
        width: 50%;
        background: url('../assets/images/login_1.jpg') no-repeat 50%;
        opacity: 0.7;
        background-size: cover;
      }

      .seconed {
        height: 100%;
        width: 50%;
        background-color: #fff;

        .title {
          width: 100%;
          margin-top: 80px;
          margin-bottom: 80px;
          text-align: center;
          font-size: 40px;
          font-weight: 800;
          color: #f54e40;

          div {
            margin-top: 10px;
            font-size: 18px;
            color: #999;
            font-weight: 100;
          }
        }

        .main {
          padding: 10px 76px 76px;

          .el-button {
            margin-top: 50px;
            width: 300px;

          }
        }
      }
    }

  }
</style>