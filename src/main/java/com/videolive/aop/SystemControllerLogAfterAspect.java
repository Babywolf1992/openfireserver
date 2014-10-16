package com.videolive.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Controller类的后置通知
 * @author mxr
 *ClassName SystemControllerLogAfterAspect 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright RSDSYST
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLogAfterAspect {
	 String description() default "";

}
