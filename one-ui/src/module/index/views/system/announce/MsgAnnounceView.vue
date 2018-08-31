<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">查看公告</h3>
      </div>

      <div class="box-body">
        <div class="crm-article">
          <div class="row">
            <div class="col-md-12 text-center">
              <div class="crm-article-title">
                {{ obj.title }}
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 text-center">
              <div class="crm-article-second-title">
                <span>创建人：{{ obj.createUser.name }}</span>
                <span>创建时间：{{ obj.createTime }}</span>
                <span>阅读次数：{{ obj.readCount }}</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="crm-article-content" v-html="obj.content"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-default" @click="$router.go(-1)">返回</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import FormMixin from '@mixins/FormMixin'

export default {
  mixins: [FormMixin],
  data: function () {
    return {
      form: {
        actions: {
          get: this.$api.system.getMsgAnnounce,
          save: this.$api.system.saveMsgAnnounce,
          update: this.$api.system.updateMsgAnnounce
        }
      },
      obj: {
        announceId: '',
        title: '',
        createUserId: '',
        createTime: '',
        readCount: 0,
        status: 0,
        isTop: 0
      }
    }
  },
  mounted () {
    this.readMsgAnnounce()
  },
  methods: {
    readMsgAnnounce () {
      this.$api.system.readMsgAnnounce(this.id)
    }
  }
}
</script>

<style lang="less" type="text/less">
.one-announce {
  .one-announce-title {
    font-size: 22px;
    margin: 20px;
  }
  .one-announce-content {
    margin: 0 100px 20px 100px;
  }
}
</style>
