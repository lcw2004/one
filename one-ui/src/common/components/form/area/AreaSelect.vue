<template>
  <div class="cascader">
    <el-cascader
      placeholder="请选择所属地区"
      v-model="innerValue"
      :options="areaList"
      :props="props"
      :clearable="true"
      :filterable="true"
      size="small"
      :change-on-select="changeOnSelect"
      @change="onChange">
    </el-cascader>
  </div>
</template>

<script>
export default {
  name: 'AreaSelect',
  props: {
    value: {
      type: String
    },
    changeOnSelect: {
      type: Boolean,
      default: false
    }
  },
  data: function () {
    return {
      props: {
        value: 'id',
        label: 'name',
        children: 'childList'
      }
    }
  },
  computed: {
    topArea: function () {
      return this.$store.state.system.topArea
    },
    areaList: function () {
      if (this.topArea.childList) {
        return this.topArea.childList
      } else {
        return []
      }
    },
    innerValue: {
      get: function () {
        let area = this.elementMap[this.value]
        let areaIdList = []
        if (area) {
          let areaIds = area.parentIds + area.id
          if (areaIds) {
            areaIdList = areaIds.split(',')
          }
        } else {
          areaIdList = []
        }
        return areaIdList
      },
      set: function (newVal) {
        this.onChange(newVal)
      }
    },
    elementMap: function () {
      let map = {}
      const asListRecursion = function (element, map) {
        map[element.id] = element
        const childList = element.childList
        if (childList) {
          for (let child of childList) {
            asListRecursion(child, map)
          }
        }
      }
      asListRecursion(this.topArea, map)
      return map
    }
  },
  mounted () {
    if (!this.topArea.id) {
      this.$store.dispatch('initArea')
    }
  },
  methods: {
    onChange (value) {
      let lastLevelId = value[value.length - 1]
      this.$emit('input', lastLevelId)
    }
  }
}
</script>

<style lang="less" type="text/less">
.cascader {
  .el-cascader {
    width: 100%;

    input {
      border-radius: 0 !important;
    }
  }
}
.el-cascader-menu {
  min-width: 180px !important;
}
</style>
