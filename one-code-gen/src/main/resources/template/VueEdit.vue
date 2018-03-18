<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-header">
        <h3 class="box-title">@{table.tableName}信息</h3>
      </div>
      <div class="box-body">
        <form class="form-horizontal">
          <%
            for (field in table.fieldList) {
              if (field.isEdit == 1) {
                print(field.editFormHtml + "\n");
              }
            }
          %>
        </form>
      </div>
      <div class="box-footer">
        <div class="row">
          <div class="col-md-2 col-md-offset-2">
            <a class="btn btn-block btn-primary" @click="validAndSave()">保存</a>
          </div>
          <div class="col-md-2">
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
            get: this.$api.@{strategy.moduleSimpleName}.get@{table.ClassNameNoEO},
            save: this.$api.@{strategy.moduleSimpleName}.save@{table.ClassNameNoEO},
            update: this.$api.@{strategy.moduleSimpleName}.update@{table.ClassNameNoEO}
          }
        },

        obj: {
        <%
            var index = 0;
            for (field in table.fieldList) {
                print("          " + field.javaFieldName + ": " + field.defaultValue);
                if (index < fieldListLength - 1) {
                    print(",");
                }
                print("\n");
                index++;
            }
        %>
        }
      }
    },
    mounted () {
    },
    methods: {
    }
  }
</script>
