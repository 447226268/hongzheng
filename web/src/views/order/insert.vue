<template>
  <div class="orderinsert">

    <div class="step">
      <el-steps :active="active" align-center>
        <el-step title="确认学员" description="输入姓名或学号"></el-step>
        <el-step title="订单添加" description="添加学员订单"></el-step>
        <el-step title="提交" description="确认并提交学员订单"></el-step>
      </el-steps>
    </div>

    <div class="search">
      <div class="itme">
        <el-input v-model="name" placeholder="请输入学生姓名"></el-input>
        <el-button type="primary" @click="getByName">搜 索</el-button>
      </div>
      <div class="itme">
        <el-input v-model="id" placeholder="请输入学生学号"></el-input>
        <el-button type="primary" @click="getById">搜 索</el-button>
      </div>
    </div>

    <div class="main">
      <ortable ref="ortable"></ortable>
    </div>

  </div>
</template>

<script>
  import ortable from "./components/table.vue"
  export default {
    name: 'orderinsert',
    data() {
      return {
        active: 1,
        name: null,
        id: null,
      }
    },
    mounted() {

    },
    methods: {
      getByName() {
        this.$refs['ortable'].getByName(this.name)
      },
      getById() {
        this.$refs['ortable'].getById(this.id)
      }
    },
    components: {
      ortable,
    }
  }
</script>

<style lang="scss" scoped>
  .orderinsert {

    .step {
      padding: 24px 0 24px 0;
      background-color: #fff;
    }

    .search {
      margin-top: 24px;
      padding: 24px 0 24px 0;
      background-color: #fff;
      display: flex;
      justify-content: center;

      .itme {
        width: 500px;
        display: flex;
        margin-right: 40px;
      }

      .el-button--primary {
        height: 32px;
        border-radius: 0px;
      }

      ::v-deep.el-input__inner {
        border-radius: 0px;
      }
    }

    .main {
      margin-top: 24px;
      padding: 24px 0 24px 0;
      background-color: #fff;
    }
  }
</style>