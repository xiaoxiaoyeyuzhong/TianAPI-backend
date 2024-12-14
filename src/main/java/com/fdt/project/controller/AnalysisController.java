package com.fdt.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdt.project.annotation.AuthCheck;
import com.fdt.project.common.BaseResponse;
import com.fdt.project.common.ErrorCode;
import com.fdt.project.common.ResultUtils;
import com.fdt.project.exception.BusinessException;
import com.fdt.project.mapper.UserInterfaceInfoMapper;
import com.fdt.project.model.vo.InterfaceInfoVO;
import com.fdt.project.service.InterfaceInfoService;
import com.fdt.tianAPICommon.model.entity.InterfaceInfo;
import com.fdt.tianAPICommon.model.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    /**
     * 获取被调用次数最多的接口信息列表
     * 先通过用户接口信息表获取接口id和总调用次数，再通过接口id获取接口详细信息
     * @return
     */
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo(){
        // 查询调用次数最多的接口信息列表
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        // 将获取的接口信息列表根据id进行分组，方便后面进行关联查询,方法分组后每个键对应的值是一个接口信息列表
        Map<Long,List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        // 创建查询接口详细信息的条件包装器
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        //设置查询条件，查询id为map的键的接口信息
        queryWrapper.in("id",interfaceInfoIdObjMap.keySet());
        // 查询符合条件的接口信息列表
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        // 判断查询的结果是否为空
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 将查询结果转换为VO列表
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            // 创建VO对象
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            // 复制interfaceInfo的属性到VO中
            BeanUtils.copyProperties(interfaceInfo,interfaceInfoVO);
            // 将调用次数设置到VO中
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            // 返回构建完毕的VO
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        //返回结果
        return ResultUtils.success(interfaceInfoVOList);
    }
}
