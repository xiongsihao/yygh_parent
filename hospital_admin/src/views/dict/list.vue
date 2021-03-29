<template>
  <div class="app-container">
    <div class="el-toolbar">
      <div class="el-toolbar-body" style="justify-content: flex-start;">
        <el-button type="text" @click="exportData"><i class="fa fa-plus"/> 导出</el-button>
      </div>
    </div>
    <el-table
      :data="list"
      style="width: 100%"
      row-key="id"
      border
      lazy
      :load="getChildrens"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

      <el-table-column
        label="名称" width="230" align="left">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column label="编码" width="220">
        <template slot-scope="{row}">
          {{ row.dictCode }}
        </template>
      </el-table-column>

      <el-table-column label="值" width="230" align="left">

        <template slot-scope="scope">
          <span>{{ scope.row.value }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" align="center">

        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>

  import dict from '@/api/dict'

  export default {
    data() {
      return {
        list: []
      }
    },
    created() {
      this.getDictList(1)  //默认查询id为1的数据 即全部分类数据
    },
    methods: {
      getDictList(id) {
        dict.dictList(id).then(response => {
          this.list = response.data
        })
      },
      getChildrens(tree, treeNode, resolve) {
        dict.dictList(tree.id).then(response => {
          resolve(response.data)
        })
      },
      exportData() {
        window.location.href = 'http://localhost:8202/admin/cmn/dict/exportData'
      }
    }
  }
</script>

<style scoped>

</style>
