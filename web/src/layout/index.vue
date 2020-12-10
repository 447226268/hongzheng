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
            <router-view/>
          </transition>
        </el-main>
        <el-footer height="30px">
          <div class="footer">
            <span>@2020 HZWD</span>
            <div>
              <el-link href="https://beian.miit.gov.cn" target="_blank" :underline="false">豫ICP备2020030532号-1</el-link>
            </div>
          </div>
        </el-footer>
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
    return {}
  },
  components: {
    navmenu,
    hzheader
  }
}
</script>

<style scoped lang="scss">
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

.footer {
  color: #606266;
  font-size: 15px;
  display: flex;
  height: 100%;
  justify-content: center;
  align-items: center;
  .el-link{
    margin-left: 10px;
    font-size: 15px;
  }
}

.el-container {
  min-width: 1200px;
  min-height: 100vh;
}
</style>
