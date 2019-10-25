package com.alliance.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.alliance.converter.domain.AbstractIntegerEnumConverter;
import com.alliance.type.SuperPower;

@Converter(autoApply = true)
public class SuperPowerConverter extends AbstractIntegerEnumConverter<SuperPower>
        implements AttributeConverter<SuperPower, Integer> {
}
