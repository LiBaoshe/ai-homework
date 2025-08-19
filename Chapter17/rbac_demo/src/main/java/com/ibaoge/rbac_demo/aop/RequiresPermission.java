package com.ibaoge.rbac_demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// RequiresPermission.java
@Target(ElementType.METHOD) // 该注解用于方法上
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时有效
public @interface RequiresPermission {
    String value(); // 需要的权限字符串，如 "user:read"
}
