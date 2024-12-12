package com.fdt.project.service;

import com.fdt.tianAPICommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 冯德田
* description 针对表【user_interface_info】的数据库操作Service
* createDate 2024-09-17 20:05:49
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    /**
     * 用户接口关系校验方法
     * @param userInterfaceInfo 用户接口关系对象
     * @param add 是否为创建校验
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 统计接口调用次数
     * @param interfaceInfoId 接口id
     * @param userId 用户id
     * @return boolean 是否统计成功
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
