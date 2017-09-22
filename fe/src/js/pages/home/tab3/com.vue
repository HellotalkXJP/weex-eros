<template>
<div>
    <text @click="test">{{label}}</text>
    <text>{{list}}</text>
</div>
</template>

<script>
export default {
    props: {
        list: Array
    },
    data() {
        return {
            label: '自路由测试'
        }
    },
    methods: {
        test() {
            console.log(123)
        }
    }
}
</script>

<style>1
  
</style>
