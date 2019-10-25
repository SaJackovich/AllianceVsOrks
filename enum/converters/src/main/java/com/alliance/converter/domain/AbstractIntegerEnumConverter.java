package com.alliance.converter.domain;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import com.alliance.type.domain.IntegerEnumType;
import com.alliance.utils.Utils;

public abstract class AbstractIntegerEnumConverter<X extends IntegerEnumType>
        implements AttributeConverter<X, Integer> {

    @Override
    public Integer convertToDatabaseColumn(X attribute) {
        return Objects.nonNull(attribute) ? attribute.getValue() : null;
    }

    @Override
    public X convertToEntityAttribute(Integer dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        Class<X> type = Utils.getGenericParamClass(this.getClass());
        return Stream.of(type.getEnumConstants())
                .filter(e -> e.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unable ti find enum [%s] with value [%s]",
                        type.getName(), dbData)));
    }
}
