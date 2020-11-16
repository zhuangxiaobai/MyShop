package com.zc.shop.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description="返回给前台的用户基本信息，不包含密码")
public class UsersVo {
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "昵称")
    private String nickname;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private String apiToken;

    @ApiModelProperty(value = "角色")
    private Long roleId;

    private Integer isDelete;

    private Integer cId;


}
