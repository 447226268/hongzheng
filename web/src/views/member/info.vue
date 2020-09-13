<template>
  <div class="memberinfo">

    <div class="table">
      <el-button class="add" type="primary" @click="insertMember">添加馆长</el-button>
      <hztable v-bind:value="tableData" ref="hztable" v-on:edit="editMember" v-on:remove="removeMember"
        v-on:freezes="freezesMember"></hztable>
    </div>

    <el-dialog title="馆长信息" :visible.sync="dialogFormVisible" @closed="cancle">
      <el-form :model="form">
        <el-form-item label="姓名" label-width="100px">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="账号名" label-width="100px">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="100px">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {
    getall,
    insert,
    update,
    remove,
    updatestate
  } from "@/api/member.js"
  import hztable from "./components/table"
  export default {
    name: 'memberinfo',
    data() {
      return {
        tableData: null,
        dialogFormVisible: false,
        isNew: false,
        form: {
          id: null,
          name: null,
          username: null,
          password: null,
        },
      }
    },
    mounted() {
      this.getAll()
    },
    methods: {
      async getAll() {
        let result = (await getall()).result
        result.forEach((e) => {
          let s = []
          for (let i of e.rooms) {
            s.push(i.name)
          }
          e.rooms = s.join('、')
          console.log(e.rooms)
        })
        this.tableData = result
      },
      insertMember() {
        this.isNew = true
        this.dialogFormVisible = true
      },
      editMember(row) {
        this.isNew = false
        this.form.name = row.name
        this.form.username = row.username
        this.form.password = row.password
        this.form.id = row.id
        this.dialogFormVisible = true
      },
      async freezesMember(row) {
        await updatestate({
          id: row.id
        })
        this.$message({
          message: '修改成功！',
          type: 'success'
        });
        this.getAll()
      },
      async removeMember(row) {
        await remove({
          id: row.id
        })
        this.$message({
          message: '删除成功！',
          type: 'success'
        });
        this.getAll()
      },
      async submit() {
        if (this.isNew) {
          await insert(this.form)
          this.$message({
            message: '提交成功！',
            type: 'success'
          });
        } else {
          await update(this.form)
          this.$message({
            message: '提交成功！',
            type: 'success'
          });
        }
        this.getAll()
        this.dialogFormVisible = false
        this.form.name = null
        this.form.username = null
        this.form.password = null
        this.form.id = null
      },
      cancle() {
        this.dialogFormVisible = false
        this.form.name = null
        this.form.aid = null
        this.form.id = null
      }
    },
    components: {
      hztable
    }
  }
</script>

<style lang="scss" scoped>
  .memberinfo {
    .table {
      padding: 24px;
      background-color: #fff;

      .add {
        margin-left: 20px;
        margin-bottom: 20px;
      }

      .el-button {
        width: 150px;
        border-radius: 0px;
      }
    }
  }
</style>