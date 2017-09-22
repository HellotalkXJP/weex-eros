<template>
    <!--<div>-->
        <bmmask class="mask" ref="selectpop" duration="300" animation="bottom">
            <bmpop class="dialog-wrap" :style="{ height: height + 10 }">
                <div class="pop-select" :style="{ height }">
                    <div class="pop-hd" ref="header">
                        <text class="cancle"></text>
                        <text class="hd-title">{{title}}</text>
                        <text class="hd-done" @click='hide'>{{btnName}}</text>
                    </div>
                    <list class="list-worder">
                       <cell v-for='(item, index) in data' :ref="'pop_item_' + index">
                            <div class="worder-cell" @click="choose(item)">
                                <text class='list-item-txt' :class="[item[valueKey] === value ? 'list-item-on' : '']"> {{ item[labelKey] }}</text>
                            </div>
                        </cell>
                    </list>
                </div>
            </bmpop>
        </bmmask>
    <!--</div>-->
</template>

<style lang="sass" src="./index.scss"></style>

<script>
    const dom = weex.requireModule('dom')
    export default {
        props: {
            title: {
                type: String,
                default: '请选择'
            },
            btnName: {
                type: String,
                default: '取消'
            },
            labelKey: {
                type: String,
                default: 'label'
            },
            valueKey: {
                type: String,
                default: 'value'
            },
            data: {
                type: Array,
                default () {
                    return []
                }
            },
            value: {
                type: [String, Number]
            }
        },
        data() {
            return {
                height: 500
            }
        },
        // watch: {
        //     data(newVal) {
        //         let length = newVal.length
        //         console.log(length)
        //             // refHeader = this.$refs.header[0],
        //             // refCell = this.$refs['pop_item_0'][0]

        //         if(!length) return
        //         if(length <= 4) {
        //             // dom.getComponentRect(refHeader, info => {
        //             //     console.log(info)
        //             // })
        //         }else {
        //             this.height = 500
        //         }
        //     }
        // },
        methods: {
            choose(item) {
                this.$emit('choose', item)
            },
            show() {
                this.$BM_tools.resignKeyboard()
                this.$refs.selectpop.show()
                this.scrollEl()
                this.$emit('show')
            },
            hide() {
                this.$BM_tools.resignKeyboard()
                this.$refs.selectpop.hide()
                this.$emit('hide')
            },
            scrollEl() {
                let length = this.data.length
                if(!length || length <= 4) return
                let index = this.data.findIndex(item => item[this.valueKey] === this.value)
                if(index === -1) return
                let el = this.$refs['pop_item_' + index][0]
                dom.scrollToElement(el, {offset: index != 0 ? -110 : 0})
            }
        }
    }
</script>