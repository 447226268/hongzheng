<template>
  <div class="orderinsert">

    <div class="step">
      <el-steps :active="active" align-center>
        <el-step title="确认学员" description="输入姓名或学号"></el-step>
        <el-step title="订单添加" description="添加学员订单"></el-step>
        <el-step title="提交" description="确认并提交学员订单"></el-step>
      </el-steps>
    </div>

    <div v-if="active===1">
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
        <ortable ref="ortable" v-on:confirm="confirm"></ortable>
      </div>
    </div>

    <div v-if="active!==1">
      <div class="main">
        <div class="form" v-if="active===2">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="学号" prop="sid">
              <el-input v-model="ruleForm.sid" disabled></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="ruleForm.name" disabled></el-input>
            </el-form-item>
            <el-form-item label="订单类型" prop="type">
              <el-select v-model="ruleForm.type">
                <el-option label="续费" value="续费"></el-option>
                <el-option label="开课" value="开课"></el-option>
                <el-option label="退课" value="退课"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="ruleForm.type !== '退课'" label="办卡类型" prop="办卡类型">
              <el-select v-model="ruleForm.cardtype">
                <el-option label="日卡" value="日卡"></el-option>
                <el-option label="月卡" value="月卡"></el-option>
                <el-option label="季卡" value="季卡"></el-option>
                <el-option label="半年卡" value="半年卡"></el-option>
                <el-option label="年卡" value="年卡"></el-option>
                <el-option label="私教卡" value="私教卡"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="学费金额" prop="money">
              <el-input v-model="ruleForm.money"></el-input>
            </el-form-item>
            <el-form-item label="处理人" prop="handler">
              <el-input v-model="ruleForm.handler"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="reason">
              <el-input type="textarea" :rows="10" v-model="ruleForm.reason"></el-input>
            </el-form-item>
            <el-form-item label="缴费方式" prop="way">
              <el-select v-model="ruleForm.way">
                <el-option label="微信" value="微信"></el-option>
                <el-option label="支付宝" value="支付宝"></el-option>
                <el-option label="现金" value="现金"></el-option>
                <el-option label="刷卡" value="刷卡"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <div class="pre-next-but">
          <el-button @click="back">上一步</el-button>
          <el-button v-if="active===3" @click="submit" type="primary">提交</el-button>
          <el-button v-else @click="next" type="primary">下一步</el-button>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import {
    insert
  } from "@/api/order.js"
  import ortable from "./components/table.vue"
  export default {
    name: 'orderinsert',
    data() {
      return {
        active: 1,
        name: null,
        id: null,
        ruleForm: {
          name: null,
          sid: null,
          rid: null,
          cardtype: null,
          money: null,
          handler: null,
          reason: null,
          way: null,
        },
        rules: {
          // name: [{
          //     required: true,
          //     message: '请输入活动名称',
          //     trigger: 'blur'
          //   },
          //   {
          //     min: 3,
          //     max: 5,
          //     message: '长度在 3 到 5 个字符',
          //     trigger: 'blur'
          //   }
          // ],
          // region: [{
          //   required: true,
          //   message: '请选择活动区域',
          //   trigger: 'change'
          // }],
          // date1: [{
          //   type: 'date',
          //   required: true,
          //   message: '请选择日期',
          //   trigger: 'change'
          // }],
          // date2: [{
          //   type: 'date',
          //   required: true,
          //   message: '请选择时间',
          //   trigger: 'change'
          // }],
          // type: [{
          //   type: 'array',
          //   required: true,
          //   message: '请至少选择一个活动性质',
          //   trigger: 'change'
          // }],
          // resource: [{
          //   required: true,
          //   message: '请选择活动资源',
          //   trigger: 'change'
          // }],
          // desc: [{
          //   required: true,
          //   message: '请填写活动形式',
          //   trigger: 'blur'
          // }]
        }
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
      },
      confirm(row) {
        console.log(row)
        this.ruleForm.name = row.name
        this.ruleForm.sid = row.id
        this.ruleForm.rid = row.rid
        this.active = 2
      },
      back() {
        if (this.active === 1) {
          return
        } else {
          this.active -= 1
        }
      },
      next() {
        if (this.active === 3) {
          return
        } else {
          this.active += 1
        }
      },
      async submit() {
        await insert(this.ruleForm)
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

      .form {
        max-width: 800px;
        padding: 30px 0;
        margin: auto;
      }

      .pre-next-but {
        margin-top: 60px;
        text-align: center;

        .el-button {
          width: 150px;
          margin-right: 50px;
        }
      }

    }
  }
</style>