package com.servlet;


import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@Priority(1)
@WebFilter("/*")
public class RateLimitFilter implements Filter {
    private static final int REQUEST_LIMIT = 5;
    private static final long TIME_LIMIT_MS = 60000;
    private Queue<Long> requestTimes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        requestTimes = new LinkedList<>();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        long currentTime = System.currentTimeMillis();

        synchronized (requestTimes) {
            
            removeOld(currentTime);

            if (requestTimes.size() < REQUEST_LIMIT) {
                requestTimes.offer(currentTime);
                chain.doFilter(request, response);
            } else {
                httpResponse.setStatus(429);
                httpResponse.getWriter().write("oops!!. Try again later.");
            }
        }
    }

	private void removeOld(long currentTime) {
		while (!requestTimes.isEmpty() && currentTime - requestTimes.peek() > TIME_LIMIT_MS) {
		    requestTimes.poll();
		}
	}

    @Override
    public void destroy() {
        requestTimes.clear();
    }
}
