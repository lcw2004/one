package com.lcw.one.common.utils.validatecode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lcw20 on 2016-07-18.
 */
public interface ValidateCodeInterface {

    void generate(int width, int height, OutputStream os) throws IOException;
}
