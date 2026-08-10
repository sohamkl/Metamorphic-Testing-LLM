package org.jspecify.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.SOURCE) @Target({ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface Nullable {}
