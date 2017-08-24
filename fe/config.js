/**
 * @Author: songqi
 * @Date:   2017-02-06
 * @Last modified by:   songqi
 * @Last modified time: 2017-05-08
 */

module.exports = {
    'server': {
        'path': '../',
        'port': 80
    },
    'proxy': [{
        'route': '/test',
        'target': '127.0.0.1:52077/test'
    }],
    'mockServer': {
        'port': 52077,
        'mockDir': './dist/mock'
    },
    'weex': true,
    'exports': [
        /************
         ** 所有页面的中介者 **
         *************/
        'js/service/bus.js',

        /************
         ** 首页 **
         *************/
        'js/pages/home/index.js',
        'js/pages/demo/index.js',
        'js/pages/home/tab1/index.js',
        'js/pages/home/tab2/index.js',
        'js/pages/home/tab2/components/router/index.js',
        'js/pages/home/tab3/index.js'
    ],
    'framework': '// { "framework": "Vue" } \n',
    'alias': {
        'Components': 'js/components',
        'Common': 'js/common',
        'Config': 'js/config',
        'Widget': 'js/widget',
        'Pages': 'js/pages',
        'Utils': 'js/utils',
        'Store': 'js/store'
    },
    'appName': 'bm-weex-fe',
    // 'jsPath': 'https://fe.benmu-health.com/fe/',
    'zipFolder': '/home/app',
    'iosZipFolder': '/ios/WeexEros/WeexEros',
    'androidZipFolder': '/android/WeexFrameworkWrapper/app/src/main/assets',
    'version': {
        'android': '1.0.0',
        'iOS': '1.0.0'
    }
};