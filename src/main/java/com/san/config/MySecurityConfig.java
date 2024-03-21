package com.san.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.san.jwt.JwtAuthenticationFilter;
import com.san.jwt.JwtAuthenticationEntryPoint;
import com.san.service.EmployeeDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class MySecurityConfig {
	private EmployeeDetailsService employeeDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jWTAuthenticationFilter;
	

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(AbstractHttpConfigurer::disable)
//				.cors(cors-> cors.disable())
//		        .httpBasic(Customizer.withDefaults())
//				.authorizeHttpRequests(requests -> requests
//	                      .requestMatchers("/admin/**").authenticated()
//	                      .requestMatchers("/emp/**").authenticated()
//	                      .requestMatchers("/**").authenticated()).build();
//	}
//	
	
	//for jwt
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/authenticate").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex ->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
	}


	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(employeeDetailsService);
		return dao;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
