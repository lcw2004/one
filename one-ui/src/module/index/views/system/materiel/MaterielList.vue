<template>
  <section class="content">
    <div class="box box-primary">
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
              <FormGroup label="采购类别">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="purchaseType.name"/>
                  <span class="input-group-btn">
                    <button class="btn btn-info" type="button" @click="purchaseTreeModalConfig.show = true">选择</button>
                  </span>
                </div>
              </FormGroup>
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
                  <th>采购策略</th>
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
                  <td>{{ obj.purchaseType.name }}</td>
                  <td>
                    <DictLabel type="base_materiel_type" :value="obj.type"></DictLabel>
                  </td>
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
  
    <MaterielFormModal :config="materielConfig" :materiel="materiel" @ok="query()" v-if="materielConfig.show"></MaterielFormModal>
    <SelectPurchaseTypeModal :config="purchaseTreeModalConfig" v-model="purchaseType" v-if="purchaseTreeModalConfig.show"/>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'
import MaterielListMixins from '../materiel/MaterielListMixins.js'
import MaterielFormModal from '../purchase/materiel/MaterielFormModal.vue'
import SelectPurchaseTypeModal from '../modal/SelectPurchaseTypeModal.vue'

export default {
  mixins: [PageMixin, MaterielListMixins],
  components: {
    MaterielFormModal,
    SelectPurchaseTypeModal
  },
  data: function () {
    return {
      purchaseTreeModalConfig: {
        show: false,
        title: '选择采购类别'
      }
    }
  },
  watch: {
    'purchaseType': {
      handler: function () {
        if (this.purchaseType.id) {
          this.param.purchaseTypeId = this.purchaseType.id
        } else {
          this.param.purchaseTypeId = ''
        }
      },
      deep: true
    }
  }
}
</script>
