package com.fdt.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdt.project.common.ErrorCode;
import com.fdt.project.exception.BusinessException;
import com.fdt.project.mapper.InterfaceInfoMapper;
import com.fdt.tianAPICommon.model.entity.InterfaceInfo;
import com.fdt.tianAPICommon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {

        // 参数校验
        if (StringUtils.isAnyBlank(url, method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建查询调节包装器
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);

        // 使用selectOne方法查询接口信息
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
