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
            <el-select
              v-model="value"
              :placeholder="roomList[0].name"
              @change="changeRoomId"
            >
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
              <i class="el-icon-user"></i>
              <span class="info-change-title">今日数据变动</span>
              <div class="info-allChange">
                <div class="info-singelChange">
                  <p class="changetitle">今日新增学员(人)</p>
                  <p class="changeNum">
                    <span>{{ numberOfAddStudents }}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">新增意向学员(人)</p>
                  <p class="changeNum">
                    <span>{{ numberOfAddBStudents }}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">今日续费订单(条)</p>
                  <p class="changeNum">
                    <span>{{ xufei }}</span>
                  </p>
                </div>
                <div class="info-singelChange">
                  <p class="changetitle">待续费学员(人)</p>
                  <p class="changeNum">
                    <span>{{ daixufei }}</span>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="14">
          <!-- 学员趋势 -->
          <div class="info-change">
            <div class="normal-box">
              <div class="title-inline">
                <div>
                  <i class="el-icon-s-marketing"></i>
                  <span class="info-change-title">流水&人数</span>
                </div>

                <div>
                  <div class="block">
                    <el-date-picker
                      v-model="dateValue"
                      type="month"
                      placeholder="选择月"
                      value-format="yyyy-MM"
                      @change="chooseMonth(dateValue)"
                    >
                    </el-date-picker>
                  </div>
                </div>
              </div>

              <div class="show-line1">
                <ve-line
                  :data="chartData"
                  :settings="chartSettings"
                  height="300px"
                  width="100%"
                ></ve-line>
              </div>
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
                <el-button type="primary" @click="btnClick" icon="el-icon-edit"
                  >增加信息</el-button
                >
              </div>
              <div class="table">
                <div class="degreetablet">
                  <el-table :data="tableData" style="width: 100%">
                    <el-table-column type="expand">
                      <template slot-scope="props">
                        <el-form
                          label-position="left"
                          inline
                          class="demo-table-expand"
                        >
                          <el-form-item label="线索">
                            <span style="white-space: pre-wrap">{{
                              props.row.info
                            }}</span>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="编号"
                      align="center"
                      prop="id"
                    ></el-table-column>
                    <el-table-column
                      label="姓名"
                      align="center"
                      prop="name"
                    ></el-table-column>
                    <el-table-column
                      label="性别"
                      align="center"
                      prop="gender"
                    ></el-table-column>
                    <el-table-column
                      label="生日"
                      align="center"
                      prop="birthday"
                    ></el-table-column>
                    <el-table-column
                      label="手机号"
                      align="center"
                      prop="telephone"
                    ></el-table-column>
                    <el-table-column
                      label="咨询校区"
                      align="center"
                      prop="room.name"
                    ></el-table-column>
                    <el-table-column
                      label="录入人"
                      align="center"
                      prop="handler"
                    ></el-table-column>
                    <el-table-column
                      label="意向程度"
                      align="center"
                      prop="degree"
                    >
                      <template slot-scope="scope">
                        <el-tag
                          :type="
                            scope.row.degree === '高' ? 'success' : 'danger'
                          "
                          effect="dark"
                        >
                          {{ scope.row.degree }}</el-tag
                        >
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="跟踪状态"
                      align="center"
                      prop="state"
                    ></el-table-column>
                    <el-table-column
                      label="是否试听"
                      align="center"
                      prop="islisten"
                    >
                      <template slot-scope="scope">
                        <el-tag
                          :type="
                            scope.row.islisten === '是' ? 'success' : 'danger'
                          "
                          effect="dark"
                        >
                          {{ scope.row.islisten }}</el-tag
                        >
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="操作"
                      align="center"
                      min-width="200"
                    >
                      <template slot-scope="scope">
                        <el-button
                          size="mini"
                          type="success"
                          @click="handleChange(scope.$index, scope.row)"
                          >报名
                        </el-button>
                        <el-button
                          size="mini"
                          type="info"
                          @click="handleEdit(scope.$index, scope.row)"
                          >编辑</el-button
                        >
                        <el-popconfirm
                          style="margin-left: 10px"
                          title="请确定是否删除？"
                          @onConfirm="handleDelete(scope.$index, scope.row)"
                        >
                          <el-button slot="reference" size="mini" type="danger"
                            >删除</el-button
                          >
                        </el-popconfirm>
                      </template>
                    </el-table-column>
                  </el-table>
                </div>
                <el-pagination
                  class="pagination"
                  background
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="total"
                  :page-sizes="[10, 20, 50, 100]"
                  :current-page="current"
                  @current-change="currentChange"
                  @size-change="sizeChange"
                  @prev-click="prevClick"
                  @next-click="nextClick"
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
                  <el-form-item label="姓名" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                  </el-form-item>
                  <el-form-item label="性别" prop="gender">
                    <el-select v-model="ruleForm.gender">
                      <el-option label="男" value="男"></el-option>
                      <el-option label="女" value="女"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="生日" prop="birthday">
                    <el-date-picker
                      type="date"
                      v-model="ruleForm.birthday"
                      style="width: 100%"
                    ></el-date-picker>
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
                      >
                      </el-option>
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
                  <el-form-item label="线索" prop="info">
                    <el-input
                      type="textarea"
                      :autosize="{ minRows: 2 }"
                      v-model="ruleForm.info"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="是否试听" prop="islisten">
                    <el-select v-model="ruleForm.islisten">
                      <el-option label="是" value="是"></el-option>
                      <el-option label="否" value="否"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div class="pre-next-but">
                  <el-button @click="back">取消</el-button>
                  <el-button @click="insertBStudent" type="primary"
                    >提交</el-button
                  >
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>

      <el-dialog
        title="编辑信息"
        :visible.sync="dialogFormVisible"
        @closed="ruleForm = {}"
      >
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
                <el-form-item label="姓名" prop="name">
                  <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                  <el-select v-model="ruleForm.gender">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="生日" prop="birthday">
                  <el-date-picker
                    type="date"
                    v-model="ruleForm.birthday"
                    style="width: 100%"
                  ></el-date-picker>
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
                <el-form-item label="线索" prop="info" height="auto">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2 }"
                    v-model="ruleForm.info"
                  ></el-input>
                </el-form-item>
                <el-form-item label="是否试听" prop="islisten">
                  <el-select v-model="ruleForm.islisten">
                    <el-option label="是" value="是"></el-option>
                    <el-option label="否" value="否"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button
            @click="
              dialogFormVisible = false;
              ruleForm = {};
            "
            >取 消</el-button
          >
          <el-button type="primary" @click="updatebStudent">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {
  getBStudentNum,
  getBStudentData,
  getRoomList,
  deleteBStudent,
  insert,
  update,
  getbyid,
  getTable1,
  getall,
} from "@/api/home.js";
export default {
  name: "home",
  data() {
    this.chartSettings = {
      axisSite: { right: ["money"] },
      yAxisName: ["人数", "金额"],
      area: true,
    };
    return {
      year: null,
      month: null,
      dateValue: new Date(),
      numberOfAddBStudents: null,
      numberOfAddStudents: null,
      xufei: null,
      daixufei: null,
      current: 1,
      currentroomid: 1,
      total: null,
      size: 10,
      value: "",
      dialogFormVisible: false,
      tableData: [],
      roomList: [
        {
          name: null,
          id: null,
        },
      ],
      showTable: true,
      ruleForm: {},
      rules: {
        name: [
          {
            required: true,
            message: "请输入姓名",
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
      chartData: {
        columns: ["day", "people", "money"],
        rows: [],
      },
    };
  },
  async mounted() {
    await this.getRoom();
    this.getBStuNumber();
    this.getBStuRange();
    this.getinfochange();
    this.chooseMonth();
  },
  watch: {
    current() {
      this.getBStuNumber();
      this.getBStuRange();
    },
    size() {
      this.getBStuNumber();
      this.getBStuRange();
    },
  },
  methods: {
    async getBStuNumber() {
      this.total = (
        await await getBStudentNum({
          number1: this.currentroomid,
        })
      ).result;
    },
    async getBStuRange() {
      this.tableData = (
        await getBStudentData({
          number1: this.size * (this.current - 1),
          number2: this.size * this.current,
          number3: this.currentroomid,
        })
      ).result;
    },
    async getRoom() {
      let result = (await getRoomList()).result;
      this.roomList = result;
      this.currentroomid = result[0].id;
    },
    async getinfochange() {
      let result = (
        await getTable1({
          rid: this.currentroomid,
        })
      ).result;
      this.numberOfAddBStudents = result.numberOfAddBStudents;
      this.numberOfAddStudents = result.numberOfAddStudents;
      this.xufei = result.xufei;
      this.daixufei = result.daixufei;
    },
    changeRoomId(id) {
      this.currentroomid = id;
      console.log(this.currentroomid);
      this.getBStuNumber();
      this.getBStuRange();
      this.getinfochange();
      this.chooseMonth();
    },
    btnClick() {
      this.showTable = !this.showTable;
    },

    //LINECHART按钮
    chooseMonth(dateValue) {
      this.year = this.$moment(dateValue).format("YYYY");
      this.month = this.$moment(dateValue).format("MM");
      console.log(this.year);
      console.log(this.month);
      this.getLineChartData();
    },
    async getLineChartData() {
      this.chartData.rows = (
        await await getall({
          rid: this.currentroomid,
          year: this.year,
          month: this.month,
        })
      ).result;
    },

    back() {
      this.showTable = !this.showTable;
    },
    async insertBStudent() {
      let data = {};
      for (let i in this.ruleForm) {
        data[i] = this.ruleForm[i];
      }
      if (data.birthday !== null) {
        data.birthday = this.$moment(data.birthday).format("YYYY-DD-MM");
        data.date = this.$moment(data.date).format("YYYY-DD-MM");
      }
      await insert(data);
      this.$message({
        message: "提交成功！",
        type: "success",
      });

      this.ruleForm = {};
      this.showTable = !this.showTable;
      this.getBStuNumber();
      this.getBStuRange();
    },
    async handleEdit(index, row) {
      this.dialogFormVisible = true;
      console.log();
      let result = (
        await getbyid({
          id: row.id,
        })
      ).result;

      this.ruleForm = result;
      this.ruleForm.id = row.id;
      this.dialogFormVisible = true;
    },
    async updatebStudent() {
      let data = {};
      for (let i in this.ruleForm) {
        data[i] = this.ruleForm[i];
      }
      if (data.birthday !== null) {
        data.birthday = this.$moment(data.birthday).format("YYYY-DD-MM");
        data.indate = this.$moment(data.indate).format("YYYY-DD-MM");
      }
      await update(data);
      this.$message({
        message: "修改成功！",
        type: "success",
      });
      this.dialogFormVisible = false;
      this.getBStuNumber();
      this.getBStuRange();
    },
    async handleChange(index, row) {
      console.log(index);
      this.$router.push(`/studentinsert/${row.id}`);
    },
    async handleDelete(index, row) {
      console.log(index);
      await deleteBStudent({
        id: row.id,
      });
      this.$message({
        message: "删除成功！",
        type: "success",
      });
      await this.getBStuNumber();
      this.getBStuRange();
    },

    currentChange(page) {
      this.current = page;
    },
    sizeChange(size) {
      this.size = size;
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