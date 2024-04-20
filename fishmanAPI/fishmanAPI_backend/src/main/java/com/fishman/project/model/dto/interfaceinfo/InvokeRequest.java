package com.fishman.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**

 * @Description: 调用请求
 */
@Data
public class InvokeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private List<Field> requestParams;
    private String userRequestParams;

    @Data
    public static class Field {
        private String fieldName;
        private String value;
    }
}

