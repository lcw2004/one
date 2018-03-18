package com.lcw.one.main;

import com.lcw.one.file.store.LocalFileStoreImpl;
import com.lcw.one.util.utils.IOUtils;
import com.lcw.one.util.utils.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream baos = new ByteArrayInputStream(randomContent.getBytes());
        String path = iFileStore.storeFile(baos, "txt");
        baos.close();

        // 验证
        File file = new File(basePath + path);
        Assert.assertTrue(file.exists());

        // 验证 loadFileBytes
        byte[] bytes1 = iFileStore.loadFileBytes(path);
        Assert.assertEquals(new String(bytes1), randomContent);

        // 验证 loadFile
        InputStream is = iFileStore.loadFile(path);
        byte[] bytes2 = IOUtils.readFully(is);
        is.close();
        Assert.assertEquals(new String(bytes2), randomContent);

        Assert.assertTrue(file.delete());
        Assert.assertTrue(!file.exists());
    }


}
