<template>
  <div id="layout">
    <el-container>
      <el-aside width="auto">
        <navmenu></navmenu>
      </el-aside>
      <el-container>
        <el-header>
          <hzheader></hzheader>
        </el-header>
        <el-main>
          <transition name="el-fade-in-linear">
            <router-view />
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import navmenu from "./navmenu"
  import hzheader from "./header"
  export default {
    name: 'layout',
    data() {
      return {

      }
    },
    components: {
      navmenu,
      hzheader
    }
  }
</script>

<style>
  .el-header {
    background-color: #ffffff;
    line-height: 60px;
  }

  .el-aside {
    min-height: 100%;
  }

  .el-main {
    background-color: #f5f7f8;
  }

  .el-container {
    min-width: 1200px;
    min-height: 100vh;
  }
</style>