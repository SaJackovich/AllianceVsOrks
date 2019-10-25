package com.alliance.annotation.realization;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

import com.alliance.annotation.FunctionCreationTimestamp;

public class FunctionCreationValueGeneration implements AnnotationValueGeneration<FunctionCreationTimestamp> {

    @Override
    public void initialize(FunctionCreationTimestamp annotation, Class<?> propertyType) {
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return null;
    }

    @Override
    public boolean referenceColumnInSql() {
        return true;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return "current_timestamp";
    }
}
