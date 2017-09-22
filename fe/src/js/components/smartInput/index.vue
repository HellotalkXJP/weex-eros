<template>
    <div class="input-main" style="flex-direction: row; align-items: center;">
      <input class="input-box" ref="input" :type="type" @input="updateValue(value)" v-model="value" @focus='onfocus' @blur='onblur' :placeholder="placeholder" :maxlength="maxlength" @change="onchange" :disabled="disabled"/>
      <text class="ff-icon ff-del" v-if="value && showDel" @click="reset">&#xe620;</text>
    </div>
</template>
<script>
  export default {
    props: ['type', 'placeholder', 'maxlength','value', 'nofocus', 'disabled'],
    data() {
      return {
         showDel: false
      }
    },
    methods: {
      updateValue() {
        this.$emit('input', this.value)
      },
      reset() {
        this.value = ''
        this.updateValue()
      },
      // blur 往外抛事件
      onblur(e) {
        this.showDel = false
        this.$emit('blur')
      },
      onchange(e) {
        this.$emit('change', e)
      },
      // 让 input focus，给外部调用的事件
      inputFocus() {
        !this.nofocus && this.$refs.input.focus()
      },
      // 外部设值
      setInputValue(val) {
        this.value = val
        this.updateValue()
      },
      onfocus(e) {
        console.log('111', e)
        if(this.nofocus) return
        this.showDel=true
        this.$emit('focus', e)
      }
    }
    
    // mounted() {
    //   if(this.from == 'login') {
    //      this.$BM_storage.get('loginPhone').then(res => {
    //         this.value = res || ''
    //         this.updateValue()
            
    //     })
    //   }
    // }
  }
</script>

<style lang="sass" src="./index.scss"></style>