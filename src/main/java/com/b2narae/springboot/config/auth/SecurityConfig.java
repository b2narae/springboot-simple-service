/*
package config.auth에 Security 관련 클래스를 모두 담음
 */

package com.b2narae.springboot.config.auth;

import com.b2narae.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리 설정 옵션의 시작점
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // .anyRequest().authenticated : .antMatchers 이외 나머지 URL
                    .anyRequest().authenticated()

                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            // 소셜 로그인 성공후, 후속 조치를 진행할 UserService Interface의 구현체 등록
                            .userService(customOAuth2UserService);
    }
}
