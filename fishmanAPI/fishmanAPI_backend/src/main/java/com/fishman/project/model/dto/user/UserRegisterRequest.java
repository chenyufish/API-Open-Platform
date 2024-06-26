package com.fishman.project.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String checkPassword;

    private String invitationCode;

    private String agreeToAnAgreement;
}
