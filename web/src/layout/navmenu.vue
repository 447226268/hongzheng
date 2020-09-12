<template>
  <div id="layout">
    <el-menu router default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">

      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">Home</span>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>学员信息</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="2-1" route="/studentinfo">学员基本信息</el-menu-item>
          <el-menu-item index="2-2" route="/orderinfo">订单信息</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>信息录入</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="3-1">学员信息添加</el-menu-item>
          <el-menu-item index="3-2">学员信息修改</el-menu-item>
          <el-menu-item index="3-3">订单添加</el-menu-item>
          <el-menu-item index="3-4">订单修改</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>馆长信息</span>
        </template>
        <el-menu-item-groupd>
          <el-menu-item index="4-1">馆长信息</el-menu-item>
          <el-menu-item index="4-2">信息添加</el-menu-item>
          <el-menu-item index="4-3">道观添加、分配</el-menu-item>
        </el-menu-item-groupd>
      </el-submenu>

      <el-menu-item index="5">
        <i class="el-icon-menu"></i>
        <span slot="title">流水管理</span>
      </el-menu-item>

      <el-menu-item index="6">
        <i class="el-icon-menu"></i>
        <span slot="title">退出</span>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
  export default {
    name: 'navmenu',
  }
</script>

<style>

</style>