<template>
      <div class="app-container">
          <div style="margin-bottom: 10px;font-size: 10px;">选择：</div>
              <el-container style="height: 100%">
              <el-aside width="200px" style="border: 1px silver solid">
                  <!-- 部门 -->
                  <el-tree
                  :data="data"
                  :props="defaultProps"
                  :default-expand-all="true"
                  @node-click="handleNodeClick">
                  </el-tree>
              </el-aside>
              <el-main style="padding: 0 0 0 20px;">
                  <el-row style="width: 100%">
                  <!-- 排班日期 分页 -->
                  </el-row>
                  <el-row style="margin-top: 20px;">
                  <!-- 排班日期对应的排班医生 -->
                  </el-row>
              </el-main>
          </el-container>
      </div>
</template>
<script>
  import hospApi from '@/api/hosp'
  export default {
    data() {
      return {
        data: [],
        defaultProps: {
          children: 'children',
          label: 'depname'
        },
        hoscode: null
      }
    },
    created(){
      this.hoscode = this.$route.params.hoscode
      this.fetchData()
    },
    methods:{
      fetchData() {
        hospApi.getDeptByHoscode(this.hoscode)
          .then(response => {
            this.data = response.data
          })
      }
    }
  }
</script>
<style>
    .el-tree-node.is-current > .el-tree-node__content {
        background-color: #409EFF !important;
        color: white;
       }
    .el-checkbox__input.is-checked+.el-checkbox__label {
        color: black;
       }
</style>
