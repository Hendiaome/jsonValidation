package com.zhaotm.framework.validaion.cache;

import com.zhaotm.framework.validaion.annotation.RequestBody;

/**
 * Created by zhao on 2018/3/31.
 */
public class ThreadHold {
    public static ThreadLocal<RequestBody> threadLocal = new ThreadLocal();
}
