package kr.co.kmarket.security;


import kr.co.kmarket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;


@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private SecurityUserService service;
	@Autowired
	private AuthFailureHandler handler;



	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// 사이트 위변조 요청 방지
			.csrf().disable()
			
			// 인가(접근권한) 설정
			.authorizeHttpRequests(req -> req
					.mvcMatchers("/user/**", "/", "/auth", "/cs/**", "/admin/**", "/file/**", "/product/**").permitAll()
					.anyRequest().authenticated()
			)

			// 로그인 설정
			.formLogin(
				login->login
					.loginPage("/user/login").permitAll()
					.loginProcessingUrl("/user/login")
					.defaultSuccessUrl("/")
					.failureHandler(handler)
			)

			// 자동 로그인 설정
			.rememberMe( reme -> reme
					.userDetailsService(service)
					.tokenValiditySeconds(600)
			)

			// 로그인 아웃 설정
			.logout(logout->logout
					.logoutSuccessUrl("/user/login")
					.logoutSuccessHandler((req, resp, auth) -> {
						String url = req.getHeader("Referer");
						String contextPath = req.getContextPath();
						resp.sendRedirect(url == null ? contextPath + "/user/login":url);
					})
			)

		;
		return http.build();
	}

	@Bean
    public PasswordEncoder PasswordEncoder () {
		return new BCryptPasswordEncoder();
    }

	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> {
			   web.ignoring()
					   .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	   };
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails[] user = {
//				User.withDefaultPasswordEncoder()
//						.username("user")
//						.password("1234")
//						.roles("1")
//						.build()
//				,
//				User.withDefaultPasswordEncoder()
//						.username("seller")
//						.password("1234")
//						.roles("2")
//						.build()
//				,
//				User.withDefaultPasswordEncoder()
//						.username("admin")
//						.password("1234")
//						.roles("3")
//						.build()
//		};
//
//		return new InMemoryUserDetailsManager(user);
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**")
				.addResourceLocations(resourceLoader.getResource("file:file/"));
	}

}

