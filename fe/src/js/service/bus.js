new Vue({
    template: '<div class="wrap"></div>',
    data() {
        return {
            // session
            count: 0
        }
    },
    methods: {
        bindEvent() {
            this.$bus.on("to.bus.add", resData => {
                this.count ++
            })
        }
    },
    mounted() {
        this.bindEvent()
    }
});
