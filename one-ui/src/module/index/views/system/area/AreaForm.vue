<template>
  <section class="content">
    <div class="box">
      <div class="box-header">
        <h3 class="box-title">菜单信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-2 control-label">上级区域</label>
            <div class="col-sm-4">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.parent.name"/>
                <span class="input-group-btn">
									<button class="btn btn-info" type="button" @click="areaTreeModalConfig.show = true">选择</button>
								</span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">区域名称</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.name"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">区域编码</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" v-model="obj.code"/>
            </div>
          </div>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="save()">保存</a>
          </div>
          <div class="col-md-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>

    <SelectAreaModal :config="areaTreeModalConfig" v-model="obj.parent"/>
  </section>
</template>

<script>
  import FormMixin from '../../../../../common/mixins/FormMixin.js'
  import SelectAreaModal from '../modal/SelectAreaModal'

  export default {
    mixins: [FormMixin],
    components: {
      SelectAreaModal
    },
    data: () => {
      return {
        actions: {
          get: {method: 'get', url: '/api/sys/area{/id}'},
          save: {method: 'post', url: '/api/sys/area'},
          update: {method: 'put', url: '/api/sys/area'}
        },

        obj: {
          parent: {},
          area: {},
          name: '',
          code: '',
          type: 1,
          grade: 1,
          address: '',
          zipCode: '',
          master: '',
          phone: '',
          fax: '',
          email: '',
          remarks: ''
        },

        officeTreeModalConfig: {
          show: false,
          title: '选择上级机构'
        },
        // 模态窗属性
        areaTreeModalConfig: {
          show: false,
          title: '选择归属区域'
        }
      }
    }
  }
</script>
