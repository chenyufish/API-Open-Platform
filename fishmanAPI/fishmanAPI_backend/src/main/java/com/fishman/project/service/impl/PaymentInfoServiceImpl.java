package com.fishman.project.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.project.mapper.PaymentInfoMapper;
import com.fishman.project.model.entity.PaymentInfo;
import com.fishman.project.model.vo.PaymentInfoVo;
import com.fishman.project.service.PaymentInfoService;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**

 * @Description: 支付信息服务impl
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Override
    public boolean createPaymentInfo(PaymentInfoVo paymentInfoVo) {
        String transactionId = paymentInfoVo.getTransactionId();
        String tradeType = paymentInfoVo.getTradeType();
        String tradeState = paymentInfoVo.getTradeState();
        String tradeStateDesc = paymentInfoVo.getTradeStateDesc();
        String successTime = paymentInfoVo.getSuccessTime();
        WxPayOrderQueryV3Result.Payer payer = paymentInfoVo.getPayer();
        WxPayOrderQueryV3Result.Amount amount = paymentInfoVo.getAmount();

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(paymentInfoVo.getOutTradeNo());
        paymentInfo.setTransactionId(transactionId);
        paymentInfo.setTradeType(tradeType);
        paymentInfo.setTradeState(tradeState);
        if (StringUtils.isNotBlank(successTime)) {
            paymentInfo.setSuccessTime(successTime);
        }
        paymentInfo.setOpenid(payer.getOpenid());
        paymentInfo.setPayerTotal(amount.getPayerTotal());
        paymentInfo.setCurrency(amount.getCurrency());
        paymentInfo.setPayerCurrency(amount.getPayerCurrency());
        paymentInfo.setTotal(amount.getTotal());
        paymentInfo.setTradeStateDesc(tradeStateDesc);
        paymentInfo.setContent(JSONUtil.toJsonStr(paymentInfoVo));
        return this.save(paymentInfo);
    }
}




