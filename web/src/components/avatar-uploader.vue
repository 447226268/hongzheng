<template>
  <div class="upload">
    <el-upload class="avatar-uploader" :action="updatePic" :show-file-list="false" :before-upload="beforeAvatarUpload"
               :on-success="onSuccess">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>

<script>
import {updatePic} from "@/api/student"

export default {
  props: [
    'image'
  ],
  watch: {
    image: {
      handler(newVal) {
        this.imageUrl = process.env.VUE_APP_SHOW_PIC + newVal
      },
      deep: true
    }
  },
  mounted() {
    this.imageUrl = this.image
  },
  data() {
    return {
      imageUrl: '',
      imageNmae: '',
      updatePic: updatePic,
    };
  },
  methods: {
    beforeAvatarUpload(file) {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.$message.error('只能上传png或jpg图片格式!');
        return false
      }
      const isLt2M = file.size / 1024 / 1024 < 5;
      if (!isLt2M) {
        this.$message.error('图片大小必须小于5m');
        return false
      }
      return true
    },
    onSuccess(response) {
      this.imageNmae = response
      this.imageUrl = process.env.VUE_APP_SHOW_PIC + response
      this.$emit("getUrl", this.imageNmae)
    },
    getName() {
      return this.imageNmae
    }
  }
}
</script>


<style lang="scss" scoped>
.upload {
  ::v-deep .avatar-uploader .el-upload {
    border: 1px dashed #8c939d;
    border-radius: 100px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  ::v-deep .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  ::v-deep .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }

  ::v-deep .avatar {
    width: 120px;
    height: 120px;
    display: block;
  }
}
</style>