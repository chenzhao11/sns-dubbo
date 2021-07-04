package com.jee.homework.sns.app.dto;

import com.jee.homework.sns.common.constant.StatusConstants;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDto implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTiem;
    @NotNull(message = "状态名不能为空")
    private String statusName;
    public StatusDto(Long status_id){
        if(status_id == StatusConstants.DISABLE_ID){
            this.id = status_id;
            this.statusName = StatusConstants.DISABLE;
        }
        else if(status_id == StatusConstants.ENABLE_ID){
            this.id = status_id;
            this.statusName = StatusConstants.ENABLE;
        }
    }
}
