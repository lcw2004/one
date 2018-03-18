            <div class="col-md-4">
              <FormGroup label="@{field.fieldName}">
                <DictSelect v-model="param.@{field.javaFieldName}" type="@{field.dictType}" :showEmptyValue="true"></DictSelect>
              </FormGroup>
            </div>
