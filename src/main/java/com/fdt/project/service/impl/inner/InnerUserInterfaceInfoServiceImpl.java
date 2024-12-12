package com.fdt.project.service.impl.inner;

import com.fdt.project.service.UserInterfaceInfoService;
import com.fdt.tianAPICommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    /**
     * 调用现成的计算用户调用接口次数的方法。
     * @param interfaceInfoId 接口id
     * @param userId 用户id
     * @return 调用次数是否记录成功
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
