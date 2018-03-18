package @{table.packageName}.entity;

import javax.persistence.*;
import java.io.Serializable;
<%
for (importClass in imports) {
    print("import " + importClass + ";\n");
}
%>

public class @{table.className}PK implements Serializable {
<%
    for (field in idFieldList) {
%>

    @Id
    @Column(name = "@{field.fieldId}")
    private @{field.javaType} @{field.javaFieldName};
<%
    }
%>
<%
    for (field in idFieldList) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        @{table.className}PK that = (@{table.className}PK) o;

<%
    for (field in idFieldList) {
%>
        if (@{field.javaFieldName} != null ? !@{field.javaFieldName}.equals(that.@{field.javaFieldName}) : that.@{field.javaFieldName} != null) return false;
<%
    }
%>

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
<%
    for (field in idFieldList) {
%>
        result = 31 * result + (@{field.javaFieldName} != null ? @{field.javaFieldName}.hashCode() : 0);
<%
    }
%>
        return result;
    }

}