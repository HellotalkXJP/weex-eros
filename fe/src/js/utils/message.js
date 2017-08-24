const router = weex.requireModule('bmRouter');

import _isArray from 'lodash/isArray'
const MESSAGE_MAP = {
    gongdan() {
        var pageCfg = this.eros.pages['worderCount']
        router.open({
            url: pageCfg.url,
            animateType: 'PUSH',
            params: {},
            authorize: true,
            navigationInfo: {
                hideNavbar: false,
                title: pageCfg.title
            }
        })
    }
}

// 事件分发器
export const distribute = (options, eventHandle) => {
    MESSAGE_MAP[options.type] && MESSAGE_MAP[options.type](options)
}

// 用户消息推送
const commonMsg = (options, eventHandle) => {
    var pageCfg = getPageUrl('message_lists'),
        nowPage = weex.config.bundleUrl
    if (options.trigger && nowPage.indexOf(pageCfg.url) === -1) {
        router.open({
            url: pageCfg.url,
            animateType: 'PUSH',
            params: {},
            authorize: true,
            navigationInfo: {
                hideNavbar: false,
                title: pageCfg.title
            }
        })
    } else {
        _isArray(eventHandle['pushMessage']) && eventHandle['pushMessage'].map((item) => {
            item(options)
        })
    }
}