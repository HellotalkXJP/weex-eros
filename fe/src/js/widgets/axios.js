/*
 * @Author: Zero 
 * @Date: 2017-10-10 14:20:22 
 * @Last Modified by: Zero
 * @Last Modified time: 2017-10-10 16:17:03
 */

var bmAxios = weex.requireModule('bmAxios'),
    Axios = Object.create(null),
    Service = Object.create(null)

import _pick from 'lodash/pick'
import _assign from 'lodash/assign'
import _isEmpty from 'lodash/isEmpty'
import { assert } from './utils/tools'

const MOCK_PREFIX = weex.config.env.jsServer
const TRUE_PREFIX = weex.config.env.request

Axios.install = (Vue, options) => {
    Vue.prototype.$fetch = (options) => {
        // 不仅支持 success, error 的回调写法，还支持 promise 的写法
        return new Promise((resolve, reject) => {
            bmAxios.fetch({
                method: options.method || 'GET',
                url: options.url,
                header: options.header || {},
                data: options.data || {}
            }, (resData) => {
                // 可以做统一的监控
                Vue.prototype.eros.responseHandler(options, resData, resolve, reject)
            })
        })
    }
}

Service.install = (Vue, options) => {
    Vue.prototype.$service = (name, params, options = {}) => {
        const AJAX_MAP = Vue.prototype.eros.ajaxMap
            // 不仅支持 success, error 的回调写法，还支持 promise 的写法
        assert(AJAX_MAP[name], 'axios--->service层中并没有此命名的方法')
        let _params = AJAX_MAP[name]['params'],
            openMock = Vue.prototype.eros.switchs.mock

        return new Promise((resolve, reject) => {
            bmAxios.fetch({
                method: AJAX_MAP[name]['method'] || 'GET',
                url: openMock ? MOCK_PREFIX + AJAX_MAP[name]['mockPath'] : TRUE_PREFIX + AJAX_MAP[name]['truePath'],
                header: AJAX_MAP[name]['header'] || {},
                data: _isEmpty(params) ? _params : _pick(_assign({}, _params, params), Object.keys(_params))
            }, (resData) => {
                // 可以做统一的监控
                Vue.prototype.eros.responseHandler(options, resData, resolve, reject)
            })
        })
    }
}

Vue.use(Axios)
Vue.use(Service)