package com.lcw.one.file.service;

import com.lcw.one.file.dao.SysFileEODao;
import com.lcw.one.file.store.IFileStore;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class SysFileEOService extends CrudService<SysFileEODao, SysFileEO> {

    @Autowired
    private IFileStore iFileStore;

    public SysFileEO saveSysFile(String userId, InputStream is, String fileName, String contentType) {
        SysFileEO sysFileEO;
        try {
            String fileExtension = FileUtil.getFileExtension(fileName);

            String path = iFileStore.storeFile(is, fileExtension);

            sysFileEO = new SysFileEO();
            sysFileEO.setFileId(UUID.randomUUID());
            sysFileEO.setFileName(FileUtil.getFileName(fileName));
            sysFileEO.setFileType(fileExtension);
            sysFileEO.setContentType(contentType);
            sysFileEO.setSavePath(path);
            sysFileEO.setCreateTime(new Date());
            sysFileEO.setUserId(userId);
            save(sysFileEO);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("文件存储失败，请重试");
        }
        return sysFileEO;
    }
}
