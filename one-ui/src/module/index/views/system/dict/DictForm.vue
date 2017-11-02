<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">字典信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="字典名称">
              <input type="text" class="form-control" v-model="obj.type" v-validate="'required'" name="字典名称" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="字典描述">
              <input type="text" class="form-control" v-model="obj.description" v-validate="'required'" name="字典描述" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="字典值描述">
              <input type="text" class="form-control" v-model="obj.label" v-validate="'required'" name="字典值描述" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="字典值">
              <input type="text" class="form-control" v-model="obj.value" v-validate="'required'" name="字典值" />
            </FormGroup>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="排序号">
              <input type="number" class="form-control" v-model="obj.sort" v-validate="'required'" name="排序号" />
            </FormGroup>
          </div>
        </div>
      </form>
    </div>
    <div class="box-footer">
      <div class="row">
        <div class="col-md-2 col-md-offset-2">
          <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
        </div>
        <div class="col-md-2">
          <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<script>
  import FormMixin from 'mixins/FormMixin.js'
  export default {
    mixins: [FormMixin],
    data: () => {
      return {
        actions: {
          get: {method: 'get', url: '/api/sys/dict{/id}'},
          save: {method: 'post', url: '/api/sys/dict'},
          update: {method: 'put', url: '/api/sys/dict'}
        },

        obj: {
          type: '',
          description: '',
          label: '',
          value: '',
          sort: 1
        }
      }
    },
    methods: {
      clear () {
        this.obj.label = ''
        this.obj.value = ''
        this.obj.sort = Number.parseInt(this.obj.sort) + 1
      }
    }
  }
</script>
