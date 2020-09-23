package com.example.demo.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class LoginController {
	
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
	
	@GetMapping({"/login","/"})
	public String login(Model model) {
	        return "/index";
	}
    
	@GetMapping("/oauth/login_success")
	public String loginSuccess(OAuth2AuthenticationToken authentication,
			HttpServletRequest request, Model model) {
		OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
	                        authentication.getAuthorizedClientRegistrationId(),
	                        authentication.getName());

		String userInfoEndpointUri = client.getClientRegistration()
	                .getProviderDetails().getUserInfoEndpoint().getUri();
 
	    if (!StringUtils.isEmpty(userInfoEndpointUri)) {
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	        ResponseEntity<Map> response = restTemplate.exchange(getUrl(authentication.getAuthorizedClientRegistrationId(), userInfoEndpointUri), HttpMethod.GET, entity, Map.class);
	        Map userAttributes = response.getBody();
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("userName", userAttributes.get("name"));
	        session.setAttribute("userId", userAttributes.get("id"));
        }
	    return "redirect:/oauth/board";
	}
	
	protected String getUrl(String site, String baseUrl){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        return uriBuilder.toUriString();
    }
}
