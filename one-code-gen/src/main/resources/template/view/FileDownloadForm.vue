          <div class="row">
            <div class="col-md-@{rowWidth}">
              <FormGroup label="@{field.fieldName}" @{formWidth == 4 ? "" : "width=\"" + formWidth + "\""}>
                <FileDownloader :fileId="obj.@{field.javaFieldName}">下载</FileDownloader>
              </FormGroup>
            </div>
          </div>