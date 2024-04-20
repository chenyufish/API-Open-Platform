package com.fishman.project.service.inner.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishman.fishmanAPI_common.model.entity.User;
import com.fishman.fishmanAPI_common.service.InnerUserService;
import com.fishman.project.common.ErrorCode;
import com.fishman.project.exception.BusinessException;
import com.fishman.project.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部用户服务实现类
 *
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
