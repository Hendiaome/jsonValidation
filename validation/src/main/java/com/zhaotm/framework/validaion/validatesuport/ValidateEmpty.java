package com.zhaotm.framework.validaion.validatesuport;

import com.zhaotm.framework.validaion.interfaced.ValidateFiled;
import org.springframework.util.StringUtils;

/**
 * @author zhaotianming1
 * @date 2018/3/30
 */
public class ValidateEmpty implements ValidateFiled {
    @Override
    public boolean validate(String args) {
        if (StringUtils.isEmpty(args)
                || "null".equals(args)) {
            return false;
        } else {
            return true;
        }
    }
}
