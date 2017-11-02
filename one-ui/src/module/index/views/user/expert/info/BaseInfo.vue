<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <section>
          <h4 class="page-header">基本信息</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <FormGroupStatic label="姓名">{{ obj.userInfo.name }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="性别">
                  <template v-if="obj.userInfo.gender == 1">男</template>
                  <template v-if="obj.userInfo.gender == 2">女</template>
                </FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="出生年月">{{ obj.userInfo.birthday }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="身份证号码">{{ obj.userInfo.identityNumber }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="登录账号">{{ obj.userInfo.account }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="最后登录时间">{{ obj.userInfo.lastLoginTime }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="注册IP">{{ obj.userInfo.createIp }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="注册时间">{{ obj.userInfo.createTime }}</FormGroupStatic>
              </div>
            </div>
          </form>
        </section>

        <section>
          <h4 class="page-header">联系方式</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <FormGroupStatic label="手机号码">{{ obj.userInfo.userContactInfo.mobile }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="座机号码">{{ obj.userInfo.userContactInfo.phone }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="电子邮箱">{{ obj.userInfo.userContactInfo.email }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="用户地址">{{ obj.userInfo.userContactInfo.address }}</FormGroupStatic>
              </div>
            </div>
          </form>
        </section>

        <section>
          <h4 class="page-header">学历</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <FormGroupStatic label="最高学历">{{ obj.educationInfo.lastEducation }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="最后毕业院校">{{ obj.educationInfo.lastSchool }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="政治面貌">{{ obj.educationInfo.politicalStatus }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="专业">{{ obj.educationInfo.studyProfession }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="毕业时间">{{ obj.educationInfo.graduationTime }}</FormGroupStatic>
              </div>
            </div>
          </form>
        </section>

        <section>
          <h4 class="page-header">工作经历</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-6">
                <FormGroupStatic label="是否内部专家">
                  <template v-if="obj.isOrgUser == 0">不是</template>
                  <template v-if="obj.isOrgUser == 1">是</template>
                </FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="所在单位名称" v-if="obj.isOrgUser == 0">{{ obj.orgName }}</FormGroupStatic>
                <FormGroupStatic label="所属机构" v-if="obj.isOrgUser == 1">{{ obj.sysOffice == null ? '' : obj.sysOffice.name }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="职务">{{ obj.position }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="简历">
                  <FileDownloader :fileId="obj.resumeFileId" :fileName="obj.userInfo.name + '简历'">下载</FileDownloader>
                </FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="职业资格等级">{{ obj.professionLevel }}</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="技术职称">--</FormGroupStatic>
              </div>
              <div class="col-md-6">
                <FormGroupStatic label="评定日期">{{ obj.professionDate }}</FormGroupStatic>
              </div>
            </div>
          </form>
        </section>

        <section>
          <h4 class="page-header">资质文件</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>资质类型</th>
                    <th>颁发机构</th>
                    <th>颁发日期</th>
                    <th>有效日期</th>
                    <th>影印件</th>
                    <th>备注</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(q, index) of obj.qualificationList">
                    <td>{{ index + 1}}</td>
                    <td>{{ q.type.qualificationName }}</td>
                    <td>{{ q.issueAgency }}</td>
                    <td>{{ q.issueDate }}</td>
                    <td>{{ q.invalidDate }}</td>
                    <td>
                      <ImageView v-if="q.fileId" :src="'/api/sys/file/' + q.fileId + '/download'"></ImageView>
                    </td>
                    <td>
                      <LongText :text="q.remark"></LongText>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </form>
        </section>

        <section>
          <h4 class="page-header">可评标类别</h4>
          <form class="form-horizontal">
            <div class="row">
              <div class="col-md-12">
                <table class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>投标类别</th>
                    <th>备注</th>
                    <th>审核人</th>
                    <th>审核状态</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(purchaseType, index) of obj.purchaseTypeList">
                    <td>{{ index + 1}}</td>
                    <td>{{ purchaseType.basePurchaseType.name }}</td>
                    <td>
                      <LongText :text="purchaseType.remark"></LongText>
                    </td>
                    <td>{{ purchaseType.userInfoEO == null ? '' : purchaseType.userInfoEO.name }}</td>
                    <td>
                      <template v-if="purchaseType.status != 3">
                        <span v-if="purchaseType.status == 1" class="label label-primary">{{ purchaseType.statusCN }}</span>
                        <span v-if="purchaseType.status == 2" class="label label-success">{{ purchaseType.statusCN }}</span>
                      </template>
                      <template v-if="purchaseType.status == 3">
                        <PopoverContainer title="驳回原因">
                          <span slot="outer" class="label label-warning">{{ purchaseType.statusCN }}</span>
                          <div class="row">
                            <div class="col-md-12">
                              <p class="text-red">{{ purchaseType.auditRemark }}</p>
                            </div>
                          </div>
                        </PopoverContainer>
                      </template>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </form>
        </section>
      </div>
    </div>
  </section>
</template>

<script>
  export default {
    components: {},
    props: {
      obj: {
        type: Object,
        required: true
      }
    },
    data: () => {
      return {}
    }
  }
</script>
