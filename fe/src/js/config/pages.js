

// 这里的后缀都是 .js 因为打包出来的都是js文件，而不是.vue文件，我们要告诉客户端跳转那个js

//  这里的路径和dev.json路径的区别
//  pages里面配置的是路由跳转的地址，也就是dev生成的 dist文件夹下的js bundle路径 从dist/js开始 
//  dev.json 的 exports 需要打包的js地址，填入src的需要被打包成js bundle的地址   从src开始

export default {
    // 首页
    'home': {
        // 首页的导航显示，隐藏需要手动在 platform.json 里面调用
        url: '/pages/home/index.js',
    },
    'router.test': {
        // 不设置title自动隐藏导航
        title: '路由测试页面',
        url: '/pages/home/tab2/router.js',
    },
    'router.amap': {
        title: '地图页面',
        url: '/pages/home/tab2/amap.js'
    },
    'demo': {
        url: '/pages/demo/index.js',
        title: '新闻'
    }
}