
<template>
  <div>
    <tabs :tabItems="tabItems" @tabsOnClick="tabsOnClick"></tabs>
  </div>
</template>
<script>
import 'Config'
import tabs from 'Components/tabs/bar'
export default {
    created() {
        console.log('weex-eros:',weex.config.env.jsServer)
    },
    data() {
        return {
            tabItems: [{
                index: 0,
                title: '瀑布流',
                titleColor: '#000000',
                icon: '',
                image: 'http://gtms01.alicdn.com/tps/i1/TB1qw.hMpXXXXagXXXX9t7RGVXX-46-46.png',
                selectedImage: 'http://gtms04.alicdn.com/tps/i4/TB16jjPMpXXXXazXVXX9t7RGVXX-46-46.png',
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab1/index.js`,
                visibility: 'visible',
            },
            {
                index: 1,
                title: 'eros',
                titleColor: '#000000',
                icon: '',
                image: 'http://gtms03.alicdn.com/tps/i3/TB1LEn9MpXXXXaUXpXX9t7RGVXX-46-46.png',
                selectedImage: 'http://gtms02.alicdn.com/tps/i2/TB1qysbMpXXXXcnXXXX9t7RGVXX-46-46.png',
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab2/index.js`,
                visibility: 'hidden',
            },
            {
                index: 2,
                title: 'tab3',
                titleColor: '#000000',
                icon: '',
                image: 'http://gtms01.alicdn.com/tps/i1/TB1B0v5MpXXXXcvXpXX9t7RGVXX-46-46.png',
                selectedImage: 'http://gtms04.alicdn.com/tps/i4/TB1NxY5MpXXXXcrXpXX9t7RGVXX-46-46.png',
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab3/index.js`,
                visibility: 'hidden',
            }],
        }
    },
    components: {
        tabs
    },
    methods: {
        tabsOnClick(e) {
            console.log('tabsOnClick', e.index)
        }
    }
}
</script>
