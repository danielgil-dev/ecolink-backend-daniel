package com.ecolink.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ecolink.spring.security.jwt.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	//private final JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	protected BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// protected WebSecurityCustomizer getWebSecurityConfig() {
	// return (web) -> web.ignoring().anyRequest();
	// }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
						.requestMatchers("/h2-console/**","/swagger-ui/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/product", "/api/startup", "/api/product", "/api/post", "/api/ods").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/mission").hasAuthority("CLIENT")
						.requestMatchers(HttpMethod.GET, "/api/challenge").hasAnyAuthority("COMPANY","STARTUP")

						.anyRequest().authenticated())
						//.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
						.csrf(csrf -> csrf.disable()
						)
				.headers(headers -> headers
						.frameOptions(frameOptions -> frameOptions.disable())
							
				)
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/public") // Redirige a la página de inicio de sesión después del cierre de
														// sesión
						.invalidateHttpSession(true)
						.permitAll());
		return http.build();
	}

	@Bean
	protected AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(getPasswordEncoder());
		authProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(authProvider);
	}
}