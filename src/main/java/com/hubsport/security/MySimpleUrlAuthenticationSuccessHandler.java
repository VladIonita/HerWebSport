package com.hubsport.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// this class redirects authentication to another page beside home page

	protected Log logger = LogFactory.getLog(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targeUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has allready been committed. Unable to redirect to" + targeUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targeUrl);
	}
	
	
	

	protected String determineTargetUrl(Authentication authentication) {

		boolean isUser = false;
		boolean isAdmin = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority: authorities) {
			if(grantedAuthority.getAuthority().equals("USER")) {
				isUser = true;
			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				 isAdmin = true;
				 break;
			}
		}
		if(isUser) {
			return "${pageContext.request.contextPath}/home";
		} else if (isAdmin) {
			return "${pageContext.request.contextPath}/dashboard";
		} else
		throw new IllegalStateException();
	}
	
	
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
