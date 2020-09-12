<template>
  <el-pagination background layout="prev, pager, next" :total="total" :current-page="current"
    @current-change="currentChange">
  </el-pagination>
</template>

<script>
  export default {
    props: [
      'pag'
    ],
    watch: {
      pag: {
        handler(newVal) {
          this.current = newVal.current
          this.total = newVal.total
        },
        deep: true
      }
    },
    created() {
      this.current = this.pag.current
      this.total = this.pag.total
    },
    data() {
      return {
        current: 1,
        total: 1000,
      }
    },
    methods: {
      currentChange(value) {
        this.current = value
        this.$emit('currentChange', {
          total: this.total,
          current: this.current
        })
      }
    }
  }
</script>


<style lang="scss" scoped>

</style>