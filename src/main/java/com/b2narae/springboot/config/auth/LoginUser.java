package com.b2narae.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // annotation이 생성될 수 있는 위치 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   // LoginUser라는 이름의 어노테이션 생성
}