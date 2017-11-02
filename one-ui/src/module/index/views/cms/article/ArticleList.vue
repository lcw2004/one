<template>
  <section class="content">
    <div class="box">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <div class="col-md-4">
              <FormGroup label="文章标题">
                <input class="form-control" type="text" placeholder="文章标题" v-model="param.articleTitle">
              </FormGroup>
            </div>
            <div class="col-md-4">
              <FormGroup label="所属栏目">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="group == null ? '' : group.name"/>
                  <span class="input-group-btn">
                  <button class="btn btn-info" type="button" @click="groupTreeModalConfig.show = true">选择</button>
                </span>
                </div>
                <SelectGroupModal :config="groupTreeModalConfig" v-model="group"></SelectGroupModal>
              </FormGroup>
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/cms/article/add" class="btn btn-primary">添加</router-link>
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
                <th style="width: 10px">#</th>
                <th>文章标题</th>
                <th>创建时间</th>
                <th>栏目名称</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(obj, index) of page.list">
                <td>{{ index + 1 }}</td>
                <td>{{ obj.articleTitle }}</td>
                <td>{{ obj.createTime }}</td>
                <td>{{ obj.group.name }}</td>
                <td>
                  <router-link :to='"/cms/article/" + obj.articleId + "/form"'>修改</router-link>
                  <a @click="deleteData(obj.articleId)">删除</a>
                </td>
              </tr>
              </tbody>
            </table>

            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import SelectGroupModal from '../common/SelectGroupModal.vue'
  import PageMixin from 'mixins/PageMixin.js'

  export default {
    mixins: [PageMixin],
    components: {
      SelectGroupModal
    },
    data: () => {
      return {
        actions: {
          list: {method: 'get', url: '/api/cms/article'},
          deleteData: {method: 'delete', url: '/api/cms/article{/id}'}
        },
        param: {
          articleTitle: '',
          groupId: ''
        },
        group: {
          id: ''
        },
        groupTreeModalConfig: {
          show: false,
          title: '选择所属栏目'
        }
      }
    },
    methods: {
      deleteData (id) {
        this.$confirm('确认删除吗？', () => {
          this.resource.deleteData({id: id}).then((response) => {
            let result = response.body
            if (result.ok) {
              this.query()
              this.$notify.success('删除成功！')
            }
          })
        })
      }
    },
    watch: {
      'param': {
        handler: function () {
          this.query()
        },
        deep: true
      },
      'group': {
        handler: function () {
          if (this.group.id) {
            this.param.groupId = this.group.id
          } else {
            this.param.groupId = ''
          }
        },
        deep: true
      }
    }
  }
</script>
