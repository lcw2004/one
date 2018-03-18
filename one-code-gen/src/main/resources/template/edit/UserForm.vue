          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <input type="text" class="form-control" v-model="obj.@{field.javaFieldName}" @{field.isRequired == 1 ? "v-validate=\"'required'\" name=\"" + field.fieldName + "\"" : ""} />
              </FormGroup>
            </div>
          </div>