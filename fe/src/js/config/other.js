// 字体大小配置
var event = weex.requireModule('bmEvents'),
    _fontModules = weex.config.env.bmFontScale || 1

event.on('FONT-changeFont', (resData) => {
    _fontModules = resData.fontScale
})
export const fontModules = _fontModules

// 加载iconfont
// 每次新增或者更改都改变url

var domModule = weex.requireModule("dom"),
    bundleUrl = weex.config.env.jsServer,
    url = bundleUrl + '/fe/dist/iconfont/iconfont.ttf';

// 这里添加 bui 依赖的 iconfont
var res = domModule.addRule('fontFace', {
    'fontFamily': 'bui-iconfont',
    'src': "url('" + url + "')"
});