import request from '@/utils/request'


export default {

  getHospSetList(current,limit,searchObj){
    return request({
      url:`admin/hosp/hospitalSet/findPageHospSet/${current}/${limit}`,
      method:'post',
      //param:  //param  不使用json传输
      data: searchObj  //data 使用json传输
    })
  },

  deleteHospSet(id){
    return request({
      url:`admin/hosp/hospitalSet/${id}`,
      method:'delete',
    })
  }


}
