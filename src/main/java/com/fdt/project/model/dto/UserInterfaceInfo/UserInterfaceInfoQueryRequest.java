package com.fdt.project.model.dto.UserInterfaceInfo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fdt.project.common.PageRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询请求
 */
@ApiModel(description = "用户信息查询请求")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 调用用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 接口对于用户的状态 0-正常 1-禁用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}