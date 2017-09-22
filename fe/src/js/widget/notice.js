var modal = weex.requireModule('bmModal'),
    Notice = Object.create(null)

import isFunction from 'lodash/isFunction'


Notice.install = (Vue, options) => {
    Vue.prototype.$notice = {
        alert(options){
            if(options.message){
                return new Promise((resolve, reject) => {
                    modal.alert({
                        // titleAlign: options.titleAlign || 'center',
                        title: options.title || '',
                        message: options.message || '',
                        // messageAlign: options.messageAlign || 'center',
                        okTitle: options.okTitle || '确定'
                    }, (params) => {
                        if(isFunction(options.callback)){
                            options.callback.call(params)
                        }
                        resolve()
                    })
                })
            }
        },
        confirm(options){
            if(options.message){
                return new Promise((resolve, reject) => {
                    modal.confirm({
                        // titleAlign: options.titleAlign || 'center',
                        title: options.title || '',
                        message: options.message || '',
                        // messageAlign: options.messageAlign || 'center',
                        cancelTitle: options.cancelTitle || '取消',
                        okTitle: options.okTitle || '确定'
                    }, (params) => {
                        if(isFunction(options.cancelCallback)){
                            options.cancelCallback.call(params)
                        }
                        reject()
                    }, (params) => {
                        if(isFunction(options.okCallback)){
                            options.okCallback.call(params)
                        }
                        resolve()
                    })
                })
            }
        },
        loading: {
            show(message = '') {
                modal.showLoading({message})
            },
            hide() {
                setTimeout(() => {
                    modal.hideLoading()
                }, 200)
            }
        },
        toast(options){
            if(!options.message) return
                modal.toast({
                message: options.message,
                duration: options.duration || 2000
            })
        }
    }
}

Vue.use(Notice)
