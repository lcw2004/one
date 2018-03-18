package @{table.packageName}.dao;

import @{table.packageName}.entity.@{table.className};
<% if (queryFieldListLength > 0) { %>
import @{table.packageName}.bean.@{table.classNameNoEO}QueryCondition;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.utils.StringUtils;
import com.lcw.one.util.utils.DateUtils;
<% } %>
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class @{table.className}Dao extends BaseRepositoryImpl<@{table.className}, String> {

    @Autowired
    public @{table.className}Dao (EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(@{table.className}.class, entityManager), entityManager);
    }

<% if (queryFieldListLength > 0) { %>
    public PageInfo<@{table.className}> page(@{table.classNameNoEO}QueryCondition queryCondition) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        hql.append(" from @{table.className} where 1=1 ");

    <% for (field in queryFieldList) { %>
        <% if (field.javaType == "Date") { %>
        if (StringUtils.isNotEmpty(queryCondition.get@{field.javaFieldUpperCamel}From())) {
            hql.append(" and @{field.javaFieldName} >= :@{field.javaFieldName}From");
            paramMap.put("@{field.javaFieldName}From", DateUtils.stringToDate(queryCondition.get@{field.javaFieldUpperCamel}From(), DateUtils.yyyy_MM_dd_EN));
        }

        if (StringUtils.isNotEmpty(queryCondition.get@{field.javaFieldUpperCamel}To())) {
            hql.append(" and @{field.javaFieldName} <= :@{field.javaFieldName}To");
            paramMap.put("@{field.javaFieldName}To", DateUtils.stringToDate(queryCondition.get@{field.javaFieldUpperCamel}To(), DateUtils.yyyy_MM_dd_EN));
        }

        <% } else { %>
        if (StringUtils.isNotEmpty(queryCondition.get@{field.javaFieldUpperCamel}())) {
            hql.append(" and @{field.javaFieldName} @{field.queryOperation} :@{field.javaFieldName}");
            <% if (field.queryType == "8") { %>
            paramMap.put("@{field.javaFieldName}", "%" + queryCondition.get@{field.javaFieldUpperCamel}() + "%");
            <% } else if (field.queryType == "9") { %>
            paramMap.put("@{field.javaFieldName}", "%" + queryCondition.get@{field.javaFieldUpperCamel}());
            <% } else if (field.queryType == "10") { %>
            paramMap.put("@{field.javaFieldName}", queryCondition.get@{field.javaFieldUpperCamel}() + "%");
            <% } else if (field.queryType == "11") { %>
            paramMap.put("@{field.javaFieldName}", StringUtils.stringToIntList(queryCondition.get@{field.javaFieldUpperCamel}()));
            <% } else { %>
            paramMap.put("@{field.javaFieldName}", @{field.queryValueOf});
            <% } %>
        }

        <% } %>
    <% } %>

        return page(queryCondition.getPageInfo(), hql.toString(), paramMap);
    }
<% } %>
    
}