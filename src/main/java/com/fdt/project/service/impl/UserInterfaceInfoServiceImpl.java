package com.fdt.project.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fdt.project.common.ErrorCode;
import com.fdt.project.exception.BusinessException;
import com.fdt.tianAPICommon.model.entity.UserInterfaceInfo;
import com.fdt.project.service.UserInterfaceInfoService;
import com.fdt.project.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 冯德田
* @description 针对表【user_interface_info】的数据库操作Service实现
* @createDate 2024-09-17 20:05:49
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    /**
     * 校验用户调用接口关系方法
     * @param userInterfaceInfo 用户接口关系对象
     * @param add 是否为创建校验
     */
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
     Long id = userInterfaceInfo.getId();
     Long userId = userInterfaceInfo.getUserId();
     Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
     Integer totalNum = userInterfaceInfo.getTotalNum();
     Integer leftNum = userInterfaceInfo.getLeftNum();
     Integer status = userInterfaceInfo.getStatus();

        //判断对象是否为空
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //创建时，非空参数必须传入
        if(add){
            if(interfaceInfoId<=0 || userId<=0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
            }
        }
        //id必须大于等于0
        if(id!=null && id<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id应大于0");
        }
        if(totalNum!=null && totalNum<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"调用次数应大于等于0");
        }
        if(leftNum!=null && leftNum<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"剩余调用次数应大于等于0");
        }
        //接口状态暂时仅支持0和1
        if(status!=null && !(status==0 || status==1)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口状态不符合要求");
        }
    }

    /**
     * 统计接口调用次数
     * @param interfaceInfoId 接口id
     * @param userId 用户id
     * @return boolean 是否成功，如果剩余调用次数不足，为false
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.update()
                .setSql("leftNum = leftNum - 1,totalNum = totalNum + 1")
                .eq("interfaceInfoId", interfaceInfoId)
                .eq("userId", userId)
                .gt("leftNum", 0) // 剩余调用次数大于0才更新
                .update();
    }
}




