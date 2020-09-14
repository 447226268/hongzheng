<template>
  <div class="orderinfo">
    <div class="table">
      <div class="ordertablet">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="意向程度">
                  <span>{{ props.row.degree }}</span>
                </el-form-item>
                <el-form-item label="跟踪状态">
                  <span>{{ props.row.state  }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="编号" align='center' prop="id"></el-table-column>
          <el-table-column label="性别" align='center' prop="gender"></el-table-column>
          <el-table-column label="生日" align='center' prop="birthday"></el-table-column>
          <el-table-column label="手机号" align='center' prop="telephone"></el-table-column>
          <el-table-column label="咨询校区" align='center' prop="room.name"></el-table-column>
          <!-- <el-table-column label="跟踪状态" align='center'>
            <template slot-scope="scope">
              <el-tag :type="scope.row.state === '正常' ? 'success' : 'danger'" effect="dark">{{scope.row.state}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="意向程度" align='center' prop="degree"></el-table-column> -->
          <el-table-column label="录入人" align='center' prop="handler"></el-table-column>
          <el-table-column label="录入时间" align='center' prop="date"></el-table-column>
          <el-table-column label="线索" align='center' prop="info"></el-table-column>
          <el-table-column label="是否试听" align='center' prop="islisten"></el-table-column>
        </el-table>
      </div>
      <el-pagination class="pagination" background layout="prev, pager, next" :total="total" :current-page="current"
        @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'home',
    data() {
      return {
        current: 1,
        total: 1000,
        size: 10,
        tableData: [],
      }
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
  }
</style>