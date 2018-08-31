<template>
  <OneModal :title="config.title" :show.sync="config.show" width="350">
    <div class="row">
      <div class="col-md-12">
        <Tree :element="topElement" v-model="selectValue" :select-type="selectType"></Tree>
      </div>
    </div>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default" @click="close()">取消</button>
      <button type="button" class="btn btn-primary" @click="ok()">确认</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalMixin'

export default {
  mixins: [ModalMixin],
  props: {
    selectType: {
      type: String,
      default: 'radio'
    },
    value: {}
  },
  data: function () {
    return {
      topElement: {},
      selectValue: {}
    }
  },
  mounted () {
    this.loadTree()
    this.selectValue = this.value
  },
  methods: {
    loadTree () {
      this.action().then((response) => {
        let result = response.data
        if (result.ok) {
          this.topElement = result.data
        }
      })
    },
    ok () {
      let value = null
      if (this.selectType === 'radio') {
        value = JSON.parse(JSON.stringify(this.selectValue))
        value.childList = null
      } else if (this.selectType === 'checkbox') {
        value = this.selectElement
      }
      this.$emit('input', value)
      this.close()
    }
  },
  computed: {
    elementMap: function () {
      const getElementRecursion = function (element) {
        let elementCopy = JSON.parse(JSON.stringify(element))
        elementCopy.childList = []
        elementMap[element.id] = elementCopy
        const childList = element.childList
        if (childList) {
          for (let i = 0; i < childList.length; i++) {
            getElementRecursion(childList[i])
          }
        }
      }
      let elementMap = {}
      getElementRecursion(this.topElement)
      return elementMap
    },
    selectElement: function () {
      let selectElement = []
      for (let id of this.selectValue) {
        selectElement.push(this.elementMap[id])
      }
      return selectElement
    }
  }
}
</script>
