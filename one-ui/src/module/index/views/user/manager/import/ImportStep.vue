<template>
  <form class="form-horizontal">
    <div class="row">
      <div class="col-md-12">
        <div class="import-notice">
          <div class="header">
            一、请按照数据模板的格式准备要导入的数据：
          </div>
          <div class="body">
            <a @click="downloadTemplate()">下载数据模板</a><br />
            注意事项：<br />
            1、模板中的表头名称不可更改，表头行不能删除<br />
            2、项目顺序可以调整，不需要的项目可以删减<br />
            3、其中用户姓名、用户电话为必填项，必须填入<br />
            4、导入文件请勿超过1MB。<br />
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="import-notice">
          <div class="header">
            二、请选择数据重复时的操作方式：
          </div>
          <div class="body">
            <div class="row">
              <div class="col-md-6">
                <select class="form-control" v-model="coverType">
                  <option :value="1">覆盖导入重复数据</option>
                  <option :value="2">停止导入，检查重复数据</option>
                  <option :value="3">导入新数据，忽略重复数据</option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="import-notice">
          <div class="header">
            三、请选择导入用户的角色
          </div>
          <div class="body">
            <div class="row">
              <div class="col-md-11">
                <div class="checkbox-inline margin-left-10" style="min-width: 165px" v-for="role of roleList">
                  <label>
                    <input type="checkbox" :value="role.id" v-model="roleIdList" v-validate="'required'" name="角色"> {{ role.name }}
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="import-notice">
          <div class="header">
            四、请选择需要导入的Excel文件：
          </div>
          <div class="body">
            <div class="row">
              <div class="col-md-11">
                <FileUpload type="xlsx" :url="url" :callback="uploadSuccess" :errorCallback="uploadError" size="1M"></FileUpload>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import { stringify } from '@utils/common'

export default {
  props: {
    importUrl: {
      type: String,
      required: true
    },
    templateUrl: {
      type: String,
      required: true
    }
  },
  data: function () {
    return {
      coverType: 1,
      roleIdList: []
    }
  },
  computed: {
    url: function () {
      return this.importUrl + '?' + stringify({
        coverType: this.coverType,
        roleIdList: this.roleIdList
      })
    },
    roleList: function () {
      // 忽略系统内置角色
      const roleList = this.$store.state.system.roleList
      let roleListWithoutDefault = []
      for (let role of roleList) {
        if (role.id !== '20' && role.id !== '30') {
          roleListWithoutDefault.push(role)
        }
      }
      return roleListWithoutDefault
    }
  },
  mounted () {
    this.$store.dispatch('initRoleList')
  },
  methods: {
    downloadTemplate () {
      window.location.href = this.templateUrl
    },
    uploadSuccess (fileObj) {
      this.$emit('success', fileObj)
    },
    uploadError (error) {
      console.log(error)
    }
  }
}
</script>
