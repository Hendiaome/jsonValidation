package com.zhaotm.framework.validaion.model;

import com.zhaotm.framework.validaion.annotation.Validation;

/**
 * Created by zhao on 2018/4/1.
 */
public class JsonChild {

    @Validation(msg = "孩子名字不能空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
