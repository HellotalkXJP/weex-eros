import _isArray from 'lodash/isArray'
import _isString from 'lodash/isString'
import isEmpty from 'lodash/isEmpty'

/**
 * have
 * @param {目标数组} target 
 * @param {想要验证的值 可以是数组或者是字符串} value 
 * @param {是否只有验证数组里的一项，全部满足才能返回true} justSingle 
 */
export const have = (target, value, justSingle) => {
    if(!target.length || !value) return
    if(!_isArray(value) && _isString(value)) {
      return target.indexOf(value) > -1 ? true : false
    }
    if(_isArray(value) && value.length && justSingle) {
      let result = true
      for(var i= 0, len = value.length ; i < len; i++){
        if(target.indexOf(value[i]) === -1){
          result = false
          break
        }
      }
      return result
    }
    if(_isArray(value) && value.length && !justSingle) {
      let result = false
      for(var i= 0, len = value.length ; i < len; i++){
        if(target.indexOf(value[i]) > -1){
          result = true
          break
        }
      }
      return result
    }
}


/**
 * 获取平台信息
 * @param {*} config vm.$getConfig()
 */
let platformInfo = {}
export const getPlatformInfo = (config) => {
    if(isEmpty(platformInfo)){
        platformInfo = {
            osName:  config.env.osName,
            platform: config.env.platform,
            osVersion: config.env.osVersion,
            isWeb: config.env.platform === 'Web',
            isiOS: config.env.platform === 'iOS',
            isAndroid: config.env.platform === 'android'
        }
    }
    return platformInfo
}