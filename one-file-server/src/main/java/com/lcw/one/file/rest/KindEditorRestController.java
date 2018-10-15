package com.lcw.one.file.rest;

import com.lcw.one.file.service.SysFileEOService;
import com.lcw.one.file.bean.constant.FilePermissionTypeEnum;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.exception.OneBaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/${restPath}/sys/file")
@Api(description = "系统 - Kind文件")
public class KindEditorRestController {

    private static final Logger logger = LoggerFactory.getLogger(KindEditorRestController.class);

    @Value("${one.url}")
    private String interfaceUrl;

    @Autowired
    private SysFileEOService sysFileEOService;

    @ApiOperation("Kind文件上传")
    @PostMapping(path = "/kind-upload")
    public Map<String, Object> upload(String userId, @RequestParam("imgFile") MultipartFile file) {
        try {
            SysFileEO sysFile = sysFileEOService.saveSysFile(userId, file.getInputStream(), file.getOriginalFilename(), file.getContentType(), FilePermissionTypeEnum.OUTER.getValue());
            return success(interfaceUrl + "/api/sys/file/" + sysFile.getFileId() + "/download-ext");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return getError("文件存储失败，请重试");
        } catch (OneBaseException e) {
            return getError(e.getMessage());
        } catch (Exception e) {
            return getError(e.getMessage());
        }
    }

    private Map<String, Object> getError(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", 1);
        result.put("message", message);
        return result;
    }

    private Map<String, Object> success(String url) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", 0);
        result.put("url", url);
        return result;
    }

}
