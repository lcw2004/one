          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <textarea class="form-control" rows="3" v-model="obj.@{field.javaFieldName}" maxLength="200"></textarea>
              </FormGroup>
            </div>
          </div>