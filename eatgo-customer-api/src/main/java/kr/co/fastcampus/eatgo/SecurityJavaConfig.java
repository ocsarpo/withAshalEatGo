package kr.co.fastcampus.eatgo;

import kr.co.fastcampus.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()           // RESTAPI에서는 불필요해서 disable
                .csrf().disable()           // RESTAPI에서는 불필요해서 disable
                .formLogin().disable()      // SpringSecurity 기본 제공 로그인폼 사용안함
                .headers().frameOptions().disable();        // h2-console 사용을 위해서 iFrame에 관한 보안설정 disable

    }

    // 컨트롤러에서는 Service를 IoC 컨테이너를 통해 의존성 주입 받아야하므로
    // PasswordEncoder가 IoC 컨테이너에 의해 관리될 수 있도록 @Bean 선언해줌
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }
}
