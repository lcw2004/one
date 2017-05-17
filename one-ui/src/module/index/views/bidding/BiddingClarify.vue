<template>
  <section class="content">
    <div class="row">
      <div class="col-xs-12">
        <div class="nav-tabs-custom">
          <ul class="nav nav-tabs">
            <li :class="{active : activeNav == 1}">
              <a data-toggle="tab" aria-expanded="false" @click="activeNav = 1">
                项目澄清
              </a>
            </li>
            <li :class="{active : activeNav == 2}" class="">
              <a data-toggle="tab" aria-expanded="true" @click="activeNav = 2">
                供应商提问 <span title="" class="badge bg-yellow" data-original-title="3 New Messages">1</span>
              </a>
            </li>
          </ul>

          <ClarifyList v-if="activeNav == 1"></ClarifyList>
          <Question v-if="activeNav == 2"></Question>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import ClarifyInfo from './clarify/ClarifyInfo'
import ClarifyList from './clarify/ClarifyList'
import Question from './clarify/Question'

export default {
  components: {
    ClarifyInfo,
    ClarifyList,
    Question
  },
  data () {
    return {
      activeNav: 1
    }
  }
}
</script>
