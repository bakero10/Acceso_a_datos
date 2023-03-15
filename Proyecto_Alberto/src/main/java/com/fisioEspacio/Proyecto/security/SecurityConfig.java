package com.fisioEspacio.Proyecto.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll() // permitimos acceso a la página de login
                .anyRequest().authenticated() // cualquier otra petición requiere autenticación
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/listar/pacientes")
                .permitAll()
                .and()
        	    .logout()
        	        .logoutUrl("/logout")
        	        .logoutSuccessUrl("/login?logout")
        	        .invalidateHttpSession(true)
        	        .deleteCookies("JSESSIONID").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication() // utilizamos autenticación en memoria
                .withUser("user")
                    .password("{noop}user") // la contraseña está en claro (no se codifica)
                    .roles("USER");
    }
}
