package com.ecolink.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecolink.spring.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected WebSecurityCustomizer getWebSecurityConfig() {
		return (web) -> web.ignoring().anyRequest();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.anyRequest().authenticated())
				.csrf(csrf -> csrf
						.ignoringRequestMatchers("/h2-console/**") // Deshabilitar CSRF para H2 Console
				)
				.headers(headers -> headers
						.frameOptions(frameOptions -> frameOptions.disable()) // Permitir frames para H2 Console
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