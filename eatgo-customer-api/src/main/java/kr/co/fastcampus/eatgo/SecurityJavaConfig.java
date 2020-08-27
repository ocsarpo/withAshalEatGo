package kr.co.fastcampus.eatgo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()           // RESTAPI에서는 불필요해서 disable
                .csrf().disable()           // RESTAPI에서는 불필요해서 disable
                .formLogin().disable()      // SpringSecurity 기본 제공 로그인폼 사용안함
                .headers().frameOptions().disable();        // h2-console 사용을 위해서 iFrame에 관한 보안설정 disable

    }
}
