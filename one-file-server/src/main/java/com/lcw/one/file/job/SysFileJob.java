package com.lcw.one.file.job;

import com.lcw.one.file.bean.constant.FileStoreTypeEnum;
import com.lcw.one.file.service.SysFileEOService;
import com.lcw.one.file.store.impl.AliyunOSSStoreImpl;
import com.lcw.one.file.store.impl.LocalFileStoreImpl;
import com.lcw.one.sys.entity.SysFileEO;
import com.lcw.one.util.http.PageInfo;
import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.DateUtils;
import com.lcw.one.util.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 该JOB用于定时同步本地文件到OOS服务器上
 */
@Component
@EnableScheduling
public class SysFileJob {

    private static final Logger logger = LoggerFactory.getLogger(SysFileJob.class);

    private static final int PAGE_SIZE = 20;
    private static final int THREAD_POOL_COUNT = 5;
    private static final ScheduledExecutorService THREAD_POOL = Executors.newScheduledThreadPool(THREAD_POOL_COUNT);

    @Value("${one.file.type}")
    private String fileStoreType;

    @Value("${one.file.local-oss.max-time}")
    private Integer maxTime;

    @Autowired
    private SysFileEOService sysFileEOService;

    @Autowired
    private LocalFileStoreImpl localFileStore;

    @Autowired
    private AliyunOSSStoreImpl aliyunOSSStore;


    @Scheduled(cron = "0 0 23 * * ?")
    public void syncLocalFileToOSS() {
        if (!FileStoreTypeEnum.LOCAL_OSS.getValue().equals(fileStoreType)) {
            return;
        }

        logger.info("==========================");
        logger.info("扫描文件");

        Date createTimeFrom = DateUtils.addDays(new Date(), 0 - maxTime);
        PageInfo<SysFileEO> sysFileEOPageInfo = sysFileEOService.pageByStoreType(new PageInfo(1, PAGE_SIZE), FileStoreTypeEnum.LOCAL.getValue(), createTimeFrom);
        do {
            logger.info("处理第{}页数据，共{}页数据", sysFileEOPageInfo.getPageNo(), sysFileEOPageInfo.getPageCount());

            if (CollectionUtils.isNotEmpty(sysFileEOPageInfo.getList())) {
                for (SysFileEO sysFileEO : sysFileEOPageInfo.getList()) {
                    syncFileThread(sysFileEO);
                }
            } else {
                break;
            }

            sysFileEOPageInfo = sysFileEOService.pageByStoreType(new PageInfo(sysFileEOPageInfo.getPageNo() + 1, PAGE_SIZE), FileStoreTypeEnum.LOCAL.getValue(), createTimeFrom);
        } while (sysFileEOPageInfo.getPageNo() <= sysFileEOPageInfo.getPageCount());
        logger.info("==========================");
    }

    /**
     * 同步文件到服务器上
     *
     * @param sysFileEO
     */
    private void syncFile(SysFileEO sysFileEO) {
        InputStream is = null;
        try {
            // 检查文件是否存在
            if (!localFileStore.isExists(sysFileEO.getSavePath())) {
                logger.warn("同步文件[{}]失败，文件[{}]不存在", sysFileEO.getFileId(), sysFileEO.getSavePath());
                return;
            }

            // 同步文件到阿里云OSS上
            logger.warn("开始同步文件[{}]", sysFileEO.getFileId());
            is = localFileStore.loadFile(sysFileEO.getSavePath());
            aliyunOSSStore.storeFile(is, sysFileEO.getSavePath());

            // 修改文件存储类别
            sysFileEOService.updateStoreType(sysFileEO.getFileId(), FileStoreTypeEnum.OSS.getValue());

            // 确定已经同步成功，删除本地文件
            if (aliyunOSSStore.isExists(sysFileEO.getSavePath())) {
                localFileStore.delete(sysFileEO.getSavePath());
            }

            logger.warn("同步文件[{}]成功", sysFileEO.getFileId());
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            logger.warn("同步文件[{}]失败", sysFileEO.getFileId());
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    private void syncFileThread(SysFileEO sysFileEO) {
        THREAD_POOL.schedule(() -> syncFile(sysFileEO), 0, TimeUnit.SECONDS);
    }

}
