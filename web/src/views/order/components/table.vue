<template>
  <div class="ordertablet">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="头像" align='center'>
        <template slot-scope="scope">
          <el-image style="width: 70px;height:auto;border-radius: 100px;" :src="scope.row.image"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="学号" align='center' prop="id"></el-table-column>
      <el-table-column label="姓名" align='center' prop="name"></el-table-column>
      <el-table-column label="年龄" align='center' prop="age"></el-table-column>
      <el-table-column label="性别" align='center' prop="gender"></el-table-column>
      <el-table-column label="所在道馆" align='center' prop="room.name"></el-table-column>
      <el-table-column label="教练" align='center' prop="coach"></el-table-column>
      <el-table-column label="状态" align='center' prop="state"></el-table-column>
      <el-table-column label="操作" align='center'>
        <template slot-scope="scope">
          <el-button size="mini" @click="handle(scope.row)">确认</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {
    getbyname,
    getbyid
  } from "@/api/order.js"
  export default {
    name: 'ordertablet',
    data() {
      return {
        tableData: null,
      }
    },
    mouted() {
      
    },
    methods: {
      async getByName(name) {
        this.tableData = (await getbyname({
          name: name
        })).result
      },
      async getById(id) {
        this.tableData = [(await getbyid({
          id: id
        })).result]
      },
      handle(row){
        this.$emit('confirm', row)
      },
    }
  }
</script>

<style>
  .ordertablet {}
</style>