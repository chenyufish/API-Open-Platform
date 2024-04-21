package com.fishman.project.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户创建请求
 */
@Data
public class UserAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 账号
     */
    private String userAccount;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 性别
     */
    private String gender;
    /**
     * 用户角色: user, admin
     */
    private String userRole;
    /**
     * 密码
     */
    private String userPassword;

    /**
     * 钱包余额（分）
     */
    private Integer balance;
}