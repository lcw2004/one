          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <DictCheckbox v-model="obj.@{field.javaFieldName}" type="@{field.dictType}"></DictCheckbox>
              </FormGroup>
            </div>
          </div>