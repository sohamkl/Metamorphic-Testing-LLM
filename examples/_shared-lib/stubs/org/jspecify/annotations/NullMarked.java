package org.jspecify.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.SOURCE) @Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD})
public @interface NullMarked {}
