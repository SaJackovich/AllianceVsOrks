package com.alliance.type;

import com.alliance.type.domain.EnumType;
import com.alliance.type.domain.IntegerEnumType;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SuperPower implements IntegerEnumType {

    SPEED(0),
    STRENGTH(1),
    INVISIBLE(2),
    FIRE_MAGIC(3);

    private Integer power;

    SuperPower(Integer power) {
        this.power = power;
    }

    @JsonValue
    public Integer getValue() {
        return power;
    }

    public static SuperPower of(Integer value) {
        return EnumType.findOptionalEnumValue(SuperPower.class, value).orElse(null);
    }

}

