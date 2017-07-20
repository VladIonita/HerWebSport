package com.hubsport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = false)// to show logger
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

	@Autowired
	MySimpleUrlAuthenticationSuccessHandler successHandler;
	
//    @Autowired
//    PersistentTokenRepository tokenRepository;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
				.and().formLogin().loginPage("/loginPage")
				.failureUrl("/loginPage?error=true")
				.successHandler(successHandler).usernameParameter("user").passwordParameter("password")
//				.and()
//				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
//				.tokenValiditySeconds(86400)
//				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	
//    @Bean
//    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
//        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
//                "remember-me", userDetailsService, tokenRepository);
//        return tokenBasedservice;
//    }
//    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
//        return new AuthenticationTrustResolverImpl();
//    }
}
