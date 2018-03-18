          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <DictSelect v-model="obj.@{field.javaFieldName}" type="@{field.dictType}"></DictSelect>
              </FormGroup>
            </div>
          </div>