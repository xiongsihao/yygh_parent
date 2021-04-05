<template>
  <div class="home page-component">
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in 2" :key="item">
        <img src="~assets/images/web-banner1.png" alt="">
      </el-carousel-item>
    </el-carousel>
    <!-- 搜索 -->
    <div class="search-container">
      <div class="search-wrapper">
        <div class="hospital-search">
          <el-autocomplete
            class="search-input"
            prefix-icon="el-icon-search"
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="点击输入医院名称"
            @select="handleSelect"
          >
            <span slot="suffix" class="search-btn v-link highlight clickable selected">搜索 </span>
          </el-autocomplete>
        </div>
      </div>
    </div>
    <!-- bottom -->
    <div class="bottom">
      <div class="left">
        <div class="home-filter-wrapper">
          <div class="title"> 医院</div>
          <div>
            <div class="filter-wrapper">
          <span
            class="label">等级：</span>
              <div class="condition-wrapper">
            <span class="item v-link clickable"
                  :class="hostypeActiveIndex == index ? 'selected' : ''"
                  v-for="(item,index) in hostypeList" :key="item.id"
                  @click="hostypeSelect(item.value, index)">{{ item.name }}</span>
              </div>
            </div>
            <hr>
            <div class="filter-wrapper">
        <span
          class="label">省份：</span>
              <div class="condition-wrapper">
          <span class="item v-link clickable"
                :class="provinceActiveIndex == index ? 'selected' : ''"
                v-for="(item,index) in provinceList" :key="item.id"
                @click="provinceSelect(item.value, index)">{{ item.name }}</span>
              </div>
            </div>
            <hr>
            <div class="filter-wrapper">
        <span
          class="label">地区：</span>
              <div class="condition-wrapper">
          <span class="item v-link clickable"
                :class="cityActiveIndex == index ? 'selected' : ''"
                v-for="(item,index) in districtList" :key="item.id"
                @click="districtSelect(item.value, index)">{{ item.name }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="v-scroll-list hospital-list">
          <div class="v-card clickable list-item" v-for="item in list" :key="item.id">
            <div class="">
              <div class="hospital-list-item hos-item" index="0" @click="show(item.hoscode)">
                <div class="wrapper">
                  <div class="hospital-title"> {{ item.hosname }}</div>
                  <div class="bottom-container">
                    <div class="icon-wrapper">
                      <span class="iconfont"></span>{{ item.param.hostypeString }}
                    </div>
                    <div class="icon-wrapper">
                      <span class="iconfont"></span>每天{{ item.bookingRule.releaseTime }}放号
                    </div>
                  </div>
                </div>
                <img :src="'data:image/jpeg;base64,'+item.logoData"
                     :alt="item.hosname"
                     class="hospital-img">
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="right">
        <div class="common-dept">
          <div class="header-wrapper">
            <div class="title"> 常见科室</div>
            <div class="all-wrapper"><span>全部</span>
              <span class="iconfont icon"></span>
            </div>
          </div>
          <div class="content-wrapper">
            <span class="item v-link clickable dark">神经内科 </span>
            <span class="item v-link clickable dark">消化内科 </span>
            <span class="item v-link clickable dark">呼吸内科 </span>
            <span class="item v-link clickable dark">内科 </span>
            <span class="item v-link clickable dark">神经外科 </span>
            <span class="item v-link clickable dark">妇科 </span>
            <span class="item v-link clickable dark"> 产科 </span>
            <span class="item v-link clickable dark">儿科 </span>
          </div>
        </div>
        <div class="space">
          <div class="header-wrapper">
            <div class="title-wrapper">
              <div class="icon-wrapper"><span
                class="iconfont title-icon"></span>
              </div>
              <span class="title">平台公告</span>
            </div>
            <div class="all-wrapper">
              <span>全部</span>
              <span class="iconfont icon"></span>
            </div>
          </div>
          <div class="content-wrapper">
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark">关于延长北京大学国际医院放假的通知 </span>
            </div>
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark">北京中医药大学东方医院部分科室医生门诊医 </span>
            </div>
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark"> 武警总医院号源暂停更新通知 </span>
            </div>
          </div>
        </div>
        <div class="suspend-notice-list space">
          <div class="header-wrapper">
            <div class="title-wrapper">
              <div class="icon-wrapper">
                <span class="iconfont title-icon"></span>
              </div>
              <span class="title">停诊公告</span>
            </div>
            <div class="all-wrapper">
              <span>全部</span>
              <span class="iconfont icon"></span>
            </div>
          </div>
          <div class="content-wrapper">
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark"> 中国人民解放军总医院第六医学中心(原海军总医院)呼吸内科门诊停诊公告 </span>
            </div>
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark"> 首都医科大学附属北京潞河医院老年医学科门诊停诊公告 </span>
            </div>
            <div class="notice-wrapper">
              <div class="point"></div>
              <span class="notice v-link clickable dark">中日友好医院中西医结合心内科门诊停诊公告 </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  import hospApi from '@/api/hosp'
  import dictApi from '@/api/dict'

  export default {
    //服务端渲染异步，显示医院列表
    asyncData({params, error}) {
      //调用
      return hospApi.getPageList(1, 10, null)
        .then(response => {
          return {
            list: response.data.content,
            pages: response.data.totalPages
          }
        })
    },
    data() {
      return {
        state:null,
        searchObj: {},
        page: 1,
        limit: 10,

        hosname: '', //医院名称
        hostypeList: [], //医院等级集合
        provinceList: [], //省份集合
        districtList: [], //地区集合

        hostypeActiveIndex: 0,
        provinceActiveIndex: 0,
        cityActiveIndex: 0
      }
    },
    created() {
      this.init()
    },
    methods: {
      //查询医院等级列表 和 所有地区列表
      init() {
        //查询医院等级列表
        dictApi.findByDictCode('Hostype')
          .then(response => {
            //hostypeList清空
            this.hostypeList = []
            //向hostypeList添加全部值
            this.hostypeList.push({"name": "全部", "value": ""})
            //把接口返回数据，添加到hostypeList
            for (var i = 0; i < response.data.length; i++) {
              this.hostypeList.push(response.data[i])
            }
          })

        //查询省份列表
        dictApi.findByDictCode('Province')
          .then(response => {
            //provinceList清空
            this.provinceList = []
            //向hostypeList添加全部值
            this.provinceList.push({"name": "全部", "value": ""})
            //把接口返回数据，添加到provinceList
            for (var i = 0; i < response.data.length; i++) {
              this.provinceList.push(response.data[i])
            }
          })

        //查询地区数据
        dictApi.findByDictCode('Beijing')
          .then(response => {
            this.districtList = []
            this.districtList.push({"name": "全部", "value": ""})
            for (let i in response.data) {
              this.districtList.push(response.data[i])
            }
          })
      },

      //查询医院列表
      getList() {
        hospApi.getPageList(this.page, this.limit, this.searchObj)
          .then(response => {
            for (let i in response.data.content) {
              this.list.push(response.data.content[i])
            }
            this.page = response.data.totalPages
          })
      },

      //根据医院等级查询
      hostypeSelect(hostype, index) {
        //准备数据
        this.list = []
        this.page = 1
        this.hostypeActiveIndex = index
        this.searchObj.hostype = hostype
        //调用查询医院列表方法
        this.getList()
      },
      //根据省份查询医院
      provinceSelect(districtCode, index) {
        this.list = []
        this.page = 1
        this.provinceActiveIndex = index
        this.searchObj.provinceCode = districtCode
        this.getList();
      },
      //根据地区查询医院
      districtSelect(districtCode, index) {
        this.list = []
        this.page = 1
        this.cityActiveIndex = index
        this.searchObj.districtCode = districtCode
        this.getList();
      },

      //在输入框输入值，弹出下拉框，显示相关内容
      querySearchAsync(queryString, cb) {
        this.searchObj = []
        if (queryString == '') return
        hospApi.getByHosname(queryString).then(response => {
          for (let i = 0, len = response.data.length; i < len; i++) {
            response.data[i].value = response.data[i].hosname
          }
          cb(response.data)
        })
      },

      //在下拉框选择某一个内容，执行下面方法，跳转到详情页面中
      handleSelect(item) {
        window.location.href = '/hospital/' + item.hoscode
      },

      //点击某个医院名称，跳转到详情页面中
      show(hoscode) {
        window.location.href = '/hospital/' + hoscode
      }
    }
  }
</script>
