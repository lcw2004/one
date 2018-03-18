          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <DatePicker v-model="obj.@{field.javaFieldName}" @{field.isRequired == 1 ? "v-validate=\"'required'\" data-vv-name=\"" + field.fieldName + "\" data-vv-value-path=\"innerValue\"" : ""}></DatePicker>
              </FormGroup>
            </div>
          </div>