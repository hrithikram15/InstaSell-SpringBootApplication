package com.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.login.filter.JWTFilter;


@SpringBootApplication
@EnableFeignClients
public class LoginUserApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() 
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JWTFilter());
		filterRegistrationBean.addUrlPatterns("/login/home/*");
		return filterRegistrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(LoginUserApplication.class, args);
	}

}
