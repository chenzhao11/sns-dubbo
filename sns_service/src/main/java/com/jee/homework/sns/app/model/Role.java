package com.jee.homework.sns.app.model;

import com.jee.homework.sns.common.persistence.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {
    /**
     * 用户的角色表
     */
    @Column(name = "role_name",unique = true, nullable = false, length = 20)
    private String roleName;
}
