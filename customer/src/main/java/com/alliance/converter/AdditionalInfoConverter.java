package com.alliance.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

import com.alliance.entity.AdditionalInfo;

@Converter
public class AdditionalInfoConverter implements AttributeConverter<AdditionalInfo, String> {

    private static final String ZERO_PLACEHOLDER = "00";
    private static final Integer RECORDS_NUMBER = 20;

    @Override
    public String convertToDatabaseColumn(AdditionalInfo additionalInfo) {
        List<Integer> anotherCustomers = additionalInfo.getAnotherCustomers();
        if (Objects.isNull(anotherCustomers)) {
            return null;
        }
        return StringUtils.join(anotherCustomers.stream().map(i -> i == null ? ZERO_PLACEHOLDER : String.format("%02d", i)).toArray());
    }

    @Override
    public AdditionalInfo convertToEntityAttribute(String dbData) {
        List<Integer> res = Arrays.asList(new Integer[RECORDS_NUMBER]);
        Collections.fill(res, 0);
        if (StringUtils.isBlank(dbData)) {
            return new AdditionalInfo(res);
        }
        List<Integer> anotherCustomers = Arrays.stream(dbData.split("(?<=\\G..)")).map(Integer::valueOf)
                .collect(Collectors.toList());
        return new AdditionalInfo(anotherCustomers);
    }
}
