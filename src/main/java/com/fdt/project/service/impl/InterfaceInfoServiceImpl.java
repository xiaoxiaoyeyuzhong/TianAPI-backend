package com.fdt.project.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fdt.project.common.ErrorCode;
import com.fdt.project.exception.BusinessException;
import com.fdt.project.model.entity.InterfaceInfo;
import com.fdt.project.service.InterfaceInfoService;
import com.fdt.project.mapper.InterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 冯德田
* @description 针对表【interface_info(接口表)】的数据库操作Service实现
* @createDate 2024-09-03 11:51:22
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    /**
     * 接口信息校验方法
     * @param interfaceInfo 接口信息对象
     * @param add 是否为创建校验
     */
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

        //判断对象是否为空
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        Date createTime = interfaceInfo.getCreateTime();
        Date updateTime = interfaceInfo.getUpdateTime();
        Integer isDelete = interfaceInfo.getIsDelete();

        //创建时，非空参数必须传入
        if(add){
            if(url.isEmpty() || userId<=0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不允许为空");
            }
        }
        //id必须大于等于0
        if(id!=null && id<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id应大于0");
        }
        //名称长度应该小于20
        if(name!=null && name.length()>20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"名称长度过长");
        }
        //描述长度应该小于128
        if(description!=null && description.length()>128){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"描述长度过长");
        }
        //url长度应该小于256
        if(url!=null && url.length()>256){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"url长度过长");
        }
        //接口状态暂时仅支持0和1
        if(status!=null && !(status==0 || status==1)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口状态不符合要求");
        }


    }

}




