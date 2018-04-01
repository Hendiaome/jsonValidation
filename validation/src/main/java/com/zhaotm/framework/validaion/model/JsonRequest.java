package com.zhaotm.framework.validaion.model;

import com.zhaotm.framework.validaion.annotation.Validation;

/**
 * @author zhaotianming1
 * @date 2018/3/29
 */
public class JsonRequest {

    @Validation(msg = "姓名不能空")
    private String name;

    @Validation(msg = "年龄不能空")
    private Integer age;

    @Validation(isObject = true, msg = "孩子不能空")
    private JsonChild child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public JsonChild getChild() {
        return child;
    }

    public void setChild(JsonChild child) {
        this.child = child;
    }
}
