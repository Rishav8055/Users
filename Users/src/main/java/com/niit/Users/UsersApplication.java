package com.niit.Users;

import com.niit.Users.Filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/userservice/*");
		return filterRegistrationBean;
	}

}
