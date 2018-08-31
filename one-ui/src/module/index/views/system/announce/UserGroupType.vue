<template>
  <div>
    <div class="radio">
      <label><input type="radio" :value="1" v-model="innerType">全公司</label>
    </div>
    <div class="radio">
      <label><input type="radio" :value="2" v-model="innerType">部门</label>
    </div>
    <div class="margin-left-30" v-if="innerType == 2">
      <Tree :element="topOffice" v-model="innerValue" select-type="checkbox" :isSelectParent="false"></Tree>
    </div>

    <div class="radio">
      <label><input type="radio" :value="3" v-model="innerType">角色</label>
    </div>
    <div class="margin-left-20" v-if="innerType == 3">
      <div class="checkbox-inline margin-left-10" v-for="role of rolelist">
        <label>
          <input type="checkbox" :value="role.id" v-model="innerValue" v-validate="'required'" name="角色"> {{ role.name }}
        </label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: Number,
      required: true
    },
    value: {
      type: Array,
      required: true
    }
  },
  data: function () {
    return {
      rolelist: [],
      topOffice: {}
    }
  },
  computed: {
    innerType: {
      get: function () {
        return this.type
      },
      set: function (newValue) {
        this.innerValue = []
        this.$emit('type-change', newValue)
      }
    },
    innerValue: {
      get: function () {
        return this.value
      },
      set: function (newValue) {
        this.$emit('value-change', newValue)
      }
    },
    roleList: function () {
      return this.$store.state.system.roleList
    }
  },
  mounted () {
    this.loadOffice()
  },
  methods: {
    loadOffice () {
      this.$api.system.getOfficeTree().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topOffice = result.data
        }
      })
    }
  }
}
</script>
