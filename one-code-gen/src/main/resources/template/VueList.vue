<template>
  <section class="content">
    <div class="box box-primary">
      <div class="box-body">
        <!--Query Start-->
        <form class="form-horizontal">
          <div class="row row-margin-bottom">
            <%
              for (field in queryFieldList) {
                print(field.queryFormHtml);
              }
            %>
            <div class="col-md-4">
              <div class="pull-right">
                <router-link to="/@{strategy.moduleSimpleName}/@{table.restPath}/add" class="btn btn-primary">添加</router-link>
              </div>
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
	                  <th style="width: 10px">#</th>
	                  <%
	                    for (field in table.fieldList) {
	                      if (field.isList == 1) {
	                  	    print("                    <th>" + field.fieldName + "</th>\n");
	                      }
	                    }
	                  %>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr v-for="(obj, index) of page.list">
	                  <td>{{ index + 1 }}</td>
	                  <%
	                    for (field in table.fieldList) {
	                      if (field.isList == 1) {
	                        if (field.showType == "4" || field.showType == "6") {
	                          print("                    <td><DictLabel :value=\"obj." + field.javaFieldName + "\" type=\"" + field.dictType + "\"></DictLabel></td>\n");
	                        } else {
	                          print("                    <td>{{ obj." + field.javaFieldName + " }}</td>\n");
	                        }
	                      }
	                    }
	                  %>
	                  <td>
	                    <router-link :to='"/@{strategy.moduleSimpleName}/@{table.restPath}/" + obj.@{table.idField.javaFieldName} + "/edit"'>修改</router-link>
	                    <a @click="deleteData(obj)">删除</a>
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
          list: {method: 'get', url: '/@{restPath}/@{strategy.moduleSimpleName}/@{table.restPath}'}
        },
        param: {
          <%
            var index = 0;
            for (field in queryFieldList) {
              if (field.javaType == "Date") {
                print("          " + field.javaFieldName + "From: '',\n");
                print("          " + field.javaFieldName + "To: ''");
              } else {
                print("          " + field.javaFieldName + ": ''");
              }
              if (index < queryFieldListLength - 1) {
                print(",");
              }
              print("\n");
              index++;
            }
          %>
        }
      }
    },
    methods: {
      deleteData (obj) {
        this.$confirm('确认删除吗？', () => {
          this.$api.@{strategy.moduleSimpleName}.delete@{table.ClassNameNoEO}(obj.@{table.idField.javaFieldName}).then((response) => {
            let result = response.data
            if (result.ok) {
              this.query()
              this.$notify.success('删除成功！')
            }
          })
        })
      }
    }
  }
</script>
