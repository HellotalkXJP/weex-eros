<template>
  <div>
    <div class="title">
      <text class="text">我是tab3</text>
    </div>
    <div class="panel">
      <text class="text" @click="go">新闻DEMO</text>
    </div>
  </div>
  
</template>
<script>
  import 'Config'
  export default {
    data () {
      return {
        lists: [1, 2, 3, 4, 5]
      }
    },
    methods: {
      go(){
        this.$router.open({
          name: 'demo',
          title: '新闻'
        })
      }
    }
  }
</script>
<style scoped>
  .title{
    width: 600px;
    height: 250px;
    margin-left: 75px;
    margin-top: 35px;
    margin-bottom: 10px;
    flex-direction: column;
    justify-content: center;
  }
  .panel {
    width: 600px;
    height: 250px;
    margin-left: 75px;
    margin-bottom: 35px;
    flex-direction: column;
    justify-content: center;
    border-width: 2px;
    border-style: solid;
    border-color: rgb(162, 217, 192);
    background-color: rgba(162, 217, 192, 0.2);
  }
  .text {
    font-size: 50px;
    text-align: center;
    color: #41B883;
  }
</style>