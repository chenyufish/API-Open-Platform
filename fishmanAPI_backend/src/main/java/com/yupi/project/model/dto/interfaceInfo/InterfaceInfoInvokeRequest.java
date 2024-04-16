package com.yupi.project.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String requestParams;

    private static final long serialVersionUID = 1L;
}