package com.fishman.fishmanAPI_common.service.inner;

/**
 * 内部用户接口信息服务
 *
 */
public interface InnerUserInterfaceInvokeService {
    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invoke(Long interfaceInfoId, Long userId, Integer reduceScore);
}
