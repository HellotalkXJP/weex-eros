import pages from './pages'
import { apis, responseHandler } from './apis'

export const config = {
    pages,
    apis,
    responseHandler,
    // ajax请求域名
    REQUEST_URL: weex.config.env.requestUrl,
    // 前端静态资源的基础 url
    PAGE_BASE_URL: weex.config.env.localUrl,
    // js绝对路径，使用tab组件的时候会用到
    JS_AB_PATH: `${weex.config.env.localUrl}/fe/dist/js`,
}

Vue.prototype.eros = config

const ICON_FONT_URL = 'https://img.benmu-health.com/iconfont/app-doctor-fe/1.0.4/iconfont.ttf'

// 字体大小配置
var event = weex.requireModule('bmEvents')
var FONT_MODULUS = weex.config.env.bmFontScale || 1
event.on('FONT-changeFont', (resData) => {
    FONT_MODULUS = resData.fontScale
})
export const fontModules  = { FONT_MODULUS }

// 加载iconfont
// 每次新增或者更改都改变url
var dom = weex.requireModule('dom')
dom.addRule('fontFace', {
    'fontFamily': "iconfont",
    'src': "url(" + ICON_FONT_URL + ")"
})
