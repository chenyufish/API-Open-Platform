package com.fishman.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fishman.fishmanAPI_common.model.entity.InterfaceInfo;

/**
* @author fishman
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2024-04-11 22:42:47
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
