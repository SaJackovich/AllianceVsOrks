package com.alliance.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.hibernate.annotations.ValueGenerationType;

import com.alliance.annotation.realization.FunctionCreationValueGeneration;

@ValueGenerationType(generatedBy = FunctionCreationValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionCreationTimestamp {
}