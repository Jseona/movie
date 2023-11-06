package com.example.movie.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    //1. 암호의 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //2. 커스텀 로그인 설정(버전에 따라 사용하는 방법이 다르다.)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //매핑에 따른 접근권한 부여
        //antMatchers(매핑명,...).권한 : 정확히 일치하는 매핑만, antMatchers("/test") -> /test만 일치
        //mvcMatchers(매핑명,...).권한 : 비슷하게 일치하는 매핑, mvcMatchers("/test") -> /test, /test.html, /test.xyz 모두 가능
        //권한
        //permitAll() : 권한없음(로그인없이 사용가능), hasAnyRole("권한명")-해당권한만 부여
        http.authorizeHttpRequests((auth)->{
            auth.antMatchers("/", "/movie/list", "/movie/detail","/member/login","/member/register").permitAll();
            auth.antMatchers("/movie/detail", "/member/logout").hasAnyRole("USER","ADMIN");
            auth.antMatchers("/admin/list", "/admin/register", "/admin/detail",
                    "/admin/update","/admin/delete").hasAnyRole("ADMIN");
        });

        //로그인 처리에 대한 설정
        //loginPage(로그인 화면으로 사용할 페이지 매핑명)
        //defaultSuccessUrl(로그인 성공시 이동할 페이지 매핑명)
        //failureUrl(로그인 실패시 이동할 페이지 매핑명)
        //usernameParameter(username 으로 사용할 변수)
        http.formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/member/login/error");
        http.csrf().disable();  //페이지 변조방지를 사용 안함.

        //로그아웃에 대한 설정
        //logoutRequestMatcher(로그아웃 시 처리할 매핑명)
        //logoutSuccessUrl(로그아웃 시 이동할 페이지
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/");

        return http.build();
    }
}
