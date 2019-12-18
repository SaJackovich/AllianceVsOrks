package com.alliance.type;

import com.alliance.type.domain.IntegerEnumType;

public enum AnimalCondition implements IntegerEnumType {

    FREE(1),
    IN_CELL(2),
    IN_ROAD(3);

    private Integer value;

    AnimalCondition(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
