<template>
  <section class="content">
    <div class="row">
      <div class="col-xs-12">
        <div class="nav-tabs-custom">
          <ul class="nav nav-tabs">
            <li :class="{active : activeNav == 1}">
              <a data-toggle="tab" aria-expanded="false" @click="activeNav = 1">
                <router-link to='/system/notice' tag='span'>投标须知</router-link>
              </a>
            </li>
            <li :class="{active : activeNav == 2}" class="">
              <a data-toggle="tab" aria-expanded="true" @click="activeNav = 2">
                <router-link to='/system/notice' tag='span'>竞价须知</router-link>
              </a>
            </li>
            <li :class="{active : activeNav == 3}" class="">
              <a data-toggle="tab" aria-expanded="true" @click="activeNav = 3">
                <router-link to='/system/notice' tag='span'>供应商注册须知</router-link>
              </a>
            </li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active" id="glyphicons">
              <div class="row">
                <div class="col-md-12">
                  <form class="form-inline">
                    <div class="col-md-6">
                    </div>
                    <div class="col-md-6">
                      <button type="button" class="btn btn-info btn-sm pull-right">
                        <i class="fa fa-plus"></i>
                        <router-link to='/system/notice-tree-form' tag='span'>添加模板</router-link>
                      </button>
                    </div>
                  </form>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th style="width: 10px">#</th>
                        <th>模板名称</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>1</td>
                        <td><a>模板1</a><span class="label label-primary">默认</span></td>
                        <td>
                          <router-link to='/system/notice-tree-form'>编辑</router-link>
                          <a>删除</a>
                        </td>
                      </tr>
                      <tr>
                        <td>2</td>
                        <td><a>模板2</a></td>
                        <td>
                          <router-link to='/system/notice-tree-form'>编辑</router-link>
                          <a>删除</a>
                        </td>
                      </tr>
                      <tr>
                        <td>3</td>
                        <td><a>模板3</a></td>
                        <td>
                          <router-link to='/system/notice-tree-form'>编辑</router-link>
                          <a>删除</a>
                        </td>
                      </tr>
                    </tbody>
                  </table>
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
export default {
  components: {},
  data: function () {
    return {
      activeNav: 1
    }
  }
}
</script>
