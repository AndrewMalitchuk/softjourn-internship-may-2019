package com.practice.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String USERS_QUERY =
            "SELECT email,\n" +
                    "       password,\n" +
                    "       enabled\n" +
                    "FROM USER\n" +
                    "WHERE email=?";
    private final String ROLES_QUERY =
            "SELECT u.email,\n" +
                    "       r.role_name\n" +
                    "FROM USER u\n" +
                    "INNER JOIN user_role ur ON (u.id_user = ur.user_id)\n" +
                    "INNER JOIN ROLE r ON (ur.role_id=r.id_role)\n" +
                    "WHERE u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .and()
                .csrf().disable()
                .formLogin().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/bookByCategory").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAuthority("USER")

                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                        if (roles.contains("ADMIN")) {
                            redirectStrategy.sendRedirect(request, response, "/adminHome");
                        } else if (roles.contains("USER")) {
                            redirectStrategy.sendRedirect(request, response, "/userHome");
                        } else {

                        }
                    }
                })
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60)
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

}