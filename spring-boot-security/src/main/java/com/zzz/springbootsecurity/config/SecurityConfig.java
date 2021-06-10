package com.zzz.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = this.passwordEncoder();

        //下面这两行配置表示在内存中配置了两个用户
//        auth.inMemoryAuthentication()
//                .withUser("javaboy").roles("admin").password(passwordEncoder.encode("123"))
//                .and()
//                .withUser("lisi").roles("user").password(passwordEncoder.encode("456"));
        auth.ldapAuthentication().
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()//开启登录配置
                .antMatchers("/hello").hasRole("admin")//表示访问 /hello 这个接口，需要具备 admin 这个角色
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
//                .loginPage("/login_p")
                //登录处理接口

//                .loginProcessingUrl("/doLogin")
//                //定义登录时，用户名的 key，默认为 username
//                .usernameParameter("uname")
//                //定义登录时，用户密码的 key，默认为 password
//                .passwordParameter("passwd")
                //登录成功的处理器
                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("success");
                    out.flush();
                })
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("fail");
                    out.flush();
                })
                .permitAll()//和表单登录相关的接口统统都直接通过
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("logout success");
                    out.flush();
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
