package com.fishman.project.service.inner.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishman.fishmanAPI_common.model.entity.InterfaceInfo;
import com.fishman.fishmanAPI_common.service.InnerInterfaceInfoService;
import com.fishman.project.common.ErrorCode;
import com.fishman.project.exception.BusinessException;
import com.fishman.project.mapper.InterfaceInfoMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部接口服务实现类
 *
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }

}
