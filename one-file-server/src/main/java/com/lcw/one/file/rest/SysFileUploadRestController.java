package com.lcw.one.file.rest;

import com.lcw.one.file.service.SysFileEOService;
import com.lcw.one.file.store.IFileStore;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.UUID;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
@RequestMapping(value = "/${restPath}/sys/file")
@Api(description = "系统 - 文件")
public class SysFileUploadRestController {

    private static final Logger logger = LoggerFactory.getLogger(SysFileUploadRestController.class);

    @Autowired
    private SysFileEOService sysFileEOService;

    @Autowired
    private IFileStore iFileStore;

    @PostMapping("/upload")
    public ResponseMessage<SysFileEO> upload(String userId, @RequestParam("file") MultipartFile file) {
        SysFileEO sysFileEO;
        InputStream is = null;
        try {
            String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());

            is = file.getInputStream();
            String path = iFileStore.storeFile(is, fileExtension);

            sysFileEO = new SysFileEO();
            sysFileEO.setFileId(UUID.randomUUID());
            sysFileEO.setFileName(FileUtil.getFileName(file.getOriginalFilename()));
            sysFileEO.setFileType(fileExtension);
            sysFileEO.setContentType(file.getContentType());
            sysFileEO.setSavePath(path);
            sysFileEO.setCreateTime(new Date());
            sysFileEO.setUserId(userId);
            sysFileEOService.save(sysFileEO);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return Result.error("文件存储失败，请重试");
        } finally {
            IOUtils.closeQuietly(is);
        }

        return Result.success(sysFileEO);
    }

}
