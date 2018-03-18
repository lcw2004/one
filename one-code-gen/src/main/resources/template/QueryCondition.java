package @{table.packageName}.bean;

import com.lcw.one.util.http.bean.BaseQueryCondition;

public class @{table.classNameNoEO}QueryCondition extends BaseQueryCondition {

<%
    for (field in queryFieldList) {
        if (field.javaType == "Date") {
            print("\tprivate String " + field.javaFieldName + "From;\n");
            print("\tprivate String " + field.javaFieldName + "To;\n");
        } else {
            print("\tprivate String " + field.javaFieldName + ";\n");
        }
    }
%>

<%
    for (field in queryFieldList) {
        if (field.javaType == "Date") {
            print("\tpublic String get" + field.javaFieldUpperCamel + "From() {\n");
            print("\t\treturn this." + field.javaFieldName + "From;\n");
            print("\t}\n\n");
            print("\tpublic void set" + field.javaFieldUpperCamel + "From(String " + field.javaFieldName + "From) {\n");
            print("\t\tthis." + field.javaFieldName + "From = " + field.javaFieldName + "From;\n");
            print("\t}\n\n");

            print("\tpublic String get" + field.javaFieldUpperCamel + "To() {\n");
            print("\t\treturn this." + field.javaFieldName + "To;\n");
            print("\t}\n\n");
            print("\tpublic void set" + field.javaFieldUpperCamel + "To(String " + field.javaFieldName + "To) {\n");
            print("\t\tthis." + field.javaFieldName + "To = " + field.javaFieldName + "To;\n");
            print("\t}\n\n");
        } else {
            print("\tpublic String get" + field.javaFieldUpperCamel + "() {\n");
            print("\t\treturn this." + field.javaFieldName + ";\n");
            print("\t}\n\n");
            print("\tpublic void set" + field.javaFieldUpperCamel + "(String " + field.javaFieldName + ") {\n");
            print("\t\tthis." + field.javaFieldName + " = " + field.javaFieldName + ";\n");
            print("\t}\n\n");
        }
    }
%>
}