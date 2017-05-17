<template>
<transition name="zoom">
  <div class="modal" v-show="config.show" style="display: block">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
            <span aria-hidden="true">×</span>
          </button>
          <h4 class="modal-title" v-text="config.title"></h4>
        </div>
        <div class="modal-body modal-scrollable">
          <div class="row">
            <div class="col-md-12 icon"  v-for="icons of iconList">
              <h2>{{ icons.type }}</h2>

              <ul class="bs-glyphicons">
                <li v-for="icon of icons.iconList" @click="selected = icon">
                  <i :class="['fa', icon]"></i>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <div class="pull-left">
            选中 <span v-if="selected" class="icon-selected"><i :class="['fa', selected]"></i></span>
          </div>
          <button type="button" class="btn btn-default " data-dismiss="modal" @click="config.show = false">取消</button>
          <button type="button" class="btn btn-primary" @click="ok()">确认</button>
        </div>
      </div>
    </div>
  </div>
</transition>
</template>

<script>
import iconList from './icons'

export default {
  props: {
    config: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      iconList: iconList,
      selected: ''
    }
  },
  methods: {
    ok () {
      this.config.show = false
      if (this.selected) {
        this.$emit('input', 'fa ' + this.selected)
      }
    }
  }
}
</script>

<style>
.icon-selected {
  margin-top: 5px;
  margin-left: 15px;
  color: red;
  font-size: 24px
}

.bs-glyphicons {
  padding-left: 0;
  padding-bottom: 1px;
  margin-bottom: 20px;
  list-style: none;
  overflow: hidden;
}

.bs-glyphicons li {
  float: left;
  width: 10%;
  height: 85px;
  padding: 10px;
  margin: 0 -1px -1px 0;
  font-size: 12px;
  line-height: 1.4;
  text-align: center;
  border: 1px solid #ddd;
}

.bs-glyphicons li:hover {
  background-color: rgba(86, 61, 124, .1);
}

.bs-glyphicons i {
  font-size: 24px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.bs-glyphicons .glyphicon {
  margin-top: 5px;
  margin-bottom: 10px;
  font-size: 24px;
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.bs-glyphicons .glyphicon-class {
  display: block;
  text-align: center;
  word-wrap: break-word;
}
</style>
