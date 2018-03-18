package @{table.packageName}.rest;

import @{table.packageName}.entity.@{table.className};
import @{table.packageName}.service.@{table.className}Service;
<% if (queryFieldListLength > 0) { %>
import @{table.packageName}.bean.@{table.classNameNoEO}QueryCondition;
import com.lcw.one.util.utils.StringUtils;
<% } %>
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/@{strategy.moduleSimpleName}/@{table.restPath}")
@Api(description = "@{table.tableName}")
public class @{table.className}RestController {

    @Autowired
    private @{table.className}Service @{table.classNameLowerCamel}Service;

    @ApiOperation(value = "@{table.tableName}详情")
    @GetMapping("/{id}")
    public ResponseMessage<@{table.className}> get(@PathVariable("id") String id) {
        return Result.success(@{table.classNameLowerCamel}Service.get(id));
    }

<% if (queryFieldListLength > 0) { %>
    @ApiOperation(value = "@{table.tableName}列表")
    @GetMapping("")
    public ResponseMessage<PageInfo<@{table.className}>> page(@ModelAttribute @{table.classNameNoEO}QueryCondition queryCondition) {
        return Result.success(@{table.classNameLowerCamel}Service.page(queryCondition));
    }
<% } else { %>
    @ApiOperation(value = "@{table.tableName}列表")
    @GetMapping("")
    public ResponseMessage<List<@{table.className}>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(@{table.classNameLowerCamel}Service.findAll());
    }
<% } %>

    @ApiOperation(value = "新增@{table.tableName}")
    @PostMapping("")
    public ResponseMessage<@{table.className}> save(@RequestBody @{table.className} @{table.classNameLowerCamel}) {
        return Result.success(@{table.classNameLowerCamel}Service.save(@{table.classNameLowerCamel}));
    }

    @ApiOperation(value = "修改@{table.tableName}")
    @PutMapping("")
    public ResponseMessage<@{table.className}> update(@RequestBody @{table.className} @{table.classNameLowerCamel}) {
        return Result.success(@{table.classNameLowerCamel}Service.update(@{table.classNameLowerCamel}));
    }

    @ApiOperation(value = "删除@{table.tableName}")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") String id) {
        @{table.classNameLowerCamel}Service.delete(id);
        return Result.success();
    }

}