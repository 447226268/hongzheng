<template>
  <div class="login">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登 陆</el-button>
      </el-form-item>
    </el-form>
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
    methods: {
      async Login() {
        await login(this.form)
        this.$router.push('/')
      },
      onSubmit() {
        this.Login()
      }
    }
  }
</script>

<style>
  .login {
    width: 300px;
    margin-top: 100px;
    margin-left: 45%;
  }
</style>