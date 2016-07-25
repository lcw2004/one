package com.lcw.one.modules.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.modules.sys.entity.Dict;
import com.lcw.one.modules.sys.entity.Menu;
import com.lcw.one.modules.sys.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/dict")
public class DictRestController {

    @Autowired
    private DictService dictService;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page list(Dict dict, HttpServletRequest request, HttpServletResponse response) {
        Page<Dict> page = dictService.find(new Page<Dict>(request, response), dict);
        return page;
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = "application/json")
    public List<String> dictTypeList() {
        List<String> typeList = dictService.findTypeList();
        return typeList;
    }
}
