<template>
  <div class="ordertablet">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="编号" align='center' prop="id"></el-table-column>
      <el-table-column label="姓名" align='center' prop="name"></el-table-column>
      <el-table-column label="用户名" align='center' prop="username"></el-table-column>
      <el-table-column label="密码" align='center' prop="password"></el-table-column>
      <el-table-column label="状态" align='center'>
        <template slot-scope="scope">
          <el-tag :type="scope.row.state === '正常' ? 'success' : 'danger'" effect="dark">{{scope.row.state}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="分配道馆" align='center' prop="rooms"></el-table-column>
      <el-table-column label="操作" align='center' width="250">
        <template slot-scope="scope">
          <el-button size="mini" @click="edit(scope.row)">修改</el-button>
          <el-button size="mini" type="warning" @click="freezes(scope.row)">冻结</el-button>
          <el-popconfirm style="margin-left:10px;" title="请确定是否删除？" @onConfirm="remove(scope.row)">
            <el-button slot="reference" size="mini" type="danger">删除</el-button>
          </el-popconfirm>
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