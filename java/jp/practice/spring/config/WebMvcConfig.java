package jp.practice.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Securityによるログインコントローラー
 * 
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * RequestMappingが「/login」
	 * 
	 * return が「login.html」
	 * 
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

}