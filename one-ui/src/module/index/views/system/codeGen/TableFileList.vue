<template>
  <div>
    <div class="row row-margin-bottom">
      <div class="col-md-6">
        <FormGroup label="表名">
          {{ tableInfo.tableName }}
        </FormGroup>
      </div>
      <div class="col-md-6">
        <FormGroup label="表中文名">
          <input type="text" class="form-control" v-model="tableInfo.tableNameCn" maxlength="100">
        </FormGroup>
      </div>
    </div>
  
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>说明</th>
          <th>表字段名称</th>
          <th>表字段类型</th>
          <th>程序类型</th>
          <th>长度</th>
          <th>精度</th>
          <th>主键</th>
          <th>递增</th>
          <th>非空</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="field of tableInfo.tableFieldList">
          <td><input class="form-control input-sm" type="text"></td>
          <td>{{ field.tableField }}</td>
          <td>{{ field.tableFieldType }}</td>
          <td>{{ field.javaType }}</td>
          <td>{{ field.length }}</td>
          <td>
            <template v-if="field.precision > 0">{{ field.precision }}</template>
          </td>
          <td>
            <template v-if="field.isPrimaryKey"><i class="fa fa-check text-green"></i></template>
          </td>
          <td>
            <template v-if="field.isAutoincrement"><i class="fa fa-check text-green"></i></template>
          </td>
          <td>
            <template v-if="!field.isNullable"><i class="fa fa-check text-green"></i></template>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    table: {},
    type: {},
    config: {}
  },
  data: function () {
    return {
      tableInfo: {}
    }
  },
  mounted () {
    this.getTableInfo()
  },
  methods: {
    getTableInfo () {
      this.$api.system.getTable(this.table).then((response) => {
        let result = response.data
        if (result.ok && result.data) {
          this.tableInfo = result.data
        }
      })
    }
  },
  watch: {
    table: {
      handler: function () {
        this.getTableInfo()
      },
      deep: true
    }
  }
}
</script>
