package @{table.packageName}.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
<%
for (importClass in imports) {
    print("import " + importClass + ";\n");
}
%>

/**
 * @{table.tableName}
 */
@Entity
@Table(name = "@{table.tableId}")
<%
    if (table.isJointKey) {
        print("@IdClass(" + table.className + "PK.class)\n");
    }
%>
public class @{table.className} {

<%
    var list = table.fieldList;
    for (field in list) {
        if (field.fieldName != null) {
            print("\t/**\n");
            print("\t * " + field.fieldName + "\n");
            print("\t */\n");
        }
        if (field.fieldType == "DATE" || field.fieldType == "DATETIME") {
            var format = "yyyy-MM-dd";
            if (field.fieldType == "DATETIME") {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            print("\t@JsonFormat(pattern = \"" + format + "\", timezone = \"GMT+8\")\n");
        }
        print(field.isPrimaryKey == 1? "\t@Id\n" : '\t@Basic\n');
        print("\t@Column(name = \"" + field.fieldId + "\")\n");
        print("\tprivate " + field.javaType + " " + field.javaFieldName + ";\n");
        print("\n");
    }
%>
<%
    for (field in table.fieldList) {
%>

    public @{field.javaType} get@{field.javaFieldUpperCamel} () {
        return this.@{field.javaFieldName};
    }

    public void set@{field.javaFieldUpperCamel} (@{field.javaType} @{field.javaFieldName}) {
        this.@{field.javaFieldName} = @{field.javaFieldName};
    }
<%
    }
%>

}