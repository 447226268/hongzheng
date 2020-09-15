<!--
 * @Author: ld
 * @Date: 2020-09-15 10:07:11
 * @LastEditors: ld
 * @LastEditTime: 2020-09-15 17:03:40
 * @Description: file content
 * @FilePath: \hongzheng\web\src\layout\navmenu.vue
-->
<template>
  <div id="layout">
    <el-menu router default-active="1" class="el-menu-vertical-demo"
      :collapse="$store.getters.flag === 'false' ? false : true">

      <el-image class="logo" v-if="$store.getters.flag === 'false' ? true : false" :src="url" fit="fit"></el-image>

      <el-menu-item index="1" route="/home">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>学员信息</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="2-1" route="/studentinfo">学员基本信息</el-menu-item>
          <el-menu-item index="2-2" route="/orderinfo">订单信息</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-s-claim"></i>
          <span>信息录入</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="3-1" route="/studentinsert">学员信息添加</el-menu-item>
          <el-menu-item index="3-2" route="/orderinsert">订单添加</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-s-management"></i>
          <span>道馆信息</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="4-1" route="/placeinfo">道馆详情</el-menu-item>
          <el-menu-item index="4-2" route="/memberinfo">馆长信息</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-menu-item index="5" route="/manage">
        <i class="el-icon-s-flag"></i>
        <span slot="title">订单管理</span>
      </el-menu-item>

      <el-menu-item @click="out">
        <i class="el-icon-remove"></i>
        <span slot="title">退出</span>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
  import {
    logout
  } from "@/api/user.js"
  export default {
    name: 'navmenu',
    data() {
      return {
        url: require('@/assets/images/logo.jpg')
      }
    },
    methods: {
      async out() {
        this.$router.push('/login')
        await logout()
      }
    }

  }
</script>

<style scoped>
  .el-menu-vertical-demo {
    height: 100vh;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 250px;
    height: 100vh;
  }

  .logo {
    width: 200px;
    height: auto;
    margin-top: 70px;
    margin-left: 25px;
    margin-bottom: 30px;
  }
</style>