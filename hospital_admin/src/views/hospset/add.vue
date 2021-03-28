<template>
    
  <div
     class="app-container">
          医院设置添加
          
    <el-form
       label-width="120px">
               
      <el-form-item
         label="医院名称">
                    
        <el-input
           v-model="hospitalSet.hosname"/>
                 
      </el-form-item>
               
      <el-form-item
         label="医院编号">
                    
        <el-input
           v-model="hospitalSet.hoscode"/>
                 
      </el-form-item>
               
      <el-form-item
         label="api基础路径">
                    
        <el-input
           v-model="hospitalSet.apiUrl"/>
                 
      </el-form-item>
               
      <el-form-item
         label="联系人姓名">
                    
        <el-input
           v-model="hospitalSet.contactsName"/>
                 
      </el-form-item>
               
      <el-form-item
         label="联系人手机">
                    
        <el-input
           v-model="hospitalSet.contactsPhone"/>
                 
      </el-form-item>
               
      <el-form-item>
                    
        <el-button
           type="primary"  @click="saveOrUpdate">保存
        </el-button>
                 
      </el-form-item>
            
    </el-form>
       
  </div>
</template>

<script>
  import hospset from '@/api/hospset'

  export default {
    data() {
      return {
        hospitalSet: {}
      }
    },
    created() {
    },
    methods: {
      //添加
      saveOrUpdate() {
        hospset.saveHospSet(this.hospitalSet)
          .then(response => {
            //提示
            this.$message({
              type: 'success',
              message: '添加成功!'
            })
            //跳转列表页面，使用路由跳转方式实现
            this.$router.push({path: '/hospSet/list'})
          })
      }
    }
  }
</script>

<style scoped>

</style>
