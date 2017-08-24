<template>
  <div>
    <text> </text>
    <list class="list">
      <cell v-for="(v,i) in rows" append="tree" :index="i" :key="i" class="row" @appear="onappear(i, $event)" @disappear="ondisappear(i, $event)">
        <div class="item" @click="handle(v.type)">
          <text class="item-title">{{'id:' + v.id}} - {{v.name}}</text>
        </div>
      </cell>
    </list>
    <text>本木二次封装和拓展的插件。</text>
    <text>lat:{{this.lat}}-lng:{{this.lng}}</text>
    <text :value="'当前显示id: ' + appearMin + ' ~ ' + appearMax"></text>
  </div>
</template>

<style scoped>
.count {
  font-size: 48px;
  margin: 10px;
}

.indicator {
  height: 40px;
  width: 40px;
  color: #45b5f0;
}

.row {
  width: 750px;
}

.item {
  justify-content: center;
  border-bottom-width: 2px;
  border-bottom-color: #c0c0c0;
  height: 100px;
  padding: 20px;
}

.item:active {
  background-color: #00BDFF;
}

</style>

<script>
module.exports = {
  methods: {
    handle(type) {
      this[`handle_${type}`]()
    },
    handle_axios() {
      this.$fetch({
        name: 'COMMON_getInfo',
        data: {
          weex: 'eros'
        }
      })
    },
    handle_router() {
      this.$router.open({
        name: 'router.test'
      })
    },
    handle_routerPresent() {
      this.$router.open({
        name: 'router.test',
        animateType: 'PRESENT'
      })
    },
    handle_routerWebView() {
      this.$router.toWebView({
        url: 'https://www.baidu.com'
      })
    },
    handle_alert() {
      this.$notice.alert({
        title: '提示',
        message: '这是一个弹窗',
        okTitle: '确定',
        callback: () => {
          this.$notice.toast({message: '您点击了确定'})
        }
      })
    },
    handle_confirm() {
      this.$notice.confirm({
        title: '提示',
        message: '这是一个选择弹窗',
        okTitle: '确定文案',
        cancelTitle: '取消文案',
        okCallback: () => {
          this.$notice.toast({message: '您点击了确定'})
        },
        cancelCallback: () => {
          this.$notice.toast({message: '您点击了取消'})
        }
      })
    },
    handle_loading() {
      this.$notice.loading.show('loading自定义文案')
      setTimeout(() => {
        this.$notice.loading.hide()
      }, 1000)
    },
    handle_toast() {
      this.$notice.toast({message: '这是一个toast'})
    },
    handle_geo() {
      this.$geo.get((resData) => {
          this.lat = resData.data.locationLat
          this.lng = resData.data.locationLng
      })
    },
    handle_scanCode() {
      this.$camera.scanCode(resData => {
          this.$notice.alert({
          title: '提示',
          message: resData
        })
      })
    },
    handle_uploadImg() {
      this.$camera.uploadImg({
        maxCount: 3,
      },resData => {
          this.$notice.alert({
          title: '提示',
          message: resData
        })
      })
    },
    onappear: function (idx, e) {
      var appearId = this.rows[idx].id;
      var appearIds = this.appearIds;
      appearIds.push(appearId);
      this.getMinAndMaxIds(appearIds);
    },
    ondisappear: function (idx, e) {
      var disAppearId = this.rows[idx].id;
      var appearIds = this.appearIds;
      var index = appearIds.indexOf(disAppearId);
      if (index > -1) {
        appearIds.splice(index, 1);
      }
      this.getMinAndMaxIds(appearIds);
    },
    getMinAndMaxIds: function (appearIds) {
      appearIds.sort(function (a, b) {
        return a - b;
      });
      this.appearIds = appearIds;
      this.appearMax = appearIds[appearIds.length - 1];
      this.appearMin = appearIds[0];
    }
  },
  data: function () {
    return {
      appearMin: 1,
      appearMax: 1,
      appearIds: [],
      lat: '',
      lng: '',
      rows: [
        {
          id: 1,
          name: '发送请求',
          type: 'axios',
        },
        {
          id: 2,
          name: '正常打开一个页面',
          type: 'router',
        },
        {
          id: 3,
          name: '插入打开一个页面',
          type: 'routerPresent',
        },
        {
          id: 3,
          name: '插入打开一个webview',
          type: 'routerWebView',
        },
        {
          id: 4,
          name: '提示弹窗',
          type: 'alert',
        },
        {
          id: 5,
          name: '选择弹窗',
          type: 'confirm',
        },
        {
          id: 6,
          name: 'loading弹窗',
          type: 'loading',
        },
        {
          id: 7,
          name: 'toast弹窗',
          type: 'toast',
        },
        {
          id: 8,
          name: '获取当前坐标',
          type: 'geo',
        },
        {
          id: 9,
          name: '扫一扫',
          type: 'scanCode',
        },
        {
          id: 10,
          name: '上传图片',
          type: 'uploadImg',
        }]
    }
  }
}
</script>
