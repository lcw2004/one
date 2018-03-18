<template>
  <OneModal :title="config.title" :show.sync="config.show">
    <form class="form-horizontal">
      <div class="row">
        <div class="col-md-12">
          <FormGroup label="评分人" width="2">
            <button type="button" class="btn btn-primary" @click="selectManageUserConfig.show = true">
              选择
            </button>
            <template v-for="user of userList">
              <span class="label label-info">{{ user.name }}</span>&nbsp;
            </template>
          </FormGroup>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <FormGroup label="评分模板" width="2">
            <div class="row">
              <div class="col-md-8">
                <BaseScoreTemplateSelect v-model="templateId" type="1"></BaseScoreTemplateSelect>
              </div>
              <div class="col-md-4" v-if="templateId">
                <button type="button" class="btn btn-primary" @click="previewConfig.show = true">预览评分模板</button>
              </div>
            </div>
          </FormGroup>
        </div>
      </div>
    </form>
    <BaseScoreTemplatePreviewByIdModal v-if="previewConfig.show" :config="previewConfig" :templateId="templateId"></BaseScoreTemplatePreviewByIdModal>
    <SelectManageUserModal :config="selectManageUserConfig" v-model="userList"></SelectManageUserModal>

    <div class="modal-footer" slot="footer">
      <button type="button" class="btn btn-default " data-dismiss="modal" @click="close()">取消</button>
      <button type="button" class="btn btn-primary" @click="ok()">确认</button>
    </div>
  </OneModal>
</template>

<script>
import ModalMixin from '@mixins/ModalSimpleMixin.js'
import SelectManageUserModal from '../../../user/modal/SelectManageUserModal.vue'
import BaseScoreTemplateSelect from '../../../system/scoreTemplate/BaseScoreTemplateSelect.vue'
import BaseScoreTemplatePreviewByIdModal from '../../../system/scoreTemplate/BaseScoreTemplatePreviewByIdModal.vue'

export default {
  mixins: [ModalMixin],
  components: {
    SelectManageUserModal,
    BaseScoreTemplateSelect,
    BaseScoreTemplatePreviewByIdModal
  },
  props: {
    projectId: {
      type: String,
      required: true
    },
    supplierId: {
      type: String,
      required: true
    }
  },
  computed: {
    userIdList: function () {
      let userIdList = []
      if (this.userList) {
        for (let user of this.userList) {
          userIdList.push(user.userId)
        }
      }
      return userIdList
    }
  },
  data: function () {
    return {
      userList: [],
      templateId: '',
      selectManageUserConfig: {
        show: false,
        title: '选择评分人'
      },
      previewConfig: {
        show: false,
        title: '评分模板预览'
      }
    }
  },
  mounted () {
    this.$validator.attach({ name: '评分人', rules: 'required' })
    this.$validator.attach({ name: '评分模板', rules: 'required' })
  },
  methods: {
    ok () {
      this.$validator.validateAll({'评分人': this.userIdList, '评分模板': this.templateId}).then((result) => {
        if (result) {
          this.$api.user.startScore(this.supplierId, this.projectId, this.userIdList, this.templateId).then((response) => {
            let result = response.data
            if (result.ok) {
              this.config.show = false
              this.$notify.success('操作成功')
              this.$emit('ok')
            }
          })
        }
      })
    }
  },
  watch: {
    'userIdList': function () {
      this.$validator.validate('评分人', this.userIdList)
    },
    'templateId': function () {
      this.$validator.validate('评分模板', this.templateId)
    }
  }
}
</script>
