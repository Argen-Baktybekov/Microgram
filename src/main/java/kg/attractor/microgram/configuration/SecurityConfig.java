package kg.attractor.microgram.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private final DataSource dataSource;
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password,enabled "
                        + "from users "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select username,authority "
                        + "from authorities "
                        + "where username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/publications").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/publications/my").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/publications/forMe").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE,"/publications").fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/comment").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE,"/comment").fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/subscriptions").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE,"/subscriptions").fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/like").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE,"/like").fullyAuthenticated();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic();

        http.formLogin().disable().logout().disable();

        http.csrf().disable();
    }
}
