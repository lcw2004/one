          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <FileUpload type="rar,zip,doc,docx,pdf" :callback="uploadSuccess" :errorCallback="uploadError"></FileUpload>
              </FormGroup>
            </div>
          </div>