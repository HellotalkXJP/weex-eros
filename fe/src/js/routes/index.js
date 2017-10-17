/*
 * @Author: Zero 
 * @Date: 2017-10-09 17:03:00 
 * @Last Modified by:   Zero 
 * @Last Modified time: 2017-10-09 17:03:00 
 */

/**
 * 路由配置
 */

// 这里的后缀都是 .js 因为打包出来的都是js文件，而不是.vue文件，我们要告诉客户端跳转那个js

//  这里的路径和dev.json路径的区别:
//  pages里面配置的是路由跳转的地址，也就是dev生成的 dist文件夹下的js bundle路径 从dist/js开始 
//  dev.json 的 exports 需要打包的js地址，填入src的需要被打包成js bundle的地址   从src开始

// 一个中大型项目中会有很多个页面，通过这样的方式进行拆分和命名能有效减少提交碰撞。

import tab2 from './tab2'
import tab3 from './tab3'
import tab4 from './tab4'

export default {
    ...tab2,
    ...tab3,
    ...tab4
}