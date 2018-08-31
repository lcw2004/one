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
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/msg/announce/add" class="btn btn-primary">新增公告</router-link>
              </div>
            </div>
          </div>
        </form>
        <!--Query End-->

        <!-- Operation -->
        <div class="row margin-bottom-10" v-if="announceIdList.length > 0">
          <div class="col-md-12">
            <div class="pull-right">
              <button type="button" class="btn btn-primary" @click="deleteMsgAnnounceBatch()">删除</button>
              <button type="button" class="btn btn-primary" @click="startMsgAnnounceBatch()">启用</button>
              <button type="button" class="btn btn-primary" @click="closeMsgAnnounceBatch()">关闭</button>
            </div>
          </div>
        </div>
        <!-- Operation -->

        <div class="row">
          <div class="col-md-12">
          	<div class="scroll-div">
	            <table class="table table-bordered table-hover">
	              <thead>
	                <tr>
                    <th><input type="checkbox" v-model="isSelectAll"/></th>
                    <th>标题</th>
                    <th>阅读次数</th>
                    <th>发布人</th>
                    <th>发布时间</th>
                    <th>发布状态</th>
                    <th>是否置顶</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr v-for="(obj, index) of page.list">
	                  <td><input type="checkbox" :value="obj.announceId" v-model="announceIdList"></td>
                    <td><router-link :to='"/msg/announce/" + obj.announceId + "/view"'>{{ obj.title | limitLength }}</router-link></td>
                    <td>{{ obj.readCount }}</td>
                    <td>{{ obj.createUser.name }}</td>
                    <td>{{ obj.createTime }}</td>
                    <td>{{ obj.statusLabel }}</td>
                    <td>
                      <span class="label label-primary" v-if="obj.isTop == 1">已置顶</span>
                      <span class="label label-default" v-if="obj.isTop == 0">未置顶</span>
                    </td>
	                  <td>
	                    <router-link :to='"/msg/announce/" + obj.announceId + "/edit"'>修改</router-link>
	                    <a @click="deleteData(obj)">删除</a>
	                    <a v-if="obj.status != 1" @click="startMsgAnnounce(obj)">启用</a>
	                    <a v-if="obj.status != 2" @click="closeMsgAnnounce(obj)">关闭</a>
	                    <a v-if="obj.isTop == 0" @click="topMsgAnnounce(obj)">置顶</a>
	                    <a v-if="obj.isTop == 1" @click="unTopMsgAnnounce(obj)">取消置顶</a>
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
        list: {method: 'get', url: '/api/msg/announce'}
      },
      param: {
        likeName: ''
      },
      isSelectAll: false,
      announceIdList: []
    }
  },
  methods: {
    deleteData (obj) {
      this.$confirm('确认删除公告吗？', () => {
        this.$api.system.deleteMsgAnnounce(obj.announceId).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('删除成功！')
            this.query()
          }
        })
      })
    },
    startMsgAnnounce (obj) {
      this.$api.system.startMsgAnnounce(obj.announceId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('启用成功！')
          this.query()
        }
      })
    },
    closeMsgAnnounce (obj) {
      this.$api.system.closeMsgAnnounce(obj.announceId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('关闭成功！')
          this.query()
        }
      })
    },
    topMsgAnnounce (obj) {
      this.$api.system.topMsgAnnounce(obj.announceId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('置顶成功！')
          this.query()
        }
      })
    },
    unTopMsgAnnounce (obj) {
      this.$api.system.unTopMsgAnnounce(obj.announceId).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('取消置顶成功！')
          this.query()
        }
      })
    },
    deleteMsgAnnounceBatch () {
      this.$confirm('确认删除公告吗？', () => {
        this.$api.system.deleteMsgAnnounceBatch(this.announceIdList).then((response) => {
          let result = response.data
          if (result.ok) {
            this.$notify.success('删除成功！')
            this.query()
            this.clear()
          }
        })
      })
    },
    startMsgAnnounceBatch () {
      this.$api.system.startMsgAnnounceBatch(this.announceIdList).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('启用成功！')
          this.query()
          this.clear()
        }
      })
    },
    closeMsgAnnounceBatch () {
      this.$api.system.closeMsgAnnounceBatch(this.announceIdList).then((response) => {
        let result = response.data
        if (result.ok) {
          this.$notify.success('关闭成功！')
          this.query()
          this.clear()
        }
      })
    },
    clear () {
      this.announceIdList = []
      this.isSelectAll = false
    }
  },
  watch: {
    'isSelectAll': function () {
      if (this.isSelectAll) {
        let selectIdList = []
        for (let obj of this.page.list) {
          selectIdList.push(obj.announceId)
        }
        this.announceIdList = selectIdList
      } else {
        this.announceIdList = []
      }
    }
  }
}
</script>
