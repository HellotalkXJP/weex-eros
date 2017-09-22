<template>
  <bmmask animation="center" class="mask" :duration="300" ref="popView" disableMaskEvent="true">
        <bmpop class="dialog-wrapper">
            <div class="popView-create">
                <text class="pop-title set-space">{{content}}</text>
                <div class="btn-box">
                    <text class="btn-text btn-first-child" @click="cancel">{{cancelText}}</text>
                    <text class="btn-text" @click="ok">{{okText}}</text>
                </div>
            </div>
        </bmpop>
    </bmmask>
</template>

<script>
export default {
    props: {
        content: {
            type: String,
            default: ''
        }, okText:{
            type: String,
            default: '确认'
        }, cancelText: {
            type: String,
            default: '取消'
        }, value: {
            type: Boolean,
            default: false
        }},
    watch: {
        value(newVal) {
            newVal ? this.show() : this.hide()
        }
    },
    methods: {
        show() {
            this.$BM_tools.resignKeyboard()
            this.$refs.popView.show()
            this.$emit('show')
        },
        hide() {
            this.$refs.popView.hide()
            this.$emit('hide')
        },
        ok() {
            this.$BM_tools.resignKeyboard()
            this.$emit('ok')
        },
        cancel() {
            this.$refs.popView.hide()
            this.$emit('cancel')
        }
    }
}
</script>

<style lang="sass">
@import "src/js/css/fragment/popView";
</style>
