<template>
  <div class="tab-content">
    <div class="tab-pane active">
      <div class="row">
        <div class="col-md-12">
          <form class="form-inline">
            <div class="col-md-6">
            </div>
            <div class="col-md-6">
              <button type="button" class="btn btn-info btn-sm pull-right" @click="configOfAdd.show = true">
                <i class="fa fa-plus"></i>  发布澄清
              </button>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-bordered table-hover">
            <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>标题</th>
                <th>发布人</th>
                <th>发布时间</th>
                <th>附件</th>
                <th>阅读次数</th>
              </tr>
              <tr>
                <td>1</td>
                <td><a><router-link to="/bidding/clarify/clarify-info">关于某某项目的澄清</router-link></a></td>
                <td>李四</td>
                <td>2017-01-16 00:00:00</td>
                <td>
                  <a><i class="fa fa-paperclip"></i> 附件一（点击下载）</a>
                </td>
                <td>5</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <ClarifyAddModal :config="configOfAdd"></ClarifyAddModal>
  </div>
</template>

<script>
import ClarifyAddModal from './ClarifyAddModal'

export default {
  components: {
    ClarifyAddModal
  },
  data: function () {
    return {
      configOfAdd: {
        show: false,
        title: '添加澄清'
      }
    }
  },
  methods: {
  }
}
</script>
