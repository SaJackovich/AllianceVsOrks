package com.alliance.type.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public interface IntegerEnumType extends EnumType<Integer> {

    @JsonValue
    Integer getValue();

}
