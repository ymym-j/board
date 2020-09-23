package com.example.demo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	        http.authorizeRequests()
		    	        .antMatchers("/oauth/**").authenticated()
		                .anyRequest().permitAll()
    	            .and()
    	                .oauth2Login()
    	                .loginPage("/login")
    	                .defaultSuccessUrl("/oauth/login_success")
    	                .permitAll()
    	            .and()
    	                .logout()
    	                .logoutUrl("/oauth/logout")
    	                .logoutSuccessUrl("/login")
    	                .invalidateHttpSession(true);
	}
    
    @Resource
    private Environment env;	
    
    private static String CLIENT_PROPERTY_KEY= "spring.security.oauth2.client.registration.facebook.";

    private ClientRegistration getRegistration(){
       return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
        .clientId(env.getProperty(CLIENT_PROPERTY_KEY+"client-id")).clientSecret(env.getProperty(CLIENT_PROPERTY_KEY+"client-secret")).build();
    }
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(){
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(getRegistration());
    }
}