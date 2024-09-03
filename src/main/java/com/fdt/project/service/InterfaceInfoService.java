package com.fdt.project.service;

import com.fdt.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 冯德田
* @description 针对表【interface_info(接口表)】的数据库操作Service
* @createDate 2024-09-03 11:51:22
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 接口信息校验方法
     * @param interfaceInfo 接口信息对象
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
