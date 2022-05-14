package cn.limitless.wingsmusic.config;

import cn.limitless.wingsmusic.exception.RestAuthenticationEntryPoint;
import cn.limitless.wingsmusic.filter.JwtAuthorizationFilter;
import cn.limitless.wingsmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final long EXPIRATION_TIME = 86400000;
    public static final String CREATE_TOKEN_URL = "/token/**";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET = "WingsMusic";
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(CREATE_TOKEN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(super.authenticationManager(), userService))
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger**/**")
                .antMatchers("/webjars/**")
                .antMatchers("/v3/**")
                .antMatchers("/user/**")
                .antMatchers("/doc.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

