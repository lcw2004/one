<template>
  <div class="cascader">
    <el-cascader
      v-model="innerValue"
      :placeholder="placeholder"
      :options="elementList"
      :props="props"
      :clearable="true"
      :filterable="true"
      expand-trigger="hover"
      size="small"
      :change-on-select="changeOnSelect"
      @change="onChange">
    </el-cascader>
  </div>
</template>

<script>
export default {
  name: 'TreeSelect',
  components: {},
  props: {
    value: {
      type: [String, Object]
    },
    placeholder: {
      type: String,
      default: '请选择'
    },
    topElement: {
      type: Object,
      required: true
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
    elementList: function () {
      if (this.topElement.childList) {
        return this.topElement.childList
      } else {
        return []
      }
    },
    innerValue: {
      get: function () {
        let area = this.elementMap[this.value.id]
        let areaIdList = []
        if (area) {
          let areaIds = area.parentIds + area.id
          areaIdList = areaIds.split(',')
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
      asListRecursion(this.topElement, map)
      return map
    }
  },
  mounted () {
    if (!this.topElement.id) {
      this.$store.dispatch('initPurchaseType')
    }
  },
  methods: {
    onChange (value) {
      let lastLevelId = value[value.length - 1]
      this.$emit('change', this.elementMap[lastLevelId])
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
