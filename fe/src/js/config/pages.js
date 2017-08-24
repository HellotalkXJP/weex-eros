export default {
    // 首页
    'home': {
        // 首页的导航显示，隐藏需要手动在 platform.json 里面调用
        url: '/pages/home/index.js',
    },
    'router.test': {
        // 不设置title自动隐藏导航
        title: '路由测试页面',
        url: '/pages/home/tab2/components/router/index.js',
    },
    'demo': {
        url: '/pages/demo/index.js',
        title: '新闻'
    }
}