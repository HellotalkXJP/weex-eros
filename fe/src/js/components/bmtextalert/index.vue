<template>
    <bmmask animation="center" class="mask" :duration="300" ref="popView" @show="onPopShow" @hide="onPopHide" disableMaskEvent="true">
        <bmpop class="dialog-wrapper">
            <div class="popView-remark">
                <div class="wrap-label flex-row-start">
                    <text class="line line-orange"></text>
                    <text class="label-tit c-base">{{title}}</text>
                </div>
                <div class="textarea-box pb0">
                    <textarea ref="textArea" class="note-pop" rows="5" @input="update" v-model="text" :maxlength='maxlength' :rows="rows"  @focus='onfocus' @blur='onblur' @change="onchange" :disabled="disabled" :placeholder="placeholder"></textarea>
                    <text class="word-limit mr-both-40 set-limit-pos">{{text.length}}/{{maxlength}}</text>
                </div>
                <div class="btn-box">
                    <text class="btn-text btn-first-child" @click="cancel">{{cancelText}}</text>
                    <text class="btn-text" :class="[text.length === 0 ? 'btn-gray-txt' : '']" @click="ok">{{okText}}</text>
                </div>
            </div>
        </bmpop>
    </bmmask>
</template>

<script>
export default {
    props: {
        title: {
            type: String,
            default: '请填写备注内容'
        }, 
        okText: {
            type: String,
            default: '提交'
        }, 
        cancelText: {
            type: String,
            default: '取消'
        }, 
        // value: {
        //     type: String,
        // },
        maxlength: {
            type: Number,
            default: 100
        },
        rows: {
            type: String,
            default: 8
        },
        placeholder: {
            type: String,
            default: '请详细描述备注内容'
        },
        disabled: {
            type: Boolean,
            default: false
        },
        autofocus: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            text: ''
        }
    },
    methods: {
        update(val) {
            this.$emit('update', val)
        },
        clear(){
            this.text = ''
        },
        show() {
            // this.$BM_tools.resignKeyboard()
            this.$refs['textArea'].focus()
            this.$refs.popView.show()
            this.$emit('show')
        },
        hide() {
            this.$refs['textArea'].blur()
            this.$refs.popView.hide()
            this.$emit('hide')
        },
        ok() {
            if(!this.text) return
            this.$emit('ok', this.text)
        },
        cancel() {
            this.$refs.popView.hide()
            this.$emit('cancel')
        },
        onfocus() {
            this.$emit('focus')
        },
        onblur() {
            this.$emit('blur')
        },
        onchange() {
            this.$emit('change')
        },
    }
}
</script>

<style lang="sass">
@import "src/js/css/fragment/form";
@import "src/js/css/fragment/popView";
@import "src/js/css/module/common-order";
.btn-gray-txt {
    color: $btn-gray;
}
</style>

