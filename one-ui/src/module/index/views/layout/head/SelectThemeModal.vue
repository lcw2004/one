<template>
  <OneModal :title="config.title" :show.sync="config.show">
    <form class="form-horizontal">
      <ul class="list-unstyled clearfix select-theme">
        <li v-for="obj of themeList" @click="selectTheme(obj)">
          <a class="clearfix full-opacity-hover">
            <div><span class="theme-head-left" :style="{ background: obj.color1 }"></span><span class="theme-head-right" :style="{ background: obj.color2 }"></span></div>
            <div><span class="theme-body-left" :style="{ background: obj.color3 }"></span><span class="theme-body-right"></span></div>
          </a>
          <p class="text-center no-margin">{{ obj.name }}</p>
        </li>
      </ul>
    </form>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  data: function () {
    return {
      themeList: [
        { name: '蓝-黑', theme: 'skin-blue', color1: '#367fa9', color2: '#3c8dbc', color3: '#222d32' },
        { name: '白-黑', theme: 'skin-black', color1: '#fefefe', color2: '#fefefe', color3: '#222222' },
        { name: '紫-黑', theme: 'skin-purple', color1: '#555299', color2: '#605ca8', color3: '#222d32' },
        { name: '绿-黑', theme: 'skin-green', color1: '#008d4c', color2: '#00a65a', color3: '#222d32' },
        { name: '红-黑', theme: 'skin-red', color1: '#d33724', color2: '#dd4b39', color3: '#222d32' },
        { name: '黄-黑', theme: 'skin-yellow', color1: '#db8b0b', color2: '#f39c12', color3: '#222d32' },
        { name: '蓝-白', theme: 'skin-blue-light', color1: '#367fa9', color2: '#3c8dbc', color3: '#f9fafc' },
        { name: '白-白', theme: 'skin-black-light', color1: '#fefefe', color2: '#fefefe', color3: '#f9fafc' },
        { name: '紫-白', theme: 'skin-purple-light', color1: '#555299', color2: '#555299', color3: '#f9fafc' },
        { name: '绿-白', theme: 'skin-green-light', color1: '#008d4c', color2: '#00a65a', color3: '#f9fafc' },
        { name: '红-白', theme: 'skin-red-light', color1: '#d33724', color2: '#dd4b39', color3: '#f9fafc' },
        { name: '黄-白', theme: 'skin-yellow-light', color1: '#db8b0b', color2: '#f39c12', color3: '#f9fafc' }
      ]
    }
  },
  methods: {
    selectTheme (obj) {
      let classList = document.body.classList
      for (let c of classList) {
        if (c.startsWith('skin-')) {
          classList.remove(c)
        }
      }
      classList.add(obj.theme)
      this.close()
    }
  }
}
</script>

<style lang="less" type="text/less">
.select-theme {
  li {
    float:left;
    width: 33.33333%;
    padding: 5px;

    a {
      display: block;
      box-shadow: 0 0 3px rgba(0,0,0,0.4);
      border: 1px solid #fff;

      .theme-head-left {
        display:block;
        float: left;
        width: 20%;
        height: 15px;
      }

      .theme-head-right {
        display:block;
        float: left;
        width: 80%;
        height: 15px;
      }

      .theme-body-left {
        display:block;
        float: left;
        width: 20%;
        height: 30px;
      }

      .theme-body-right {
        display:block;
        float: left;
        width: 80%;
        height: 30px;
        background: #f4f5f7;
      }

      &:hover {
        border: 1px solid #f00;
      }
    }
  }
}
</style>
