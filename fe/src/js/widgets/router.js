/**
 * @Author: songqi
 * @Date:   2017-01-11
 * @Last modified by:   songqi
 * @Last modified time: 2017-04-05
 */

var modal = weex.requireModule('bmModal'),
    router = weex.requireModule('bmRouter'),
    storage = weex.requireModule('bmStorage'),
    globalEvent = weex.requireModule('globalEvent')

import _isFunction from 'lodash/isFunction'
import _isUndefined from 'lodash/isUndefined'
import isPlainObject from 'lodash/isPlainObject'

// 客户端默认打开页面的动画
export const DEFAULT_ANIMATETYPE = 'PUSH'

var Router = {
    // 页面将要出现
    viewWillAppear: [],
    // 页面已经出现
    viewDidAppear: [],
    // 页面被拿出栈时即将展示
    viewWillBackAppear: [],
    // 页面被拿出栈时已经展示
    viewDidBackAppear: [],
    // 页面将要放在栈中
    viewWillDisappear: [],
    // 页面已经放在栈中
    viewDidDisappear: []
}

globalEvent.addEventListener("viewWillAppear", function(options) {
    if (options.type === 'open' || options.type === 'refresh') {
        router.getParams((params) => {
            Router.viewWillAppear.map((item) => {
                item(params, options)
            })
        })
    } else if (options.type === 'back') {
        storage.getData('router.backParams', (resData) => {
            Router.viewWillBackAppear.map((item) => {
                item(JSON.parse(resData.data.value || '{}'), options)
            })
        })
    }
})

globalEvent.addEventListener("viewDidAppear", function(options) {
    if (options.type === 'open' || options.type === 'refresh') {
        router.getParams((params) => {
            Router.viewDidAppear.map((item) => {
                item(params, options)
            })
        })
    } else if (options.type === 'back') {
        storage.getData('router.backParams', (resData) => {
            Router.viewDidBackAppear.map((item) => {
                item(JSON.parse(resData.data.value || '{}'), options)
            })
            storage.deleteData('router.backParams')
        })
    }
})

globalEvent.addEventListener("viewWillDisappear", function(options) {
    modal.hideLoading()
    Router.viewWillDisappear.map((item) => {
        item(options)
    })
})

globalEvent.addEventListener("viewDidDisappear", function(options) {
    Router.viewDidDisappear.map((item) => {
        item(options)
    })
})

// todo 修改逻辑
Router.install = (Vue, options) => {
    Vue.mixin({
        beforeCreate() {
            if (this.$options.bmRouter) {
                var bmRouter = this.$options.bmRouter
                for (var i in bmRouter) {
                    if (!Router[i]) {
                        Router[i] = []
                    }
                    Router[i].push(bmRouter[i].bind(this))
                }
            }
        }
    })
    Vue.prototype.$router = {
        open(options) {
            options = options || {}
            let currentPageInfo = this.getUrl(options.name)
            if (!currentPageInfo || !currentPageInfo.url) return

            return new Promise((resolve, reject) => {
                router.open({
                    url: currentPageInfo.url,
                    type: options.type || currentPageInfo.type || 'PUSH',
                    params: options.params || {},
                    canBack: options.canBack || _isUndefined(currentPageInfo.canBack) ? true : currentPageInfo.canBack,
                    navShow: _isUndefined(currentPageInfo.navShow) ? true : currentPageInfo.navShow,
                    navTitle: currentPageInfo.navTitle,
                    statusBarStyle: currentPageInfo.statusBarStyle || 'Default',
                    isRunBackCallback: _isFunction(options.backCallback)
                }, (data) => {
                    if (_isFunction(options.backCallback)) {
                        options.backCallback.call(this, data)
                    }
                })
            })
        },
        back(options) {
            options = options || {}
            return new Promise((resolve, reject) => {
                router.back({
                    length: options.length || 1
                }, (data) => {
                    if (_isFunction(options.callback)) {
                        options.callback.call(this, data)
                    }
                    resolve(data)
                })
            })
        },
        getParams(callback) {
            return new Promise((resolve, reject) => {
                router.getParams((params) => {
                    if (_isFunction(callback)) {
                        callback.call(this, params)
                    }
                    resolve(params)
                })
            })
        },
        getUrl(page) {
            let currentPageInfo = Vue.prototype.eros.routes[page]
            if (!currentPageInfo) {
                modal.alert({
                    message: '跳转页面不存在',
                    okTitle: '确定'
                })
                return false
            }
            return currentPageInfo
        },
        refresh() {
            router.refreshWeex()
        },
        setBackParams(params) {
            if (isPlainObject(params)) {
                storage.setData('router.backParams', JSON.stringify(params))
            }
        },
        toWebView(params) {
            if (!params.url) {
                return
            }
            params.title = params.title || 'weex-eros'
                // params.shareInfo = {
                //     title: params.shareTitle,
                //     content: params.content || '',
                //     image: params.image || '',
                //     url: params.url || '',
                //     platforms: params.platforms || [] // 传空的话默认全部
                // }
            if (params.shareInfo) {
                !params.shareInfo.image && (params.shareInfo.image = 'https://img.benmu-health.com/wechat/jyt100.png')
            }

            router.toWebView(params)
        },
        toMap(options) {
            router.toMap(options)
        },
        toCallPhone(phone) {
            if (phone) {
                router.callPhone({
                    phone: phone
                })
            }
        }
    }
}

Vue.use(Router)