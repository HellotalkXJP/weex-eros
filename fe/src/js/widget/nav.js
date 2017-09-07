var navigator = weex.requireModule('bmNavigator'),
    modal = weex.requireModule('bmModal')

import isFunction from 'lodash/isFunction'

var Navigator = Object.create(null)

Navigator.install = (Vue, options) => {
    Vue.prototype.$navigator = {
        setLeftItem(options, callback){
            navigator.setLeftItem(options, () => {
                if(!!options.clickCallback) return
                isFunction(options.clickCallback) && options.clickCallback()
            })
        },
        setRightItem(options, callback){
            navigator.setRightItem(options, () => {
                if(!!options.clickCallback) return
                isFunction(options.clickCallback) && options.clickCallback()
            })
        },
        setCenterItem(options, callback){
            navigator.setCenterItem(options, () => {
                if(!!options.clickCallback) return
                isFunction(options.clickCallback) && options.clickCallback()
            })
        },
        setNavigationInfo(options, callback){
            navigator.setNavigationInfo(options, () => {
                if(!!options.clickCallback) return
                isFunction(options.clickCallback) && options.clickCallback()
            })
        }
    }
}

Vue.use(Navigator)
