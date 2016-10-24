package com.lcw.one.sys.rest;

import com.lcw.one.common.persistence.Page;
import com.lcw.one.sys.entity.Dict;
import com.lcw.one.sys.service.DictService;
import com.lcw.one.sys.utils.DictUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "${restPath}/sys/dict")
public class DictRestController {

    @Autowired
    private DictService dictService;

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page list(Dict dict, HttpServletRequest request, HttpServletResponse response) {
        Page<Dict> page = dictService.find(new Page<Dict>(request, response), dict);
        return page;
    }

    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public void update(@RequestBody Dict dict) {
        dictService.save(dict);
    }

    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public void add(@RequestBody Dict dict) {
        dictService.save(dict);
    }

    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Dict get(@PathVariable String id) {
        return dictService.get(id);
    }

    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable String id) {
        dictService.delete(id);
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = "application/json")
    public List<String> dictTypeList() {
        List<String> typeList = dictService.findTypeList();
        return typeList;
    }

    @RequestMapping(value = "/listByType/{type}", method = RequestMethod.GET, produces = "application/json")
    public List<Dict> getDictListByType(@PathVariable String type) {
        return DictUtils.getDictList(type);
    }
}
