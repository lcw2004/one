          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <InlineEditor v-model="obj.@{field.javaFieldName}"></InlineEditor>
              </FormGroup>
            </div>
          </div>