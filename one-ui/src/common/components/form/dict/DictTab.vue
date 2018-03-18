<template>
  <FormControllStatic>
    <div class="row">
      <div class="col-md-tag" @click="dictValue = ''">
        <div class="one-tag-item" :class="dictValue == '' ? 'active' : ''">全部</div>
      </div>
      <template v-if="!showMore">
        <template v-for="(dict, index) of dictList">
          <div class="col-md-tag" @click="dictValue = dict.value" v-if="index < 6">
            <div class="one-tag-item" :class="dictValue == dict.value ? 'active' : ''">{{ dict.label }}</div>
          </div>
        </template>
        <div class="col-md-tag" v-if="dictList.length > 7" @click="showMore = !showMore">
          <div class="one-tag-item-more"><a>更多</a></div>
        </div>
      </template>
      <template v-if="showMore">
        <template v-for="(dict, index) of dictList">
          <div class="col-md-tag" @click="dictValue = dict.value">
            <div class="one-tag-item" :class="dictValue == dict.value ? 'active' : ''">{{ dict.label }}</div>
          </div>
        </template>
        <div class="col-md-tag" v-if="dictList.length > 7" @click="showMore = !showMore">
          <div class="one-tag-item-more"><a>收起</a></div>
        </div>
      </template>
    </div>
  </FormControllStatic>
</template>

<script>
import DictMixin from './DictMixin'

export default {
  mixins: [DictMixin],
  name: 'DictTab',
  data: function () {
    return {
      showMore: false
    }
  }
}
</script>

<style lang="less">
.col-md-tag {
  width: 12%;
  float: left;
  position: relative;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
  margin-top: 2px;
  margin-bottom: 2px;

  .one-tag-item-more {
    text-align: center;
    width: 20%;
    display: inline;
    padding: .2em .7em .3em;
    font-size: 80%;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: 1px;
    cursor: pointer;
  }

  .one-tag-item {
    text-align: center;
    width: 20%;
    display: inline;
    padding: .2em .7em .3em;
    font-size: 80%;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: 1px;
    cursor: pointer;

    &:hover {
      color: #fff;
      background-color: #337ab7;
    }
  }

  .active {
    color: #fff;
    background-color: #337ab7;
  }
}

</style>
