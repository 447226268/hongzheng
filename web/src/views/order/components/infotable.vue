<template>
  <div class="ordertablet">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="id">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="学生姓名">
              <span>{{ props.row.student.name }}</span>
            </el-form-item>
            <el-form-item label="订单金额">
              <span>{{ props.row.money }}</span>
            </el-form-item>
            <el-form-item label="订单类型">
              <span>{{ props.row.type }}</span>
            </el-form-item>
            <el-form-item label="录入人">
              <span>{{ props.row.handler }}</span>
            </el-form-item>
            <el-form-item label="方式">
              <span>{{ props.row.way }}</span>
            </el-form-item>
            <el-form-item label="所属道馆">
              <span>{{ props.row.room.name }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="编号" align='center' prop="id"></el-table-column>
      <el-table-column label="学生姓名" align='center' prop="student.name"></el-table-column>
      <el-table-column label="订单金额" align='center' prop="money"></el-table-column>
      <el-table-column label="订单类型" align='center' prop="type"></el-table-column>
      <el-table-column label="录入人" align='center' prop="handler"></el-table-column>
      <el-table-column label="方式" align='center' prop="way"></el-table-column>
      <el-table-column label="状态" align='center'>
        <template slot-scope="scope">
          <el-tag :type="scope.row.state === '正常' ? 'success' : 'danger'" effect="dark">{{scope.row.state}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="日期" align='center' prop="date"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: 'ordertablet',
    data() {
      return {
        tableData: null,
      }
    },
    props: ['value'],
    watch: {
      value: {
        handler(newVal) {
          newVal.forEach((e) => {
            if (e.student === null) {
              e.student = {
                name: null
              }
              e.student.name = null
            }
            if (e.room === null) {
              e.room = {
                name: null
              }
            }
          })
          this.tableData = newVal
        },
        deep: true
      }
    },
    created() {
      this.value.forEach((e) => {
        if (e.student === null) {
          e.student = {
            name: null
          }
        }
        if (e.room === null) {
          e.room = {
            name: null
          }
        }
      })
      this.tableData = this.value
    },
    methods: {
    }
  }
</script>

<style lang="scss" scoped>
  .ordertablet {
    .demo-table-expand {
      font-size: 0;
    }

    .demo-table-expand label {
      width: 90px;
      color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }
  }
</style>