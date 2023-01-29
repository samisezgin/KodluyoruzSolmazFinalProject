package com.samisezgin.finalproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/register**").permitAll()
                        .antMatchers("/", "/home").permitAll()

                        .antMatchers(HttpMethod.POST, "/voyages/**").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/voyages/**").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/voyages/**").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.GET, "/voyages").hasAnyAuthority("ADMIN", "USER")

                        .antMatchers(HttpMethod.GET, "/voyages/revenue/**").hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.GET, "/voyages/getSoldTicketCount/**").hasAnyAuthority("ADMIN")

                        .antMatchers("/tickets/**").hasAnyAuthority("ADMIN")

                        .antMatchers(HttpMethod.GET, "/bookings/**").hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST, "/bookings/**").hasAnyAuthority("ADMIN", "USER")
                        .antMatchers(HttpMethod.PUT, "/bookings/**").hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/bookings/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());


    }
}
