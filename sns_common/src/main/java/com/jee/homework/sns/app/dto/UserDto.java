package com.jee.homework.sns.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class UserDto implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTiem;
    private List<RoleDto> roles = new ArrayList<>();
    private Long statusId;
    private String headPicUrl;
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "用户密码不能为空")
    private String password;
    private String nickname;
    private List<Long> likePostIds = new ArrayList<>();
    private List<CommentDto> comments = new ArrayList<>();
    private List<PostDto> posts = new ArrayList<>();
    public void addRole(RoleDto roleDto){
        this.roles.add(roleDto);
    }
    @JsonIgnore
    public List<String> getRoleNames(){
        List<String> roleNames = new ArrayList<>();
        for(RoleDto roleDto : roles ){
            roleNames.add(roleDto.getRoleName());
        }
        return roleNames;
    }

}
