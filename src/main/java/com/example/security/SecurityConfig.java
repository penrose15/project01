package com.example.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config....................");
		
		http.authorizeHttpRequests()
			.antMatchers("/project01/home").permitAll()
			.antMatchers("/project01/register")
			.hasAnyRole("ADMIN");
		
		http
		.authorizeRequests()
		.mvcMatchers("/","/css/**","/scripts/**","/plugin/**","/fonts/**")
    	.permitAll();
			
		
		http
		.authorizeRequests().antMatchers("/login").permitAll()  //authorizeRequest로 access 제한 허용//antMatchers로 컨트롤할 url지정, permitAll()로 /login으로 접근하는 모든 사용자에 대해 접근을 허용
		.and()  //anyrequest() 의 경우 지정된 url이외 모든 url을 지정한다. 
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/dologin")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(new CustomLoginSuccessHandler())
		.permitAll();
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		http.rememberMe()
			.key("user")
			.userDetailsService(userService)
			.tokenRepository(getJDBCRepository())
			.tokenValiditySeconds(60*60*24);
			//.authenticationSuccessHandler(loginSuccessHandler());

	}
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/resources/**");
	}
//	@Bean
//	public LoginSuccessHandler loginSuccessHandler() {
//	LoginSuccessHandler handler = new LoginSuccessHandler();
//	handler.setDefaultTargetUrl("/main");
//	return handler;
//	}
	
	private PersistentTokenRepository getJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("build Auth Global..........");
		
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	


}
