package com.fishman.project.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.fishmanAPI_common.model.entity.UserInterfaceInfo;
import java.util.List;

/**
* @author fishman
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
* @createDate 2024-04-17 20:56:59
* @Entity com.fishman.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




