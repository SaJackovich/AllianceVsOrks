package com.alliance.type.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public interface StringEnumType extends EnumType<String> {

    @JsonValue
    String getValue();

}
