<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">{{ dict.name }}（{{ dict.type }}）</h3>
      </div>
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-12">
              <div class="pull-right">
                <a class="btn btn-primary" @click="addData()">添加字典数据</a>
                <a class="btn btn-default" @click="$router.go(-1)">返回</a>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>字典数据名称</th>
                  <th>字典数据值</th>
                  <th>字典数据描述</th>
                  <th>排序号</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="obj of list">
                  <td>{{ obj.label }}</td>
                  <td>{{ obj.value }}</td>
                  <td>{{ obj.remark }}</td>
                  <td>{{ obj.sort }}</td>
                  <td>
                    <a @click="updateData(obj)">修改</a>
                    <a @click="deleteData(obj.dataId)">删除</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <DictDataModal v-if="config.show" :config="config" @ok="loadData" :dictId="dictId" :data="dictData"></DictDataModal>
  </section>
</template>

<script>
import DictDataModal from './DictDataModal'

export default {
  components: {
    DictDataModal
  },
  data: function () {
    return {
      list: [],
      config: {
        show: false,
        title: '添加字典数据'
      },
      dict: {},
      dictData: {}
    }
  },
  computed: {
    dictId: function () {
      return this.$route.params.id
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    loadData () {
      this.$api.system.listDictData(this.dictId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.list = result.data
        }
      })
      this.$api.system.getDict(this.dictId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.dict = result.data
        }
      })
    },
    addData () {
      this.dictData = {}
      this.config.title = '添加字典数据'
      this.config.show = true
    },
    updateData (dictData) {
      this.dictData = dictData
      this.config.title = '修改字典数据'
      this.config.show = true
    },
    deleteData (dataId) {
      this.$confirm('确认删除吗？', () => {
        this.$api.system.deleteDictData(this.dictId, dataId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.loadData()
            this.$notify.success('删除成功！')
          }
        })
      })
    }
  }
}
</script>
