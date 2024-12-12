package com.fdt.project.service.impl.inner;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdt.project.common.ErrorCode;
import com.fdt.project.exception.BusinessException;
import com.fdt.project.mapper.UserMapper;
import com.fdt.project.service.UserService;
import com.fdt.tianAPICommon.model.entity.User;
import com.fdt.tianAPICommon.service.InnerUserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKey) {

        // 参数校验
        if(StringUtils.isNotBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);

        // 查询用户
        return userMapper.selectOne(queryWrapper);
    }
}
