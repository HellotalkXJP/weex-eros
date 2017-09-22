import pages from './pages'
import { apis, responseHandler } from './apis'

export const config = {
    pages,
    apis,
    responseHandler
}

Vue.prototype.eros = config

// 字体大小配置
var event = weex.requireModule('bmEvents')
var FONT_MODULUS = weex.config.env.bmFontScale || 1
event.on('FONT-changeFont', (resData) => {
    FONT_MODULUS = resData.fontScale
})
export const fontModules = { FONT_MODULUS }

// 加载iconfont
// 每次新增或者更改都改变url
var dom = weex.requireModule('dom')
dom.addRule('fontFace', {
    'fontFamily': "iconfont",
    'src': "url('https://img.benmu-health.com/iconfont/app-doctor-fe/1.0.4/iconfont.ttf')"
})