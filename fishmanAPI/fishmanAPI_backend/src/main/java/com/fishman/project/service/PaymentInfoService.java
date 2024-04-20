package com.fishman.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fishman.project.model.entity.PaymentInfo;
import com.fishman.project.model.vo.PaymentInfoVo;

/**
 * @Description: 支付信息服务
 */
public interface PaymentInfoService extends IService<PaymentInfo> {
    /**
     * 创建付款信息
     *
     * @param paymentInfoVo 付款信息vo
     * @return boolean
     */
    boolean createPaymentInfo(PaymentInfoVo paymentInfoVo);
}
