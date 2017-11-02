<template>
<section class="content">
  <div class="box">
    <div class="box-header">
      <h3 class="box-title">文章信息</h3>
    </div>
    <div class="box-body">
      <form class="form-horizontal">
        <div class="row">
          <div class="col-md-6">
            <FormGroup label="文章标题">
              <input type="text" class="form-control" v-model="obj.articleTitle" v-validate="'required'" name="文章标题" />
            </FormGroup>
          </div>
          <div class="col-md-5">
            <FormGroup label="所属栏目">
              <div class="input-group">
                <input type="text" class="form-control" v-model="obj.group == null ? '' : obj.group.name"/>
                <span class="input-group-btn">
                  <button class="btn btn-info" type="button" @click="groupTreeModalConfig.show = true">选择</button>
                </span>
              </div>
              <SelectGroupModal :config="groupTreeModalConfig" v-model="obj.group"></SelectGroupModal>
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <FormGroup label="文章来源">
              <input type="text" class="form-control" v-model="obj.articleSource" />
            </FormGroup>
          </div>
          <div class="col-md-5">
            <FormGroup label="文章作者">
              <input type="text" class="form-control" v-model="obj.articleAuthor" />
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <FormGroup label="文章SEO关键字">
              <input type="text" class="form-control" v-model="obj.keyword"/>
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <FormGroup label="文章描述" width="2" inputWidth="9">
              <textarea class="form-control" rows="3" maxlength="200" v-model="obj.remark"></textarea>
            </FormGroup>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <FormGroup label="文章内容" width="2" inputWidth="9">
              <InlineEditor v-model="obj.content"></InlineEditor>
            </FormGroup>
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
</section>
</template>

<script>
import FormMixin from 'mixins/FormMixin.js'
import SelectGroupModal from '../common/SelectGroupModal.vue'
export default {
  mixins: [FormMixin],
  components: {
    SelectGroupModal
  },
  data: () => {
    return {
      actions: {
        get: {method: 'get', url: '/api/cms/article{/id}'},
        save: {method: 'post', url: '/api/cms/article'},
        update: {method: 'put', url: '/api/cms/article'}
      },
      obj: {
        articleId: '',
        articleTitle: '',
        orderIndex: 1,
        imageFileId: null,
        articleSource: '',
        articleAuthor: '',
        remark: '',
        keyword: '',
        content: '',
        userId: '',
        group: {}
      },
      groupTreeModalConfig: {
        show: false,
        title: '选择所属栏目'
      }
    }
  },
  methods: {
  }
}
</script>
