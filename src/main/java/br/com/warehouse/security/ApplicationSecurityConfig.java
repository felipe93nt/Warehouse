package br.com.warehouse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.List;

import static br.com.warehouse.security.ApplicationUserPermission.EMPLOYER_WRITE;
import static br.com.warehouse.security.ApplicationUserRole.EMPLOYEE;
import static br.com.warehouse.security.ApplicationUserRole.EMPLOYER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests()
			.antMatchers(POST,"/categories").hasAuthority(EMPLOYER_WRITE.getPermission())
			.antMatchers(PUT,"/categories").hasAuthority(EMPLOYER_WRITE.getPermission())
			.antMatchers(DELETE,"/categories").hasAuthority(EMPLOYER_WRITE.getPermission())
			.antMatchers(GET,"/categories").hasAnyRole(EMPLOYER.name(),EMPLOYEE.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		List<UserDetails> users = new ArrayList<>();
		UserDetails user =  User.builder()
				.username("Felipe")
				.password(passwordEncoder.encode("umasenha"))
				/*.roles(EMPLOYER.name())*/
				.authorities(EMPLOYER.getGrantedAuthorities())
				.build();
		users.add(user);
		user = User.builder()
				.username("Fulano")
				.password(passwordEncoder.encode("outrasenha"))
				/*.roles(EMPLOYEE.name())*/
				.authorities(EMPLOYEE.getGrantedAuthorities())
				.build();
		users.add(user);
		return new InMemoryUserDetailsManager(
				users
		);
	}
}
