package com.b2narae.springboot.web;
import com.b2narae.springboot.config.auth.SecurityConfig;
import com.b2narae.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
@RunWith(SpringRunner.class)
JUnit 내장 실행자 대신 SpringRunner라는 스프링 실행자 사용
SpringBoot Test와 JUnit 사이의 연결자 역할

@WebMvcTest
Web(Spring MVC)에 집중할 수 있는 어노테이션
사용 가능 : @Controller, @ControllerAdvice
사용 불가 : @Service, @Component, @Repository
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }) // JPA 미지원
public class HelloControllerTest {

    // MockMvc : Web API 테스트시 사용
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello_must_be_returned() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 Http get 메소드 실행 가능
        // .andExpect를 통해 검증 가능
        // status().isOk() : 200인지 아닌지 검증
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto_must_be_returned() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                                .param("name", name) // API 테스트 때 사용될 요청 파라미터 설정
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
