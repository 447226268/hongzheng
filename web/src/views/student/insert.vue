<template>
  <div class="studentinsert">
    <div class="step">
      <el-steps :active="active" align-center>
        <el-step title="基本信息" description="输入学员信息"></el-step>
        <el-step title="课程信息" description="输入学员所购买课程信息"></el-step>
        <el-step title="提交" description="确认并调教学员信息"></el-step>
      </el-steps>
    </div>

    <div class="main">
      <div class="form">
        <el-form v-if="active===1" :model="ruleForm1" :rules="rules1" ref="ruleForm1" label-width="100px"
                 class="demo-ruleForm">
          <div class="avatarimg">
            <avataruploader ref="avataruploader" v-bind:image="ruleForm1.image" v-on:getUrl="getUrl"></avataruploader>
          </div>

          <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm1.name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-select v-model="ruleForm1.gender">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="birthday" label="出生日期">
            <el-date-picker type="date" v-model="ruleForm1.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="身份证号" prop="idnum">
            <el-input v-model="ruleForm1.idnum"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="telephone">
            <el-input v-model="ruleForm1.telephone"></el-input>
          </el-form-item>
          <el-form-item label="教练" prop="coach">
            <el-input v-model="ruleForm1.coach"></el-input>
          </el-form-item>
          <el-form-item label="监护人" prop="pname">
            <el-input v-model="ruleForm1.pname"></el-input>
          </el-form-item>
          <el-form-item label="监护人关系" prop="relation">
            <el-select v-model="ruleForm1.relation">
              <el-option label="父亲" value="父亲"></el-option>
              <el-option label="母亲" value="母亲"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="监护人电话" prop="phone">
            <el-input v-model="ruleForm1.phone"></el-input>
          </el-form-item>
          <el-form-item label="段位" prop="level">
            <el-input v-model="ruleForm1.level"></el-input>
          </el-form-item>
          <el-form-item label="意向类型" prop="type">
            <el-select v-model="ruleForm1.type">
              <el-option label="购买课程" value="购买课程"></el-option>
              <!-- <el-option label="意向咨询" value="意向咨询"></el-option>
              <el-option label="其他" value="其他"></el-option> -->
            </el-select>
          </el-form-item>
        </el-form>

        <el-form v-if="active===2" :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="办卡类型" prop="cardtype">
            <el-select v-model="ruleForm2.cardtype">
              <el-option label="日卡" value="日卡"></el-option>
              <el-option label="月卡" value="月卡"></el-option>
              <el-option label="季卡" value="季卡"></el-option>
              <el-option label="半年卡" value="半年卡"></el-option>
              <el-option label="年卡" value="年卡"></el-option>
              <el-option label="私教卡" value="私教卡"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="indate" label="办卡日期">
            <el-date-picker type="date" v-model="ruleForm2.indate" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="延期时长" prop="delaytime">
            <el-input v-model="ruleForm2.delaytime"></el-input>
          </el-form-item>
          <el-form-item label="道馆选择" prop="rid">
            <el-select v-model="ruleForm2.rid">
              <el-option v-for="item in room" :label="item.name" :value="item.id" :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="缴纳金额" prop="money">
            <el-input v-model="ruleForm2.money"></el-input>
          </el-form-item>
          <el-form-item label="缴纳方式" prop="way">
            <el-select v-model="ruleForm2.way">
              <el-option label="微信" value="微信"></el-option>
              <el-option label="支付宝" value="支付宝"></el-option>
              <el-option label="现金" value="现金"></el-option>
              <el-option label="刷卡" value="刷卡"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="录入人" prop="handler">
            <el-input v-model="ruleForm2.handler"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="info">
            <el-input type="textarea" :rows="15" v-model="ruleForm2.info"></el-input>
          </el-form-item>
        </el-form>

        <div class="pre-next-but">
          <el-button @click="back">上一步</el-button>
          <el-button v-if="active===3" @click="insertStudent" type="primary">提交</el-button>
          <el-button v-else @click="go" type="primary">下一步</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  insert,
  getall,
  change
} from "@/api/student.js";
import avataruploader from "@/components/avatar-uploader.vue";

export default {
  name: "studentinsert",
  data() {
    return {
      active: 1,
      room: [],
      ruleForm1: {
        name: null,
        image: null,
        gender: null,
        birthday: null,
        telephone: null,
        idnum: null,
        coach: null,
        pname: null,
        relation: null,
        phone: null,
        level: null,
        type: null,
      },
      rules1: {
        name: [{
          required: true,
          message: "姓名不能为空",
          trigger: "blur",
        },],
        type: [{
          required: true,
          message: "请选择一个意向类型",
          trigger: "change",
        },],
        room: [{
          required: true,
          message: "请选择一个意向类型",
          trigger: "change",
        },],
        telephone: [{
          required: true,
          message: "请输入联系电话",
          trigger: "blur",
        },],
        idnum: [{
          required: true,
          message: "请输入身份证号",
          trigger: "blur",
        },],
      },
      ruleForm2: {
        cardtype: null,
        indate: null,
        delaytime: null,
        rid: null,
        money: null,
        way: null,
        handler: null,
        info: null,
        room: {
          id: null,
          name: null,
        },
      },
    };
  },
  async mounted() {
    this.getAllRoom();
    if (this.$route.params.id) {
      let result = (
          await change({
            id: this.$route.params.id,
          })
      ).result;
      for (let i in this.ruleForm1) {
        this.ruleForm1[i] = result[i];
      }
      for (let i in this.ruleForm2) {
        this.ruleForm2[i] = result[i];
      }
      this.ruleForm1.id = this.$route.params.id;
    }
  },
  methods: {
    back() {
      if (this.active === 1) {
        return;
      } else {
        if (this.active === 3) {
          if (this.ruleForm1["type"] === "购买课程") {
            this.active = 2;
          } else {
            this.active = 1;
          }
        } else {
          this.active = 1;
        }
      }
    },
    go() {
      if (this.active === 3) {
        return;
      } else {
        if (this.active === 1) {
          this.$refs["ruleForm1"].validate((valid) => {
            if (valid) {
              if (this.ruleForm1["type"] === "购买课程") {
                this.active = 2;
              } else {
                this.active = 3;
              }
            } else {
              console.log("error submit!!");
              return false;
            }
          });
        } else {
          this.active = 3;
        }
      }
    },
    async getAllRoom() {
      let result = (await getall()).result;
      this.room = result;
    },
    getUrl(url) {
      this.ruleForm1.image = url
    },
    async insertStudent() {
      let data = {};
      if (this.ruleForm1["type"] === "购买课程") {
        for (let i in this.ruleForm1) {
          data[i] = this.ruleForm1[i];
        }
        for (let i in this.ruleForm2) {
          data[i] = this.ruleForm2[i];
        }
      } else {
        for (let i in this.ruleForm1) {
          data[i] = this.ruleForm1[i];
        }
      }
      if (data.birthday !== null) {
        data.birthday = this.$moment(data.birthday).format("YYYY-MM-DD");
        data.indate = this.$moment(data.indate).format("YYYY-MM-DD");
      }

      await insert(data);
      this.$message({
        message: "提交成功！",
        type: "success",
      });

      if (this.$route.params.id) {
        console.log("delate")
      }

      this.$router.push("/studentinfo");
    },
  },
  components: {
    avataruploader,
  },
};
</script>

<style lang="scss" scoped>
.studentinsert {
  .step {
    padding: 24px 0 24px 0;
    background-color: #fff;
  }

  .main {
    margin-top: 24px;
    background-color: #fff;

    .form {
      max-width: 800px;
      padding: 30px 0;
      margin: auto;

      .avatarimg {
        margin-bottom: 30px;
        text-align: center;
      }
    }

    .pre-next-but {
      margin-top: 60px;
      text-align: center;

      .el-button {
        width: 150px;
        margin-right: 50px;
      }
    }
  }
}
</style>