<template>
  <div class="ordertablet">
    <el-table :data="tableData" style="width: 100%">
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
      <el-table-column label="操作" align='center' width='250'>
        <template slot-scope="scope">
          <el-button size="mini" @click="edit(scope.row)">修改</el-button>
          <el-button size="mini" type="warning" @click="freezes(scope.row)">冻结</el-button>
          <el-button size="mini" type="danger" @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
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
          this.tableData = newVal
        },
        deep: true
      }
    },
    created() {
      this.tableData = this.value
    },
    methods: {
      edit(row) {
        this.$emit('edit', row)
      },
      remove(row) {
        this.$emit('remove', row)
      },
      freezes(row) {
        this.$emit('freezes', row)
      }
    }
  }
</script>

<style>
  .ordertablet {}
</style>