package com.alliance.entity.type;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.alliance.type.SuperPower;

@RunWith(MockitoJUnitRunner.class)
public class SuperPowerTest {

    private static final Integer INVISIBLE = 2;
    private static final Integer INVALID_VALUE = 5;

    @Test
    public void shouldGetPowerByValue() {
        assertSame(SuperPower.INVISIBLE, SuperPower.of(INVISIBLE));
    }

    @Test
    public void shouldGetNullByInvalidValue() {
        assertNull(SuperPower.of(INVALID_VALUE));
    }

}
