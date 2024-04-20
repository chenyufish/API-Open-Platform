package com.fishman.fishmanAPI_common.service.inner;
import com.fishman.fishmanAPI_common.model.vo.UserVO;
/**
 * 内部用户服务
 *
 */
public interface InnerUserService {
    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    UserVO getInvokeUserByAccessKey(String accessKey);
}
