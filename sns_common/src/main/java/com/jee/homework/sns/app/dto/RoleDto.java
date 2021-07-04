package com.jee.homework.sns.app.dto;

import com.jee.homework.sns.common.constant.RoleConstants;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class RoleDto implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTiem;
    @NotNull(message = "输入的角色名不能为空！")
    private String roleName;
    public RoleDto(Long id){
        if(id == RoleConstants.ROLE_USER_ID){
            this.id = RoleConstants.ROLE_USER_ID;
            this.roleName = RoleConstants.ROLE_USER;
        }
        else if(id ==  RoleConstants.ROLE_ADMIN_ID){
            this.id = RoleConstants.ROLE_ADMIN_ID;
            this.roleName  = RoleConstants.ROLE_ADMIN;
        }
    }
}
