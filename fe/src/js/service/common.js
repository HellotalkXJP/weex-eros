export default {
    // 接口配置
    'common/getInfo': {
        method: 'GET', // 请求 必填
        desc: '', // 接口描述 选填
        header: {}, // 请求头 选填 默认application/json
        mockPath: '/test/info', // mock接口地址 选填
        truePath: '/test/info', // zhen'shi 必填
        params: {
            a: 1 // 请求 必填
        }
    }
}