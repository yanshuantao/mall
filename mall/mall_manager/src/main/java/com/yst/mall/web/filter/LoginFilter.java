package com.yst.mall.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yst.mall.model.SysUser;

/**
 * Servlet implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String servletPath=req.getServletPath();
		if(!servletPath.contains("login.action")&&!servletPath.contains("login.html")&&!servletPath.contains("loginDo.action")){
			if(servletPath.contains(".jsp")||servletPath.contains(".action")){
				HttpSession session = req.getSession();
				SysUser userInfo = (SysUser) session.getAttribute("userInfo");
				if(userInfo!=null){
					filterChain.doFilter(request, response);
				}else{
					String contextPath=req.getContextPath();
					resp.sendRedirect(contextPath+"/");
				}
			}else{
				filterChain.doFilter(request, response);
			}
		}else{
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}