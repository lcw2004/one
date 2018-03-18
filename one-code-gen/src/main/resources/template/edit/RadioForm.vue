          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <DictRadio v-model="obj.@{field.javaFieldName}" type="@{field.dictType}"></DictRadio>
              </FormGroup>
            </div>
          </div>