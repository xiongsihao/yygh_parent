import request from '@/utils/request'

const api_name = `/api/hosp/hospital`

export default {

  //用户端预约挂号界面 查询医院列表
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/findHospList/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },

  //根据医院名称模糊查询
  getByHosname(hosname) {
    return request({
      url: `${api_name}/findByHosname/${hosname}`,
      method: 'get'
    })
  }
}
