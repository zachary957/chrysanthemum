package com.eutopia.entity;

import com.eutopia.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {

    private String menuDescription;

    private Integer sort;
}
