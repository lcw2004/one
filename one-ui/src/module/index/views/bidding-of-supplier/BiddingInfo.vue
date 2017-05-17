<template>
<section class="content">
  <div class="row">
    <div class="col-md-5">
      <TimeLine></TimeLine>
      <TimeLine1></TimeLine1>
    </div>

    <div class="col-md-7">
      <ProjectInfo></ProjectInfo>

      <div class="row">
        <div class="col-md-12">
          <div class="row">
            <div class="col-md-12">
              <div class="box box-solid">
                <div class="box-header with-border">
                  <i class="fa fa-text-width"></i>
                  <h3 class="box-title">备注</h3>
                </div>
                <div class="box-body">
                  <ol>
                    <li>如果未缴纳标书费
                      <ol>
                        <li>显式缴纳标书费的账号以及联系方式</li>
                        <li>"下载标书按钮"以及后面的操作按钮都置灰色</li>
                        <li>如果已经缴纳标书费，显式缴纳标书费的时间，并隐藏账号</li>
                        <li>用户缴纳标书费之后，"下载标书按钮"置为可点击</li>
                      </ol>
                    </li>

                    <li>如果未缴纳投标保证金
                      <ol>
                        <li>显式缴纳投标保证金的账号以及联系方式</li>
                        <li>上传投标文件以及后面的操作按钮都置灰色</li>
                        <li>如果已经缴纳标书费，显式缴纳投标保证金的时间，并隐藏账号</li>
                        <li>超过开标时间之后，上传投标文件按钮置灰</li>
                      </ol>
                    </li>

                    <li>到开标时间，项目负责人签到之后，进入开标现场按钮置为可点击</li>

                    <li>图标颜色
                      <ol>
                        <li>超过当前时间不能操作或者已完成，背景色为灰色</li>
                        <li>当前时间后面的操作，背景色为蓝色</li>
                        <li>时间背景色为红色，突出显示</li>
                      </ol>
                    </li>
                  </ol>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


</section>
</template>

<script>
import ProjectInfo from './ProjectInfo'
import TimeLine from './TimeLine'
import TimeLine1 from './TimeLine1'
import TimeLine2 from './TimeLine2'

export default {
  components: {
    ProjectInfo,
    TimeLine,
    TimeLine1,
    TimeLine2
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
