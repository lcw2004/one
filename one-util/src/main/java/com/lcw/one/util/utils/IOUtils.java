package com.lcw.one.util.utils;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils extends org.apache.commons.io.IOUtils {

    public static byte[] readFully(InputStream is) throws IOException {
        byte[] bytes = new byte[is.available()];
        readFully(is, bytes);
        return bytes;
    }
}
