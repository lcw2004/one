          <div class="row">
            <div class="col-md-6">
              <FormGroupStatic label="@{field.fieldName}">
                <DictLabel :value="obj.@{field.javaFieldName}" type="@{field.dictType}"></DictLabel>
              </FormGroupStatic>
            </div>
          </div>