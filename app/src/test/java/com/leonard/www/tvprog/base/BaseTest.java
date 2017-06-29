package com.leonard.www.tvprog.base;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Leonard Wu on 20/2/17.
 */

public abstract class BaseTest {
    protected static final int WAITING = 2000;//milli seconds
    protected static final int LONG_WAITING = 100000;//milli seconds

    protected CountDownLatch lock = new CountDownLatch(1);

    public static String fileToString(String fileName) throws Exception{
        return Files.toString(new File("app/src/test/res/" + fileName), Charsets.UTF_8);
    }
}
