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
                    <el-form-item label="办卡类型">
                      <span>{{ props.row.cardtype }}</span>
                    </el-form-item>
                    <el-form-item label="延期日期">
                      <span>{{ props.row.delaytime }}</span>
                    </el-form-item>
                    <el-form-item label="处理人">
                      <span>{{ props.row.handler }}</span>
                    </el-form-item>
                    <el-form-item label="办卡时间">
                      <span>{{ props.row.indate }}</span>
                    </el-form-item>
                    <el-form-item label="备注">
                      <span>{{ props.row.info }}</span>
                    </el-form-item>
                    <el-form-item label="段位">
                      <span>{{ props.row.level }}</span>
                    </el-form-item>
                    <el-form-item label="金额">
                      <span>{{ props.row.money }}</span>
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
                  </el-form>
                </template>
              </el-table-column>
              <!-- <el-table-column label="头像">
                <template slot-scope="scope">
                  <el-image ></el-image>
                </template>
              </el-table-column>-->
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
      tableData: [
        {
          admin: null,
          age: 18,
          birthday: "1997-05-20",
          cardtype: null,
          coach: "周鑫",
          delaytime: null,
          gender: "男",
          handler: null,
          id: 2,
          image: null,
          indate: null,
          info: null,
          level: null,
          money: null,
          name: "yxr",
          num: null,
          orders: null,
          outdate: null,
          phone: null,
          pname: null,
          reday: null,
          relation: null,
          rid: null,
          room: {
            name: "孵化楼",
          },
          state: "冻结！",
          telephone: null,
          type: null,
        },
      ],
    };
  },
  created() {
    this.getAllInfo();
  },
  methods: {
    async getAllInfo() {
      let results = await getStudentInfo();
      console.log(await getStudentInfo());
      this.tableData = results.result;
      console.log("this.tableData");
      console.log(this.tableData);
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