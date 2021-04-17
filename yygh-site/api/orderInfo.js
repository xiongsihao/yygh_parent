import request from '@/utils/request'

const api_name = `/api/order/orderInfo`

export default {
  submitOrder(scheduleId, patientId) {
    return request({
      url: `${api_name}/auth/submitOrder/${scheduleId}/${patientId}`,
      method: 'post'
    })
  },
  //订单详情
  getOrders(orderId) {
    return request({
      url: `${api_name}/auth/getOrders/${orderId}`,
      method: `get`
    })
  },
}
