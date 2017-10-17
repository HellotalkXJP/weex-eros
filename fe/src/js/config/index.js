import routes from '../routes'
import { msgMap } from './push'
import { ajaxMap, responseHandler } from '../service'
import './other'

// 开关
const switchs = {
    // $service 中配置的请求
    // true 走本地 mock | false 真实接口
    mock: true
}

export const config = {
    switchs,
    routes,
    msgMap,
    ajaxMap,
    responseHandler,
}

Vue.prototype.eros = config