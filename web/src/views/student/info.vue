<template>
  <div class="sutdentinfo">
    <!-- 统计分析和下拉框 -->
    <div class="show1">
      <el-row :gutter="20" type="flex" justify="space-between">
        <el-col>
          <p>统计分析</p>
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

    <!-- 总体信息 -->
    <div class="show2">
      <el-row :gutter="20" justify="space-between">
        <el-col :span="7">
          <!-- 学员数据变动 -->
          <div class="info-change">
            <div class="normal-box">
              <span class="info-change-title">学员数据变动</span>
              <div class="info-allChange">
                <div class="info-singelChange">
                  <p class="changeNum">
                    <span>{{newStudentsAddedToday}}</span>
                  </p>
                  <p class="changetitle">今日新增学员</p>
                </div>
                <div class="info-singelChange">
                  <p class="changeNum">
                    <span>{{numberOfStudents}}</span>
                  </p>
                  <p class="changetitle">在读学员</p>
                </div>
                <div class="info-singelChange">
                  <p class="changeNum">
                    <span>{{birthdayThisMonth}}</span>
                  </p>
                  <p class="changetitle">本月生日学员</p>
                </div>
                <div class="info-singelChange">
                  <p class="changeNum">
                    <span>{{graduateStudent}}</span>
                  </p>
                  <p class="changetitle">毕业学员</p>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="9">
          <!-- 学员招生占比 -->
          <div class="info-change">
            <div class="normal-box">
              <span class="info-change-title">学员招生占比</span>
              <ve-ring :data="chartData" :settings="chartSettings" :extend="barExtend"></ve-ring>
            </div>
          </div>
        </el-col>

        <el-col :span="8">
          <!-- 生日学员 -->
          <div class="info-change">
            <div class="normal-box">
              <div class="title-inline">
                <div>
                  <i class="el-icon-date"></i>
                  <span class="info-change-title">生日学员</span>
                </div>
                <span style="font-weight: 800; margin-right: 20px">{{birthdayNum}}</span>
              </div>
              <el-table :data="birthTable" height="250px" border style="width: 100%">
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="birthday" label="生日"></el-table-column>
                <el-table-column prop="room.name" label="所属校区"></el-table-column>
                <el-table-column prop="telephone" label="电话"></el-table-column>
              </el-table>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 学生具体信息 -->
    <div class="show3">
      <el-row :gutter="20" justify="space-between">
        <el-col :span="24">
          <div class="show-table">
            <el-table :data="tableData" style="width: 100%" :current-page.sync="current">
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="学号">
                      <span>{{ props.row.id }}</span>
                    </el-form-item>
                    <el-form-item label="姓名">
                      <span>{{ props.row.name }}</span>
                    </el-form-item>
                    <el-form-item label="年龄">
                      <span>{{ props.row.age }}</span>
                    </el-form-item>
                    <el-form-item label="性别">
                      <span>{{ props.row.gender }}</span>
                    </el-form-item>
                    <!-- <el-form-item label="所在道馆">
                      <span>{{ props.row.room.name }}</span>
                    </el-form-item>-->
                    <el-form-item label="教练">
                      <span>{{ props.row.coach }}</span>
                    </el-form-item>
                    <el-form-item label="状态">
                      <span>{{ props.row.state }}</span>
                    </el-form-item>
                    <el-form-item label="生日">
                      <span>{{ props.row.birthday }}</span>
                    </el-form-item>
                    <el-form-item label="身份证号">
                      <span>{{ props.row.idnum }}</span>
                    </el-form-item>
                    <el-form-item label="办卡类型">
                      <span>{{ props.row.cardtype }}</span>
                    </el-form-item>
                    <el-form-item label="延期日期">
                      <span>{{ props.row.delaytime }}</span>
                    </el-form-item>
                    <el-form-item label="录入人">
                      <span>{{ props.row.handler }}</span>
                    </el-form-item>
                    <el-form-item label="办卡时间">
                      <span>{{ props.row.indate }}</span>
                    </el-form-item>
                    <el-form-item label="段位">
                      <span>{{ props.row.level }}</span>
                    </el-form-item>
                    <el-form-item label="金额">
                      <span>{{ props.row.money }}</span>
                    </el-form-item>
                    <el-form-item label="缴费方式">
                      <span>{{ props.row.way }}</span>
                    </el-form-item>
                    <el-form-item label="上课次数">
                      <span>{{ props.row.num }}</span>
                    </el-form-item>
                    <el-form-item label="结课日期">
                      <span>{{ props.row.outdate }}</span>
                    </el-form-item>
                    <el-form-item label="家长电话">
                      <span>{{ props.row.phone }}</span>
                    </el-form-item>
                    <el-form-item label="家长姓名">
                      <span>{{ props.row.pname }}</span>
                    </el-form-item>
                    <el-form-item label="剩余时间">
                      <span>{{ props.row.reday }}</span>
                    </el-form-item>
                    <el-form-item label="联系电话">
                      <span>{{ props.row.telephone }}</span>
                    </el-form-item>
                    <el-form-item label="学员类型">
                      <span>{{ props.row.type }}</span>
                    </el-form-item>
                    <br />
                    <el-form-item label="备注">
                      <span v-html="props.row.info"></span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column label="头像" align="center" width="100px">
                <template slot-scope="scope">
                  <el-image
                    style="width: 60px; height: 60px; border-radius: 100px;"
                    :src="scope.row.image"
                    fit="cover"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column label="学号" prop="id" align="center"></el-table-column>
              <el-table-column label="姓名" prop="name" align="center"></el-table-column>
              <el-table-column label="年龄" prop="age" align="center"></el-table-column>
              <el-table-column label="性别" prop="gender" align="center"></el-table-column>
              <el-table-column label="所在道馆" prop="room.name" align="center"></el-table-column>
              <el-table-column label="教练" prop="coach" align="center"></el-table-column>
              <el-table-column label="状态" prop="state" align="center"></el-table-column>
              <el-table-column label="操作" align="center" width="200px">
                <template slot-scope="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-popconfirm
                    style="margin-left:10px;"
                    title="请确定是否删除？"
                    @onConfirm="handleDelete(scope.$index, scope.row)"
                  >
                    <el-button slot="reference" size="mini" type="danger">删除</el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-col>

        <el-col style="text-align:center">
          <el-pagination
            class="pagination"
            background
            layout="total, prev, pager, next"
            :total="total"
            :current-page="current"
            @current-change="currentChange"
            @prev-click="prevClick"
            @next-click="nextClick"
          ></el-pagination>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {
  getStudentNum,
  getStudentData,
  deleteStudent,
  getRoomList,
} from "@/api/studentinfo.js";
export default {
  name: "sutdentinfo",
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
      current: 1,
      currentroomid: 1,
      total: null,
      size: 10,
      value: "",
      tableData: [],
      newStudentsAddedToday: -1,
      numberOfStudents: -1,
      birthdayThisMonth: -1,
      graduateStudent: -1,
      birthdayNum: 5,
      roomList: [
        {
          name: null,
          id: null,
        },
      ],
      chartData: {
        columns: ["日期", "访问用户"],
        rows: [
          { 日期: "1/1", 访问用户: 1393 },
          { 日期: "1/2", 访问用户: 3530 },
          { 日期: "1/3", 访问用户: 2923 },
          { 日期: "1/4", 访问用户: 1723 },
          { 日期: "1/5", 访问用户: 3792 },
          { 日期: "1/6", 访问用户: 4593 },
        ],
      },
      birthTable: [
        {
          name: "王小虎",
          birthday: "2016-05-03",
          room: {
            name: "校区1",
          },
          telephone: 123213,
        },
      ],
    };
  },
  mounted() {
    this.getStuNumber();
    this.getStuRange();
    this.getRoom();
  },
  watch: {
    current() {
      this.getStuNumber();
      this.getStuRange();
    },
  },
  methods: {
    async getStuNumber() {
      this.total = (await getStudentNum()).result;
    },
    async getStuRange() {
      this.tableData = (
        await getStudentData({
          number1: this.size * (this.current - 1),
          number2: this.size * this.current,
          number3: this.currentroomid,
        })
      ).result;
    },
    async getRoom() {
      let result = (
        await getRoomList({
          number1: this.currentroomid,
        })
      ).result;
      this.roomList = result;
      this.currentroomid = result[0].id;
    },
    handleEdit(index, row) {
      this.$router.push(`/studentedit/${row.id}`);
    },
    async handleDelete(index, row) {
      console.log(index);
      await deleteStudent({ id: row.id });
      this.$message({
        message: "删除成功！",
        type: "success",
      });
      await this.getStuNumber();
      this.getStuRange();
    },
    changeRoomId(id) {
      this.currentroomid = id;
      console.log(this.currentroomid);
      this.getStuNumber();
      this.getStuRange();
    },
    currentChange(page) {
      this.current = page;
    },
    prevClick(page) {
      this.current = page;
    },
    nextClick(page) {
      this.current = page;
    },
  },
};
</script>

<style scoped lang="scss">
.sutdentinfo {
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

    .title-inline {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
    }

    .show-line1 {
      height: 260px;

      .ve-line {
        height: 260px;
      }
    }
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

      .info-change-title {
        font-size: 16px;
        font-weight: 500;
        color: #333;
      }

      .info-allChange {
        height: 236px;
        text-align: center !important;
        display: flex;
        flex-flow: row wrap;
        align-items: center;

        .info-singelChange {
          padding-left: 12px;
          padding-right: 12px;
          display: block;
          box-sizing: border-box;
          width: 50%;
          text-align: center !important;

          .changeNum {
            font-size: 20px;
            margin: 0 0 8px 0;
          }

          .changetitle {
            font-size: 14px;
            color: #999;
          }
        }
      }
    }
  }

  .show3 {
    .show-table {
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
}
</style>