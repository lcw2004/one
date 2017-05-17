<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header with-border">
            <h3 class="box-title">某某招标项目（ZB201701150003）</h3>
            <div class="box-tools pull-right">
              <FullScreenBtn :id="id"></FullScreenBtn>
            </div>
          </div>

          <div class="box-body">
            <div class="row">
              <div class="col-md-8">
                <Home></Home>
              </div>
              <div class="col-md-4">
                <div class="row">
                  <div class="col-md-12">
                    <ProjectInfo></ProjectInfo>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <Chat></Chat>
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
import Home from './Home'
import Chat from '../../bidding/scence/chat/Chat'
import ProjectInfo from '../../bidding/scence/ProjectInfo'

export default {
  components: {
    Home,
    Chat,
    ProjectInfo
  },
  data: function () {
    return {
    }
  }
}
</script>
