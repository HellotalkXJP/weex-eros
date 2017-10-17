var storage = weex.requireModule('bmStorage'),
    modal = weex.requireModule('bmModal')

import isEmpty from 'lodash/isEmpty'
import isFunction from 'lodash/isFunction'

var Storage = Object.create(null)

Storage.install = (Vue, options) => {
    Vue.prototype.$storage = {
        set(key, value, callback) {

            return new Promise((resolve, reject) => {
                storage.setData(key, value, (resData) => {
                    if (isFunction(callback)) {
                        callback.call(this, resData)
                    }
                    if (resData && resData.resCode == 0) {
                        resolve(true)
                    } else {
                        reject(false)
                    }
                })
            })
        },
        setSync(key, value) {
            let resData = storage.setDataSync(key, value)
            return resData && resData.resCode == 0
        },
        get(key, callback) {
            return new Promise((resolve, reject) => {
                storage.getData(key, (resData) => {
                    resData.data = JSON.parse(resData.data.value || '{}')
                    if (isFunction(callback)) {
                        callback.call(this, resData)
                    }
                    if (resData && resData.resCode == 0) {
                        resolve(resData.data)
                    } else if (resData && resData.resCode == 9) {
                        // 如果发现值不存在则返回空字符串
                        resolve(false)
                    } else {
                        reject(resData)
                    }
                })
            })
        },
        getSync(key) {
            let resData = storage.getDataSync(key),
                _code = resData && resData.resCode

            resData.data = JSON.parse(resData.data.value || '{}')
            if (_code == 0) {
                return resData.data
            }
            if (_code == 9) {
                return false
            }
        },
        delete(key, callback) {
            return new Promise((resolve, reject) => {
                storage.deleteData(key, (resData) => {
                    if (isFunction(callback)) {
                        callback.call(this, resData)
                    }
                    if (resData && resData.resCode == 0) {
                        resolve(true)
                    } else {
                        reject(false)
                    }
                })
            })
        },
        deleteSync(key) {
            let resData = storage.deleteDataSync(key)
            return resData && resData.resCode == 0
        },
        removeAll(callback) {
            return new Promise((resolve, reject) => {
                storage.removeData((resData) => {
                    if (isFunction(callback)) {
                        callback.call(this, resData)
                    }
                    if (resData && resData.resCode == 0) {
                        resolve(true)
                    } else {
                        reject(false)
                    }
                })
            })
        },
        removeAllSync() {
            let resData = storage.removeDataSync()
            return resData && resData.resCode == 0
        }
    }
}

Vue.use(Storage)