package com.lcw.one.sys.rest;

import com.lcw.one.baseInfo.entity.BaseTemplateTypeEO;
import com.lcw.one.sys.service.BaseTemplateTypeEOService;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/${restPath}/base/templateType")
@Api(description = "系统模板类别")
public class BaseTemplateTypeEORestController {

    @Autowired
    private BaseTemplateTypeEOService baseTemplateTypeEOService;
    
    @ApiOperation(value = "系统模板类别列表")
    @GetMapping("")
    public ResponseMessage<List<BaseTemplateTypeEO>> list(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return Result.success(baseTemplateTypeEOService.findAll());
    }

}
