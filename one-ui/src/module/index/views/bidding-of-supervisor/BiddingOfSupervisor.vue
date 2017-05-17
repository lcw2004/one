<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <form class="form-inline">
              <div class="col-md-3"><label class="control-label">项目</label> <input type="text" placeholder="项目编号/项目名称" class="form-control inline-block"></div>
            </form>
          </div>

          <div class="box-body">
            <table class="table table-bordered table-hover">
              <tbody>
                <tr>
                  <th style="width: 10px">#</th>
                  <th>项目编号</th>
                  <th>项目名称</th>
                  <th>项目类型</th>
                  <th>发布日期</th>
                  <th>开标时间</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>

                <tr>
                  <td>4</td>
                  <td><a>ZB201701160009</a></td>
                  <td>测试4</td>
                  <td>邀请招标</td>
                  <td>2017-01-16</td>
                  <td>2017-03-16 12:00:00</td>
                  <td>开标中</td>
                  <td>
                    <a><router-link to='/bidding/scene'>开标现场</router-link></a>
                    <a @click="config.show = true">选择评标组长</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="box box-solid">
          <div class="box-header with-border">
            <i class="fa fa-text-width"></i>
            <h3 class="box-title">备注</h3>
          </div>
          <div class="box-body">
            <ol>
              <li></li>
            </ol>
          </div>
        </div>
      </div>
    </div>

    <SelectExpertLeader :config="config"></SelectExpertLeader>
  </section>
</template>

<script>
import SelectExpertLeader from './SelectExpertLeader'

export default {
  components: {
    SelectExpertLeader
  },
  data: function () {
    return {
      config: {
        show: false,
        title: '选择评标组长'
      }
    }
  },
  methods: {
    test: function () {
      this.$alert('123123')
    }
  }
}
</script>
