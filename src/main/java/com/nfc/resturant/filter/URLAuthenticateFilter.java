/**
JMathur
 */

package com.nfc.resturant.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nfc.resturant.constants.Constants;

public class URLAuthenticateFilter implements Filter, Constants {

	private final Log log = LogFactory.getLog(this.getClass());
	private static String LIST = "restrictedURL";

	private static Set<String> restrictedURL;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String urlList = filterConfig.getInitParameter(LIST);
		String[] uriArray = parseURIs(urlList);
		restrictedURL = getIgnoreList(uriArray);
		log.info("URL Filter Initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		String uri = httpRequest.getRequestURI();

		int ind = uri.lastIndexOf("/");

		String uriToken = uri.substring(ind + 1);
		String redirectURL=httpRequest.getContextPath()+REDIRECT_URL;

		if (session != null) {
			String token = httpRequest.getHeader(SESSION_TOKEN);

			if (restrictedURL != null) {
				if (restrictedURL.contains(uriToken)) {
					log.info("Authenticating URL: " + uri);
					if(token==null || !token.equals(session.getId())){
						httpResponse.sendRedirect(redirectURL);
						return;
					}
				}
			} else {
				log.info("Bypassing URL: " + uri);
			}
		}

		chain.doFilter(request, response);
		return;

	}

	@Override
	public void destroy() {
		log.info("URL authenticate destroyed");

	}

	private static Set<String> getIgnoreList(String[] uriArray) {
		Set<String> restrictedURL = new HashSet<String>(20);
		int urlCnt = uriArray.length;
		for (int i = 0; i < urlCnt; i++) {
			restrictedURL.add(uriArray[i].trim());
		}
		return restrictedURL;
	}

	private String[] parseURIs(String URIs) {
		URIs = URIs.replaceAll("\n", "");
		URIs = URIs.replaceAll("\t", "");

		return URIs.split(",");
	}

}
