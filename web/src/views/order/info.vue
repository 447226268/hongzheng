<template>
  <div class="orderinfo">
    <div class="table">
      <infotable v-bind:value="tableData" ref="hztable" v-on:edit="editPlace" v-on:remove="removePlace"
        v-on:freezes="freezesPlace"></infotable>
      <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :current-page="current"
        @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import infotable from "./components/infotable"
  import {
    number,
    getrange,
  } from "@/api/order.js"
  export default {
    name: 'orderinfo',
    data() {
      return {
        current: 1,
        total: null,
        size: 10,
        tableData: [],
      }
    },
    created() {
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
        this.getNumber()
        this.current = page
        this.getRange()
      },
    },
    components: {
      infotable
    }
  }
</script>

<style lang="scss" scoped>
  .orderinfo {

    .table {
      padding: 60px 24px 24px 24px;
      background-color: #fff;

      .pagination {
        margin-top: 40px;
        margin-bottom: 20px;
        text-align: center;
      }
    }
  }
</style>