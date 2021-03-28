import request from '@/utils/request'


export default {

  getHospSetList(current, limit, searchObj) {
    return request({
      url: `admin/hosp/hospitalSet/findPageHospSet/${current}/${limit}`,
      method: 'post',
      //param:  //param  不使用json传输
      data: searchObj  //data 使用json传输
    })
  },

  deleteHospSet(id) {
    return request({
      url: `admin/hosp/hospitalSet/${id}`,
      method: 'delete',
    })
  },

  //批量删除
  batchHospSet(idList) {
    return request({
      url: `admin/hosp/hospitalSet/batchRemove`,
      method: 'delete',
      data: idList
    })
  },

  //锁定和取消锁定
  lockHospSet(id, status) {
    return request({
      url: `/admin/hosp/hospitalSet/lockHospitalSet/${id}/${status}`,
      method: 'put'
    })
  },

  //添加医院设置
  saveHospSet(hospitalSet) {
    return request({
      url: `/admin/hosp/hospitalSet/saveHospitalSet`,
      method: 'post',
      data: hospitalSet
    })
  }
}
