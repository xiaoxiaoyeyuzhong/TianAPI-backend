package com.fdt.project.model.dto.UserInterfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

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