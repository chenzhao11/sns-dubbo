package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status extends BaseEntity {
    /**
     * 状态名
     */
    @Column(name = "status_name")
    private String statusName;
    public Status(Long id){
        this.setId(id);
    }
}
