package com.lcw.one.main;

import com.lcw.one.file.store.impl.LocalFileStoreImpl;
import com.lcw.one.util.utils.FileUtil;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LocalFileStoreImplTest {

    private static final String basePath = System.getProperty("java.io.tmpdir");
    private LocalFileStoreImpl iFileStore;

    @Before
    public void before() {
        iFileStore = new LocalFileStoreImpl();
        iFileStore.setBasePath(basePath);
    }

    @Test
    public void testLocalFileStore() throws IOException {
        String randomContent = UUID.randomUUID();
        String relativePath = FileUtil.generateFilePath("txt");
        InputStream baos = new ByteArrayInputStream(randomContent.getBytes());
        iFileStore.storeFile(baos, relativePath);
        baos.close();

        // 验证
        File file = new File(basePath + relativePath);
        Assert.assertTrue(file.exists());

        // 验证 loadFileBytes
        byte[] bytes1 = iFileStore.loadFileBytes(relativePath);
        Assert.assertEquals(new String(bytes1), randomContent);

        // 验证 loadFile
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        iFileStore.loadFile(relativePath, os);
        byte[] bytes2 = os.toByteArray();
        os.close();
        Assert.assertEquals(new String(bytes2), randomContent);

        Assert.assertTrue(file.delete());
        Assert.assertTrue(!file.exists());
    }


}
