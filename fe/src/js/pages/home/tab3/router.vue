<template>
    <scroller>   
        <text @click="back">{{test}}</text>
        <text class="ff-icon ff-login">&#xe6ca;</text>
        <text class="ff-icon ff-login">&#xe6cb;</text>
        <image style="width:550px; height:550px; top:100px" src="bmlocal://assets/test.jpeg"></image>
        <input style="top:150px" placeholder="Input Text" /> 
    </scroller>
</template>

<script>
var storage = weex.requireModule('bmStorage')
import 'Config'
export default {
    data(){
        return {
            test:'这是用路由打开的一个新页面，点击返回上个页面'
        }
    },
    created(){
        setTimeout(() => {
            this.test = '修改测试'
        }, 2000)
    },
    methods: {
        back() {
            this.$router.back({
                length: 1
            })
        }
    }
}
</script>

<style lang="sass">
    @import "src/js/css/core/base";
</style>
