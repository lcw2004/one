package com.lcw.one.file.service;

import com.lcw.one.file.dao.SysFileEODao;
import com.lcw.one.file.store.FileStoreFactory;
import com.lcw.one.file.store.IFileStore;
import com.lcw.one.sys.bean.SysFileVO;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.exception.OneBaseException;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.service.CrudService;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class SysFileEOService extends CrudService<SysFileEODao, SysFileEO, String> {

    @Autowired
    private FileStoreFactory fileStoreFactory;

    /**
     * 文件上传，根据配置的文件策略保存文件
     *
     * @param userId         当前用户ID
     * @param is             文件输入流
     * @param fileName       文件名称
     * @param contentType    文件类别
     * @param permissionType 文件权限类型
     * @return
     */
    public SysFileEO saveSysFile(String userId, InputStream is, String fileName, String contentType, Integer permissionType) {
        SysFileEO sysFileEO;
        try {
            String fileExtension = FileUtil.getFileExtension(fileName);
            String relativePath = FileUtil.generateFilePath(fileExtension);

            // 存储文件
            IFileStore fileStore = fileStoreFactory.defaultFileStore();
            fileStore.storeFile(is, relativePath);

            // 存储文件对象
            sysFileEO = new SysFileEO();
            sysFileEO.setFileId(UUID.randomUUID());
            sysFileEO.setFileName(FileUtil.getFileName(fileName));
            sysFileEO.setFileType(fileExtension);
            sysFileEO.setContentType(contentType);
            sysFileEO.setSavePath(relativePath);
            sysFileEO.setStoreType(fileStore.getStoreType());
            sysFileEO.setCreateTime(new Date());
            sysFileEO.setUserId(userId);
            sysFileEO.setPermissionType(permissionType);
            save(sysFileEO);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("文件存储失败，请重试");
        }
        return sysFileEO;
    }

    public SysFileVO getSysFileVO(String fileId) {
        SysFileEO sysFileEO = get(fileId);
        SysFileVO sysFileVO = new SysFileVO();
        sysFileVO.setFileName(sysFileEO.getFileName());
        sysFileVO.setFileType(sysFileEO.getFileType());
        sysFileVO.setFileId(sysFileEO.getFileId());
        return sysFileVO;
    }

    public PageInfo<SysFileEO> pageByStoreType(PageInfo pageInfo, String storeType, Date createTimeFrom) {
        return dao.pageByStoreType(pageInfo, storeType, createTimeFrom);
    }

    public void updateStoreType(String fileId, String storeType) {
        dao.updateStoreType(fileId, storeType);
    }

}
