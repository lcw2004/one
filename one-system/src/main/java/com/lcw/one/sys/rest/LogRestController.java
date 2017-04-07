package com.lcw.one.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.sys.entity.Log;
import com.lcw.one.sys.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "${restPath}/sys/log")
public class LogRestController {

    @Autowired
    private LogService logService;

    @RequiresPermissions("sys:log:view")
    @RequestMapping(value = {"list", ""})
    public Page list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) {
        Page<Log> page = logService.find(new Page<Log>(request, response), paramMap);
        return page;
    }
}
