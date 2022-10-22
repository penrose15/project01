package java.com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import lombok.extern.java.Log;

import com.example.interceptor.LoginCheckInterceptor;

@Configuration
@Log
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/login");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	

}
