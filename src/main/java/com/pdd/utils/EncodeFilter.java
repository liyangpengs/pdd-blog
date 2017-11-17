package com.pdd.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class EncodeFilter
 */
public class EncodeFilter implements Filter {

	private String encode;
    /**
     * Default constructor. 
     */
    public EncodeFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * ±àÂë¹ýÂËÆ÷
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encode);
		response.setCharacterEncoding(this.encode);
		response.setContentType("text/html;charset="+this.encode);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.encode=fConfig.getInitParameter("encode");
	}
}
