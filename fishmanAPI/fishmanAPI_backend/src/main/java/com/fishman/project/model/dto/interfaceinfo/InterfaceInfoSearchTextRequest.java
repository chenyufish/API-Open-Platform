package com.fishman.project.model.dto.interfaceinfo;

import com.fishman.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 界面信息搜索文本请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoSearchTextRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = -6337349622479990038L;

    private String searchText;
}
