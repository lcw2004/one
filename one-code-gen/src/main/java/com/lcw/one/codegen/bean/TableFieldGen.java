package com.lcw.one.codegen.bean;

import com.lcw.one.codegen.bean.consts.SqlOperationEnum;
import com.lcw.one.codegen.core.form.FormTemplateBuilder;
import com.lcw.one.codegen.entity.CodeGenTableFieldEO;
import com.lcw.one.codegen.util.JavaFieldTypeDefaultValueMapper;
import com.lcw.one.codegen.util.JavaFieldTypeValueOfMapper;
import com.lcw.one.codegen.util.NameUtils;
import com.lcw.one.util.utils.StringUtils;

import java.text.MessageFormat;

public class TableFieldGen extends CodeGenTableFieldEO {

    public String getJavaFieldUpperCamel() {
        return NameUtils.upperFirstChar(getJavaFieldName());
    }

    public String getEditFormHtml() {
        return FormTemplateBuilder.getEditFormHtml(this);
    }

    public String getViewFormHtml() {
        return FormTemplateBuilder.getViewFormHtml(this);
    }

    public String getQueryFormHtml() {
        return FormTemplateBuilder.getQueryFormHtml(this);
    }

    public String getQueryOperation() {
        return SqlOperationEnum.get(getQueryType()).getOperation();
    }

    public String getQueryValueOf() {
        String formatStr = JavaFieldTypeValueOfMapper.getValue(getJavaType());
        return MessageFormat.format(formatStr, "queryCondition.get" + getJavaFieldUpperCamel() + "()");
    }

    public String getSQLParamValue() {
        SqlOperationEnum sqlOperationEnum = SqlOperationEnum.get(getQueryType());

        String paramValue = getFieldId();
        if (sqlOperationEnum == SqlOperationEnum.LIKE) {
            paramValue = "%" + getFieldId() + "%";
        } else if (sqlOperationEnum == SqlOperationEnum.LEFT_LIKE) {
            paramValue = "%" + getFieldId();
        } else if (sqlOperationEnum == SqlOperationEnum.RIGHT_LIKE) {
            paramValue = getFieldId() + "%";
        } else if (sqlOperationEnum == SqlOperationEnum.IN) {
        } else {
            paramValue = getFieldId();
        }
        return paramValue;
    }

    public Object getDefaultValue () {
        return JavaFieldTypeDefaultValueMapper.getValue(getJavaType());
    }
}
