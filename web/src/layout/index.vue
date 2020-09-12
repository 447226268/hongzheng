<template>
  <div id="layout">
    <el-container>
      <el-aside width="200px">
        <navmenu></navmenu>
      </el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import navmenu from "./navmenu"
  export default {
    name: 'layout',
    components: {
      navmenu
    }
  }
</script>

<style>
  .el-header,
  .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    height: 100%;
  }

  .el-main {
    background-color: #f0f2f5;

  }

  .el-container {
    min-width: 1200px;
    height: 100vh;
  }
</style>