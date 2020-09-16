<template>
  <div class="header">
    <div class="first">
      <i :class="$store.getters.flag === 'false' ? 'el-icon-s-fold' : 'el-icon-s-unfold'" @click="change"></i>
      <span>|</span>
      <span>{{item[$route.name]}}</span>
    </div>
    <div>
      <span>欢迎您，{{$store.getters.userName}}</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'header',
    data() {
      return {
        item: {
          Home: '首页',
          Studentinfo: '学员基本信息',
          Orderinfo: '订单信息',
          Studentinsert: '学员信息添加',
          Studentedit: '学员信息修改',
          Orderinsert: '订单添加',
          Orderedit: '订单修改',
          Memberinfo: '馆长信息',
          Memberinsert: '信息添加',
          Placeinfo: '道馆添加、分配',
          Manage: '流水管理',
        },
        flag: true,
      }
    },
    methods: {
      change() {
        if (this.$store.getters.flag === 'false') {
          this.$store.dispatch('flag', 'true')
        } else {
          this.$store.dispatch('flag', 'false')
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .header {
    display: flex;
    justify-content: space-between;
    line-height: 60px;
    height: 60px;

    .first {
      display: flex;
      align-items: center;

      i {
        font-size: 30px;
      }

      i:hover {
        cursor: pointer;
      }

      span {
        margin-left: 20px;
      }

    }
  }
</style>