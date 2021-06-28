package com.athome.bo;

import lombok.Data;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/28 17:44
 * @Version 1.0
 */
@Data
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
