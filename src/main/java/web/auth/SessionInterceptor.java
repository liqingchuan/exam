package web.auth;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import web.system.login.dao.UserLoginDao;

public class SessionInterceptor implements HandlerInterceptor{
	
	@Resource
	private UserLoginDao dao;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies==null) {
			response.sendRedirect("toLogin.do");
			return false;
		}else {
			for(Cookie c : cookies) {
				 if(dao.findById(c.getValue())==null) {
					 response.sendRedirect("toLogin.do");
					 return false;
				 }
			}
			
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
