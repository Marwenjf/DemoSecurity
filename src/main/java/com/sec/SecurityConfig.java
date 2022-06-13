package com.sec;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
	public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception {
	  /*auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","PROF");
	  auth.inMemoryAuthentication().withUser("prof1").password("{noop}1234").roles("PROF");
	  auth.inMemoryAuthentication().withUser("et1").password("{noop}1234").roles("ETUDIANT");
	  auth.inMemoryAuthentication().withUser("sco1").password("{noop}1234").roles("SCOLARITE");*/
      auth.jdbcAuthentication()
          .dataSource(dataSource)
          .passwordEncoder(NoOpPasswordEncoder.getInstance())
          .usersByUsernameQuery("Select username as principal,password as credentials,true from users where username = ?")
          .authoritiesByUsernameQuery("Select user_username as principal,roles_role as role from users_roles where user_username = ?")
          .rolePrefix("ROLE_");
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	    .csrf().disable()
    	    .authorizeRequests()
    	    .antMatchers("/css/**","/js/**","/images/**")
    	    .permitAll()
    			.anyRequest()
    				.authenticated()
    					.and()
    		.formLogin()
    			.loginPage("/login")
    			.failureUrl("/error.html")
    			.permitAll()
    			.defaultSuccessUrl("/index.html")
    			.and()
    		.logout()
    			.invalidateHttpSession(true)
    			.logoutUrl("/logout")
    			.permitAll();
    			
    }
}
