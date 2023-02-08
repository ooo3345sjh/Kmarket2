package kr.co.kmarket.security;


import kr.co.kmarket.vo.UserVO;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
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

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// 사이트 위변조 요청 방지
			.csrf().disable()
			
			// 인가(접근권한) 설정
			.authorizeHttpRequests(req ->
				req.anyRequest().authenticated()
			)

			// 로그인 설정
			.formLogin()

			// 로그인 아웃 설정
//			.logout()

		;
		return http.build();
	}

//	@Bean
//    public PasswordEncoder PasswordEncoder () {
//		return NoOpPasswordEncoder.getInstance();
//    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
//		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();


		UserDetails[] user = {
				User.withDefaultPasswordEncoder()
					.username("user")
					.password("1234")
					.roles("1")
					.build()
				,
				User.withDefaultPasswordEncoder()
					.username("seller")
					.password("1234")
					.roles("2")
					.build()
				,
				User.withDefaultPasswordEncoder()
					.username("admin")
					.password("1234")
					.roles("3")
					.build()
		};
		return new InMemoryUserDetailsManager(user);
	}

}
