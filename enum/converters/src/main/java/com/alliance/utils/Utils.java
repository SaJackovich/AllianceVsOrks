package com.alliance.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Utils {

    @Contract(pure = true)
    private Utils() {
        super();
    }

    public static <T> Class<T> getGenericParamClass(Class<?> genericClass) {
        ParameterizedType type = getConverterGenericSuperclass(genericClass);
        Type[] args = type.getActualTypeArguments();
        if (args.length != 0) {
            return (Class<T>) type.getActualTypeArguments()[0];
        }
        throw new IllegalArgumentException(String.format("Class [%s] should extend parent with defined generic types.",
                genericClass.getSimpleName()));
    }

    private static ParameterizedType getConverterGenericSuperclass(@NotNull Class<?> genericClass) {
        Type superclass = genericClass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            return (ParameterizedType) superclass;
        }
        throw new IllegalArgumentException(String.format("Class [%s] should extend parent with defined generic types.",
                genericClass.getSimpleName()));
    }

}
