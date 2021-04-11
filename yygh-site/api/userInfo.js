import request from '@/utils/request'

const api_name = `/api/user`

export default {
  login(userInfo) {
    return request({
      url: `${api_name}/login`,
      method: `post`,
      data: userInfo
    })
  }
}
