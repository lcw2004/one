<template>
  <section class="content">
    <div class="row">
      <div class="col-md-12">
        <div class="box">
          <div class="box-header">
            <h3 class="box-title">澄清与回应</h3>
          </div>
          <div class="box-body">
            <div class="row">
              <div class="col-md-12">
                <div class="pull-right">
                  <button type="button" class="btn btn-default"  @click="$router.go(-1)">
                    返回
                  </button>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <ClarifyDetail index="3" status="0"></ClarifyDetail>
                <ClarifyDetail index="2" status="1"></ClarifyDetail>
                <ClarifyDetail index="1" status="1"></ClarifyDetail>
              </div>
            </div>
          </div>
          <div class="box-footer">

          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import ClarifyDetail from './ClarifyDetail'

export default {
  components: {
    ClarifyDetail
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>
