<template>
  <div class="manage">
    <div class="table">
      <hztable v-bind:value="tableData" ref="hztable" v-on:edit="editPlace" v-on:remove="removePlace"
        v-on:freezes="freezesPlace"></hztable>
      <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :current-page="current"
        @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {
    number,
    getrange
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
      currentChange(page) {
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