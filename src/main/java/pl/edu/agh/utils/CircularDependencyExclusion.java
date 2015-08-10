package pl.edu.agh.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CircularDependencyExclusion implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(GSONExclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
