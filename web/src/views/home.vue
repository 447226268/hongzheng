<template>
  <div class="home">
    <!-- 统计分析和下拉框 -->
    <div class="show1">
      <el-row :gutter="20" type="flex" justify="space-between">
        <el-col>
          <p>首页</p>
        </el-col>
        <el-col align="right">
          <div>
            <span>校区：</span>
            <el-select v-model="value" :placeholder="roomList[0].name" @change="changeRoomId">
              <el-option
                v-for="item in roomList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 信息模块 -->
    <div class="show2">
      <el-row :gutter="20" justify="space-between">
        <el-col :span="10">
          <!-- 今日数据变动 -->
          <div class="info-change">
            <div class="normal-box">
              <i class="el-icon-s-data"></i>
              <span class="info-change-title">今日数据变动</span>
            </div>
          </div>
        </el-col>

        <el-col :span="8">
          <!-- 事项提醒 -->
          <div class="info-change">
            <div class="normal-box">
              <i class="el-icon-message-solid"></i>
              <span class="info-change-title">事项提醒</span>
            </div>
          </div>
        </el-col>

        <el-col :span="6">
          <!-- 生日学员 -->
          <div class="info-change">
            <div class="normal-box">
              <i class="el-icon-date"></i>
              <span class="info-change-title">生日学员</span>
            </div>
          </div>
        </el-col>

        <el-col :span="24">
          <!-- 今日课消 -->
          <div class="info-change">
            <div class="normal-box">
              <i class="el-icon-reading"></i>
              <span class="info-change-title">今日课消</span>
            </div>
          </div>
        </el-col>

        <el-col :span="24">
          <!-- 今日课业绩 -->
          <div class="info-change">
            <div class="normal-box">
              <i class="el-icon-money"></i>
              <span class="info-change-title">今日业绩</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getRoomList } from "@/api/studentinfo.js";
export default {
  name: "home",
  data() {
    this.chartSettings = {
      radius: [70, 90],
      offsetY: 120,
    };
    this.barExtend = {
      legend: {
        type: "scroll",
        orient: "vertical",
        height: "auto",
        top: 10,
        bottom: 10,
        right: 30,
      },
    };

    return {
      currentroomid: 1,
      roomList: null,
    };
  },
  created() {
    this.getRoom();
  },
  methods: {
    async getRoom() {
      let result = (await getRoomList()).result;
      this.roomList = result;
      this.currentroomid = result[0].id;
    },
    changeRoomId(id) {
      this.currentroomid = id;
      console.log(this.currentroomid);
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  overflow-x: hidden;
  width: 100% !important;
  box-sizing: border-box;

  .el-row {
    position: relative;
    box-sizing: border-box;
    box-sizing: border-box;
    width: 100%;
  }

  .normal-box {
    padding: 16px 20px;
    background-color: #fff;
    height: 270px;
  }

  .show1 {
    font-size: 14px;
    margin-bottom: 20px;
  }

  .show2 {
    width: 100%;
    position: relative;
    height: auto;
    zoom: 1;
    display: flex;
    flex-flow: row wrap;

    .info-change {
      margin-bottom: 24px;
    }
  }
}
</style>