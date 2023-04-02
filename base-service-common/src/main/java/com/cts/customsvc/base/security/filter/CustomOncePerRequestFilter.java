package com.cts.customsvc.base.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

	private List<String> excludeUrlPatterns = new ArrayList<String>(Arrays.asList("/swagger-ui.html", "/h2"));

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authroization = request.getHeader("Authorization");
		if (!StringUtils.equals(authroization, "test@2021")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
			log.info("==============Authorization Failed.Access Denied===================");
			return;
		} else {
			log.info("==============Authorization Success.Calling the API===================");
		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		if (excludeUrlPatterns.contains(path)) {
			return true;
		} else {
			return false;
		}
	}

}
