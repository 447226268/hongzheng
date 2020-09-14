<template>
  <div class="manage">
    <div class="table">
      <hztable v-bind:value="tableData" ref="hztable" v-on:edit="editManage" v-on:remove="removeManage"
        v-on:freezes="freezesManage"></hztable>
      <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :current-page="current"
        @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {
    number,
    getrange,
    remove,
    updatestate
  } from "@/api/order.js"
  import hztable from "./components/table"
  export default {
    name: 'manage',
    data() {
      return {
        current: 1,
        total: null,
        size: 10,
        tableData: [],
      }
    },
    mounted() {
      this.getNumber()
      this.getRange()
    },
    methods: {
      async getNumber() {
        this.total = (await number()).result
      },
      async getRange() {
        this.tableData = (await getrange({
          number1: this.size * (this.current - 1),
          number2: this.size * this.current
        })).result
      },
      editManage(row) {
        this.$router.push(`/orderedit/${row.id}`)
      },
      async removeManage(row) {
        await remove({
          id: row.id
        })
        this.$message({
          message: '删除成功！',
          type: 'success'
        });
        this.getRange()
      },
      async freezesManage(row) {
        await updatestate({
          id: row.id
        })
        this.$message({
          message: '冻结成功！',
          type: 'success'
        });
        this.getRange()
      },
      currentChange(page) {
        this.getNumber()
        this.current = page
        this.getRange()
      },
    },
    components: {
      hztable,
    }
  }
</script>

<style lang="scss" scoped>
  .manage {

    .table {
      padding: 60px 24px 24px 24px;
      background-color: #fff;

      .add {
        margin-left: 20px;
        margin-bottom: 20px;
      }

      .el-button {
        width: 150px;
        border-radius: 0px;
      }

      .pagination {
        margin-top: 40px;
        margin-bottom: 20px;
        text-align: center;
      }
    }
  }
</style>