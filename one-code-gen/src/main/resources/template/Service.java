package @{table.packageName}.service;

<% if (queryFieldListLength > 0) { %>
import @{table.packageName}.bean.@{table.classNameNoEO}QueryCondition;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.utils.StringUtils;
<% } %>
import @{table.packageName}.entity.@{table.className};
import @{table.packageName}.dao.@{table.className}Dao;
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class @{table.className}Service extends CrudService<@{table.className}Dao, @{table.className}, @{table.idField.javaType}> {

<% if (queryFieldListLength > 0) { %>
    public PageInfo<@{table.className}> page(@{table.classNameNoEO}QueryCondition queryCondition) {
        return dao.page(queryCondition);
    }
<% } %>

}