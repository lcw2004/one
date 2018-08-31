<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row margin-bottom-10">
            <div class="col-md-4">
              <FormGroup label="公告标题">
                <input class="form-control" type="text" placeholder="公告标题" v-model="param.likeName">
              </FormGroup>
            </div>
          </div>
        </form>
        <!--Query End-->

        <div class="row">
          <div class="col-md-12">
          	<div class="scroll-div">
	            <table class="table table-bordered table-hover">
	              <thead>
	                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>阅读次数</th>
                    <th>发布人</th>
                    <th>发布时间</th>
                    <th>发布状态</th>
                    <th>是否置顶</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr v-for="(obj, index) of page.list">
	                  <td>{{ index + 1 }}</td>
                    <td><router-link :to='"/msg/announce/" + obj.announceId + "/view"'>{{ obj.title | limitLength }}</router-link></td>
                    <td>{{ obj.readCount }}</td>
                    <td>{{ obj.createUser.name }}</td>
                    <td>{{ obj.createTime }}</td>
                    <td>{{ obj.statusLabel }}</td>
                    <td>
                      <span class="label label-primary" v-if="obj.isTop == 1">已置顶</span>
                      <span class="label label-default" v-if="obj.isTop == 0">未置顶</span>
                    </td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
            <Pagination :page="page" @page="handlerPage(arguments)"></Pagination>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import PageMixin from '@mixins/PageMixin'

export default {
  mixins: [PageMixin],
  data: function () {
    return {
      actions: {
        list: {method: 'get', url: '/api/msg/announce/forRead'}
      },
      param: {
        likeName: ''
      },
      isSelectAll: false,
      announceIdList: []
    }
  }
}
</script>
