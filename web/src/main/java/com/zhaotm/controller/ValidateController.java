package com.zhaotm.controller;

import com.zhaotm.framework.validaion.annotation.RequestBody;
import com.zhaotm.framework.validaion.model.JsonRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaotianming1
 * @date 2018/3/29
 */
@RestController
public class ValidateController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/validate")
    public Object validate(@RequestBody(code = "90000", msg = "呵呵呵") JsonRequest request) {
        return 123;
    }

    @PostMapping("/validate1")
    public Object validate1(@RequestBody JsonRequest request) {
        return "ok";
    }
}
