package com.classhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.classhub.filter.JWTRequestFilter;

@Configuration // Mandatory cls level anno to tell SC --
//following cls is equivalent to bean-config.xml
//going to containg bean configs : @Bean 
@EnableWebSecurity // to enable web sec support
@EnableGlobalMethodSecurity(prePostEnabled = true)
//to enable method level authorization rules
public class SpringSecConfig {
	// dep : password encoder
	@Autowired
	private PasswordEncoder enc;
	
	@Autowired
	private JWTRequestFilter jwtFilter;

	
	// configure another bean for authorization
	@Bean
	public SecurityFilterChain myAuthorization(HttpSecurity http) throws Exception {
		http.
		csrf().disable().
		authorizeHttpRequests() // simply tell spring sec , to authorize all reqs
				.antMatchers("/courses/view","/users/signin","/swagger*/**", "/v*/api-docs/**","/users/add","/admin/home").permitAll() // no authentication/ auth required
				.antMatchers("/students/registration").permitAll() // only customer can purchase
				.antMatchers("**/add","**/delete","**/update","/admin/home").hasRole("ADMIN") // only admin can add the products
				.anyRequest() // any other remaining end points
				.authenticated() // must be : must be
				.and() // bridge
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
