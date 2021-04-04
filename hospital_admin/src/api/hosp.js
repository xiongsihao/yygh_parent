import request from '@/utils/request'

export default {
  //医院列表
  getPageList(current,limit,searchObj) {
    return request ({
      url: `/admin/hosp/hospital/list/${current}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  //查询dictCode查询下级数据字典
  findByDictCode(dictCode) {
    return request({
      url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },

  //根据id查询下级数据字典
  findByParentId(dictCode) {
    return request({
      url: `/admin/cmn/dict/findChildData/${dictCode}`,
      method: 'get'
    })
  },

  updateStatus(id, status) {
    return request({
      url: `/admin/hosp/hospital/updateStatus/${id}/${status}`,
      method: 'get'
    })
  }
}
