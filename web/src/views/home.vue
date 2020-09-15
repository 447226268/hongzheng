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
              <i class="el-icon-s-marketing"></i>
              <span class="info-change-title">今日数据变动</span>
              <div class="info-allChange">
                <div class="info-singelChange">
                  <p class="changetitle">今日新增学员(人)</p>
                  <p class="changeNum">
                    <span>{{newStudentsAddedToday}}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">待续费学员(人)</p>
                  <p class="changeNum">
                    <span>{{numberOfStudents}}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">今日续费订单(条)</p>
                  <p class="changeNum">
                    <span>{{birthdayThisMonth}}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">待补齐尾款订单(条)</p>
                  <p class="changeNum">
                    <span>{{graduateStudent}}</span>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="6">
          <!-- 事项提醒 -->
          <div class="info-change">
            <div class="normal-box">
              <div class="title-inline">
                <div>
                  <i class="el-icon-message-solid"></i>
                  <span class="info-change-title">事项提醒</span>
                  <span style="font-weight: 800; margin-left: 20px">{{tipsNum}}</span>
                </div>
                <el-button type="primary" icon="el-icon-edit" circle></el-button>
              </div>
              
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
                  <span style="font-weight: 800; margin-left: 20px">{{tipsNum}}</span>
                </div>
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

        <el-col :span="24" v-if="showTable">
          <!-- 表格 -->
          <div class="info-change">
            <div class="normal-box2">
              <div class="title-inline">
                <div>
                  <i class="el-icon-notebook-2"></i>
                  <span class="info-change-title">意向学员</span>
                </div>
                <el-button
                  type="primary"
                  @click="btnClick"
                  icon="el-icon-edit"
                >增加信息</el-button>
              </div>
              <div class="table">
                <div class="degreetablet">
                  <el-table :data="tableData" style="width: 100%">
                    <el-table-column type="expand">
                      <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                          <el-form-item label="意向程度">
                            <span>{{ props.row.degree }}</span>
                          </el-form-item>
                          <el-form-item label="跟踪状态">
                            <span>{{ props.row.state }}</span>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column label="编号" align="center" prop="id"></el-table-column>
                    <el-table-column label="性别" align="center" prop="gender"></el-table-column>
                    <el-table-column label="生日" align="center" prop="birthday"></el-table-column>
                    <el-table-column label="手机号" align="center" prop="telephone"></el-table-column>
                    <el-table-column label="咨询校区" align="center" prop="room.name"></el-table-column>
                    <!-- <el-table-column label="跟踪状态" align='center'>
            <template slot-scope="scope">
              <el-tag :type="scope.row.state === '正常' ? 'success' : 'danger'" effect="dark">{{scope.row.state}}</el-tag>
            </template>
          </el-table-column>
                    <el-table-column label="意向程度" align='center' prop="degree"></el-table-column>-->
                    <el-table-column label="录入人" align="center" prop="handler"></el-table-column>
                    <el-table-column label="录入时间" align="center" prop="date"></el-table-column>
                    <el-table-column label="线索" align="center" prop="info"></el-table-column>
                    <el-table-column label="是否试听" align="center" prop="islisten"></el-table-column>
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
                <el-pagination
                  class="pagination"
                  background
                  layout="prev, pager, next"
                  :total="total"
                  :current-page="current"
                  @current-change="currentChange"
                ></el-pagination>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="24" v-if="!showTable">
          <!-- form表单 -->
          <div class="info-change">
            <div class="normal-box2">
              <div class="form">
                <el-form
                  :model="ruleForm"
                  :rules="rules"
                  ref="ruleForm"
                  label-width="100px"
                  class="demo-ruleForm"
                >
                  <el-form-item label="编号" prop="id">
                    <el-input v-model="ruleForm.id"></el-input>
                  </el-form-item>
                  <el-form-item label="性别" prop="gender">
                    <el-select v-model="ruleForm.gender">
                      <el-option label="男" value="男"></el-option>
                      <el-option label="女" value="女"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="生日" prop="birthday">
                    <el-date-picker type="date" v-model="ruleForm.birthday" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                  <el-form-item label="手机号" prop="telephone">
                    <el-input v-model="ruleForm.telephone"></el-input>
                  </el-form-item>
                  <el-form-item label="咨询校区" prop="rid">
                    <el-select v-model="ruleForm.rid">
                      <el-option
                        v-for="item in roomList"
                        :label="item.name"
                        :value="item.id"
                        :key="item.id"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="跟踪状态" prop="state">
                    <el-select v-model="ruleForm.state">
                      <el-option label="未跟踪" value="未跟踪"></el-option>
                      <el-option label="跟踪中" value="跟踪中"></el-option>
                      <el-option label="放弃跟踪" value="放弃跟踪"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="意向程度" prop="degree">
                    <el-select v-model="ruleForm.degree">
                      <el-option label="高" value="高"></el-option>
                      <el-option label="中" value="中"></el-option>
                      <el-option label="低" value="低"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="录入人" prop="handler">
                    <el-input v-model="ruleForm.handler"></el-input>
                  </el-form-item>
                  <el-form-item label="录入时间" prop="date">
                    <el-date-picker type="date" v-model="ruleForm.date" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                  <el-form-item label="线索" prop="info">
                    <el-input v-model="ruleForm.info"></el-input>
                  </el-form-item>
                  <el-form-item label="是否试听" prop="islisten">
                    <el-select v-model="ruleForm.gender">
                      <el-option label="是" value="是"></el-option>
                      <el-option label="否" value="否"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div class="pre-next-but">
                  <el-button @click="back">取消</el-button>
                  <el-button @click="insertStudent" type="primary">提交</el-button>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getRoomList, insert } from "@/api/home.js";
// insert接口没换，table数据
export default {
  name: "home",
  data() {
    return {
      current: 1,
      total: 1000,
      size: 10,
      tableData: [],
      currentroomid: 1,
      roomList: null,
      showTable: true,
      birthdayNum: 5,
      tipsNum: 4,
      tipsData: {},
      ruleForm: {
        id: null,
        gender: null,
        birthday: null,
        telephone: null,
        rid: null,
        state: null,
        degree: null,
        handler: null,
        date: null,
        info: null,
        islisten: null,
      },
      rules: {
        id: [
          {
            required: true,
            message: "编号不能为空",
            trigger: "blur",
          },
        ],
        telephone: [
          {
            required: true,
            message: "请输入联系电话",
            trigger: "blur",
          },
        ],
        rid: [
          {
            required: true,
            message: "请选择一个校区",
            trigger: "change",
          },
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
    btnClick() {
      this.showTable = !this.showTable;
    },
    back() {
      this.showTable = !this.showTable;
    },
    async insertStudent() {
      let data = {};
      for (let i in this.ruleForm) {
        data[i] = this.ruleForm[i];
      }
      if (data.birthday !== null) {
        data.birthday = this.$moment(data.birthday).format("YYYY-DD-MM");
      }

      await insert(data);
      this.$message({
        message: "提交成功！",
        type: "success",
      });
      this.showTable = !this.showTable;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    async handleDelete(index, row) {
      console.log(index, row);
      // await deleteStudent({ id: row.id });
      this.$message({
        message: "删除成功！",
        type: "success",
      });
      // await this.getStuNumber();
      // this.getStuRange();
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

    .title-inline {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
    }
  }

  .normal-box2 {
    padding: 16px 20px;
    background-color: #fff;
    height: auto;

    .title-inline {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
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

    .form {
      max-width: 800px;
      padding: 30px 0;
      margin: auto;

      .pre-next-but {
        margin-top: 60px;
        text-align: center;

        .el-button {
          width: 150px;
          margin-right: 50px;
        }
      }
    }

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

  .table {
    padding: 24px 24px 24px 24px;
    background-color: #fff;

    .pagination {
      margin-top: 40px;
      margin-bottom: 20px;
      text-align: center;
    }
  }

  .degreetablet {
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