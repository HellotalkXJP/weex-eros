
<template>
  <div>
    <tabs :tabItems="tabItems" @tabsOnClick="tabsOnClick"></tabs>
  </div>
</template>
<script>
import 'Config'
import tabs from 'Components/tabs/bar'
const IMAGE ='http://gtms01.alicdn.com/tps/i1/TB1qw.hMpXXXXagXXXX9t7RGVXX-46-46.png'
const SELECTED_IMAGE = 'http://gtms04.alicdn.com/tps/i4/TB16jjPMpXXXXazXVXX9t7RGVXX-46-46.png'
export default {
    globalEvent: {
        appActive() {
            console.log('active')
        },
        appDeactive() {
            console.log('deactive')
        }
    },
    data() {
        return {
            tabItems: [{
                index: 0,
                title: 'weex-ui',
                titleColor: '#000000',
                icon: '',
                image: IMAGE,
                selectedImage: SELECTED_IMAGE,
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab1/tab-page/index.js`,
                visibility: 'visible',
            },
            {
                index: 1,
                title: 'bui-weex',
                titleColor: '#000000',
                icon: '',
                image: IMAGE,
                selectedImage: SELECTED_IMAGE,
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab2/index.js`,
                visibility: 'hidden',
            },
            {
                index: 2,
                title: 'eros-widget',
                titleColor: '#000000',
                icon: '',
               image: IMAGE,
                selectedImage: SELECTED_IMAGE,
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab3/index.js`,
                visibility: 'hidden',
            },
            {
                index: 3,
                title: 'eros-demo',
                titleColor: '#000000',
                icon: '',
                image: IMAGE,
                selectedImage: SELECTED_IMAGE,
                src: `${weex.config.env.jsServer}/fe/dist/js/pages/home/tab4/index.js`,
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
