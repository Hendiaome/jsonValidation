package com.zhaotm.framework.validaion.interfaced;

/**
 * 校验参数接口
 */
public interface ValidateFiled {
    /**
     * 校验方法
     * @param args
     * @return 通过true
     */
    boolean validate(String args);
}
