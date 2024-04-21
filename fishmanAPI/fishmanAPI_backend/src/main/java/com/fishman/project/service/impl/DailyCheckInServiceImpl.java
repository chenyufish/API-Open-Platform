package com.fishman.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.project.mapper.DailyCheckInMapper;
import com.fishman.project.model.entity.DailyCheckIn;
import com.fishman.project.service.DailyCheckInService;
import org.springframework.stereotype.Service;

/**
 * @Description: 每日签到服务impl
 */
@Service
public class DailyCheckInServiceImpl extends ServiceImpl<DailyCheckInMapper, DailyCheckIn>
        implements DailyCheckInService {

}




