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
            <el-select v-model="value" placeholder="校区1">
              <el-option
                v-for="item in rooms"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 总体信息 -->
    <div class="show2">
      <el-row :gutter="20" justify="space-between">
        <el-col :span="5">
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

        <el-col :span="10">
          <!-- 学员招生占比 -->
          <div class="info-change">
            <div class="normal-box">
              <span class="info-change-title">学员趋势</span>
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

        <el-col :span="24">
          <!-- 学员招生占比 -->
          <div class="info-change">
            <div class="normal-box">
              <span class="info-change-title">学员趋势</span>
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
      </el-row>
    </div>

    <!-- 学生具体信息 -->
    <div class="show3">
      <el-row :gutter="20" justify="space-between">
        <el-col :span="24">
          <div class="show-table">
            <el-table :data="tableData" style="width: 100%">
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
                    <el-form-item label="所在道馆">
                      <span>{{ props.row.room.name }}</span>
                    </el-form-item>
                    <el-form-item label="教练">
                      <span>{{ props.row.coach }}</span>
                    </el-form-item>
                    <el-form-item label="状态">
                      <span>{{ props.row.state }}</span>
                    </el-form-item>
                    <el-form-item label="admin">
                      <span>{{ props.row.admin }}</span>
                    </el-form-item>
                    <el-form-item label="生日">
                      <span>{{ props.row.birthday }}</span>
                    </el-form-item>
                    <el-form-item label="cardtype">
                      <span>{{ props.row.cardtype }}</span>
                    </el-form-item>
                    <el-form-item label="delaytime">
                      <span>{{ props.row.delaytime }}</span>
                    </el-form-item>
                    <el-form-item label="handler">
                      <span>{{ props.row.handler }}</span>
                    </el-form-item>
                    <el-form-item label="image">
                      <span>{{ props.row.image }}</span>
                    </el-form-item>
                    <el-form-item label="indate">
                      <span>{{ props.row.indate }}</span>
                    </el-form-item>
                    <el-form-item label="info">
                      <span>{{ props.row.info }}</span>
                    </el-form-item>
                    <el-form-item label="level">
                      <span>{{ props.row.level }}</span>
                    </el-form-item>
                    <el-form-item label="money">
                      <span>{{ props.row.money }}</span>
                    </el-form-item>
                    <el-form-item label="num">
                      <span>{{ props.row.num }}</span>
                    </el-form-item>
                    <el-form-item label="orders">
                      <span>{{ props.row.orders }}</span>
                    </el-form-item>
                    <el-form-item label="outdate">
                      <span>{{ props.row.outdate }}</span>
                    </el-form-item>
                    <el-form-item label="phone">
                      <span>{{ props.row.phone }}</span>
                    </el-form-item>
                    <el-form-item label="pname">
                      <span>{{ props.row.pname }}</span>
                    </el-form-item>
                    <el-form-item label="reday">
                      <span>{{ props.row.reday }}</span>
                    </el-form-item>
                    <el-form-item label="relation">
                      <span>{{ props.row.relation }}</span>
                    </el-form-item>
                    <el-form-item label="rid">
                      <span>{{ props.row.rid }}</span>
                    </el-form-item>
                    <el-form-item label="telephone">
                      <span>{{ props.row.telephone }}</span>
                    </el-form-item>
                    <el-form-item label="type">
                      <span>{{ props.row.type }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column label="学号" prop="id"></el-table-column>
              <el-table-column label="姓名" prop="name"></el-table-column>
              <el-table-column label="年龄" prop="age"></el-table-column>
              <el-table-column label="性别" prop="gender"></el-table-column>
              <el-table-column label="所在道馆" prop="room.name"></el-table-column>
              <el-table-column label="教练" prop="coach"></el-table-column>
              <el-table-column label="状态" prop="state"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getStudentInfo } from "@/api/studentinfo.js";
export default {
  name: "sutdentinfo",
  data() {
    return {
      newStudentsAddedToday: -1,
      numberOfStudents: -1,
      birthdayThisMonth: -1,
      graduateStudent: -1,
      rooms: [
        {
          value: "选项1",
          label: "校区1",
        },
        {
          value: "选项2",
          label: "校区2",
        },
      ],
      value: "校区1",
      tableData: [],
    };
  },
  created() {
    this.getAllInfo();
  },
  methods: {
    async getAllInfo() {
      let results = await getStudentInfo()
      console.log(results)
      this.tableData = results.result
      console.log('this.tableData')
      console.log(this.tableData)

    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
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