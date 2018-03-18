<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">物资库</h3>
      </div>
      <div class="box-body">
        <!--Query-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <FormGroup label="物料名称">
                <input type="text" class="form-control" placeholder="物料名称" v-model="param.name"/>
              </FormGroup>
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <button type="button" class="btn btn-primary" @click="addData()">
                  添加
                </button>
              </div>
            </div>
          </div>
        </form>
        <!--Query-->
  
        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>编码</th>
                  <th>名称</th>
                  <th>采购类别</th>
                  <th>计量单位</th>
                  <th>标杆价格</th>
                  <th>规格型号</th>
                  <th>备注</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(obj, index) of page.list">
                  <td>{{ index + 1 }}</td>
                  <td>{{ obj.id }}</td>
                  <td>{{ obj.name }}</td>
                  <td>{{ obj.name }}</td>
                  <td>{{ obj.unit }}</td>
                  <td>{{ obj.price }}</td>
                  <td>{{ obj.model }}</td>
                  <td>
                    <LongText :text="obj.remark"></LongText>
                  </td>
                  <td>
                    <a @click="editData(obj)">编辑</a>
                    <a @click="deleteData(obj)">删除</a>
                  </td>
                </tr>
              </tbody>
            </table>
  
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>
  
    <MaterielFormModal :config="materielConfig" :materiel="materiel" :purchaseType="purchaseType" @ok="query()" v-if="materielConfig.show"></MaterielFormModal>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import MaterielListMixins from '../../materiel/MaterielListMixins.js'
import MaterielFormModal from './MaterielFormModal.vue'

export default {
  mixins: [PageMixin, MaterielListMixins],
  components: {
    MaterielFormModal
  },
  created () {
    this.param.purchaseTypeId = this.$route.params.id
  },
  mounted () {
    this.loadPurchaseType()
  },
  methods: {
    loadPurchaseType () {
      let id = this.param.purchaseTypeId
      if (id) {
        this.$api.system.getPurchaseType(id).then((response) => {
          let result = response.data
          if (result.ok && result.data) {
            this.purchaseType = result.data
          }
        })
      }
    }
  }
}
</script>
