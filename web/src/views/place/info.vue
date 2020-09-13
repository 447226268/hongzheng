<template>
  <div class="placeinfo">
    <div class="table">
      <el-button class="add" type="primary" @click="insertPlace">添加道馆</el-button>
      <hztable v-bind:value="tableData" ref="hztable" v-on:edit="editPlace" v-on:remove="removePlace"
        v-on:freezes="freezesPlace"></hztable>
    </div>

    <el-dialog title="道馆信息" :visible.sync="dialogFormVisible" @closed="cancle">
      <el-form :model="form">
        <el-form-item label="道馆名称" label-width="100px">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="所属教练" label-width="100px">
          <el-select v-model="form.aid" placeholder="请选择活动区域">
            <el-option v-for="item in admin" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
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
  import hztable from "./components/table"
  import {
    getall,
    getadminall,
    insert,
    update,
    remove,
    updatestate
  } from "@/api/place.js"
  export default {
    name: 'placeinfo',
    data() {
      return {
        dialogFormVisible: null,
        admin: null,
        isNew: true,
        tableData: [],
        form: {
          id: null,
          name: null,
          aid: null,
        },
      }
    },
    mounted() {
      this.getAll()
    },
    methods: {
      async getAll() {
        this.tableData = (await getall()).result
      },
      async getAdminAll() {
        this.admin = (await getadminall()).result
      },
      insertPlace() {
        this.getAdminAll()
        this.isNew = true
        this.dialogFormVisible = true
      },
      editPlace(row) {
        this.getAdminAll()
        this.form.name = row.name
        this.form.aid = row.aid
        this.form.id = row.id
        this.isNew = false
        this.dialogFormVisible = true
      },
      async freezesPlace(row) {
        await updatestate({
          id: row.id
        })
        this.$message({
          message: '修改成功！',
          type: 'success'
        });
        this.getAll()
      },
      async removePlace(row) {
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
            message: '修改成功！',
            type: 'success'
          });
        }
        this.getAll()
        this.dialogFormVisible = false
        this.form.name = null
        this.form.aid = null
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
  .placeinfo {

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