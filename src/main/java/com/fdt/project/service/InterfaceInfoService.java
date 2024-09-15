package com.fdt.project.service;

import com.fdt.project.common.IdRequest;
import com.fdt.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 发布接口
     * @param idRequest id请求
     * @param request http请求
     * @return boolean 发布结果
     */
    boolean onlineInterfaceInfo(@RequestBody IdRequest idRequest, HttpServletRequest request);

    /**
     * 下线接口
     * @param idRequest id请求
     * @param request http请求
     * @return boolean 下线结果
     */
    boolean offlineInterfaceInfo(IdRequest idRequest, HttpServletRequest request);
}
