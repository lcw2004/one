package ${restPackageName};

import io.swagger.annotations.ApiOperation;

import ${serviceClassName};
import ${beanClassName};
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/#{restPath}/${restControllerPath}")
@Api(description = "|${beanSimpleName}|")
public class ${restSimpleName} {

    @Autowired
    private ${serviceSimpleName} ${serviceVarName};

    @ApiOperation(value = "|${beanSimpleName}|详情")
    @GetMapping("/{id}")
    public ResponseMessage<${beanSimpleName}> getById(@NotNull @PathVariable("id") String id) {
        return Result.success(${serviceVarName}.get(id));
    }

    @ApiOperation(value = "|${beanSimpleName}|列表")
    @GetMapping("")
    public ResponseMessage<List<${beanSimpleName}>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(${serviceVarName}.findAll());
    }

    @ApiOperation(value = "新增|${beanSimpleName}|")
    @PostMapping("")
    public ResponseMessage<${beanSimpleName}> save(@RequestBody ${beanSimpleName} ${beanVarName}) {
        return Result.success(${serviceVarName}.save(${beanVarName}));
    }

    @ApiOperation(value = "修改|${beanSimpleName}|")
    @PutMapping("")
    public ResponseMessage<${beanSimpleName}> update(@RequestBody ${beanSimpleName} ${beanVarName}) {
        return Result.success(${serviceVarName}.update(${beanVarName}));
    }

    @ApiOperation(value = "删除|${beanSimpleName}|")
    @DeleteMapping("/{id}")
    public ResponseMessage deleteById(@NotNull @PathVariable("id") String id) {
        ${serviceVarName}.delete(id);
        return Result.success();
    }

}
