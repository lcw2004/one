package com.lcw.one.file.rest;

import com.lcw.one.file.service.SysFileEOService;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.IOUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/${restPath}/sys/file")
@Api(description = "系统 - 文件")
public class SysFileUploadRestController {

    private static final Logger logger = LoggerFactory.getLogger(SysFileUploadRestController.class);

    @Autowired
    private SysFileEOService sysFileEOService;

    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage<SysFileEO> upload(String userId, @RequestParam("file") MultipartFile file) {
        SysFileEO sysFileEO;
        try {
            sysFileEO = sysFileEOService.saveSysFile(userId, file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return Result.error("文件存储失败，请重试");
        }
        return Result.success(sysFileEO);
    }

    public SysFileEO saveSysFile(String userId, InputStream is, String fileName, String contentType) {
        return sysFileEOService.saveSysFile(userId, is, fileName, contentType);
    }

}
