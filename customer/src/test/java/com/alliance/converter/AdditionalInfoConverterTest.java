package com.alliance.converter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.alliance.entity.AdditionalInfo;

@RunWith(MockitoJUnitRunner.class)
public class AdditionalInfoConverterTest {

    private static final String CONVERTED_INTEGERS = "01020405";
    private static final List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 4, 5));

    @InjectMocks
    private AdditionalInfoConverter converter;

    @Test
    public void shouldConvertIntegerListToString() {
        String actual = converter.convertToDatabaseColumn(new AdditionalInfo(integers));

        assertEquals(CONVERTED_INTEGERS, actual);
    }

    @Test
    public void shouldConvertStringToIntegerList() {
        AdditionalInfo actual = converter.convertToEntityAttribute(CONVERTED_INTEGERS);

        assertEquals(new AdditionalInfo(integers), actual);
    }

}
