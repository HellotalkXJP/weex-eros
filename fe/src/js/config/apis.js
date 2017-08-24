import isFunction from 'lodash/isFunction'

let modal = weex.requireModule('bmModal')

const AJAX_MAP = {
    'COMMON_getInfo': '/test/link'
}

export const responseHandler = (options, resData, resolve, reject) => {
    if (resData && resData.resCode == 0) {
        if (isFunction(options.success)) {
            options.success(resData)
        }
        resolve(resData)
    } else {
        modal.hideLoading()
        const resCode = resData.resCode
        if (resCode == 101) {
            // 不同code的不同处理
            return
        }
        if (!options.noShowDefaultError) {
            // resCode 非 0 的统一处理，且不传入 noShowDefaultError
            // 这里可以做 resData.resCode 的统一处理
            modal.alert({
                message: resData.msg,
                okTitle: '确定'
            })
        }
        if (isFunction(options.error)) {
            options.error(resData)
        }
        reject(resData)
    }
}

// 自动拼接 platform.json 中的 requestUrl
var _apis = {}
for ( var i in AJAX_MAP ){
    if( !_apis[i] ) _apis[i] = undefined
    _apis[i] = weex.config.env.requestUrl + (AJAX_MAP[i])
}
export const apis =  _apis