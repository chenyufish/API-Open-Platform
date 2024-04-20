package com.fishman.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fishman.project.model.entity.ProductInfo;

/**

 * @Description: 产品信息服务
 */
public interface ProductInfoService extends IService<ProductInfo> {
    /**
     * 有效产品信息
     * 校验
     *
     * @param add         是否为创建校验
     * @param productInfo 产品信息
     */
    void validProductInfo(ProductInfo productInfo, boolean add);
}
