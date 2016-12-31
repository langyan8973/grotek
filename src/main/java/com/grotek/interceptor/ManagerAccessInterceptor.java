package com.grotek.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.grotek.pojo.Admin;
import com.grotek.pojo.Employee;
import com.grotek.service.AdminContext;
import com.grotek.service.AdminService;
import com.grotek.service.UserContext;
import com.grotek.service.WeekliesService;

public class ManagerAccessInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AdminContext userContext;
	
	@Autowired
	private AdminService adminService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		if(modelAndView!=null){
			Admin admin = userContext.getCurrentUser();
			admin = adminService.getById(admin.getId());
			modelAndView.addObject("role", admin.getRole()); 
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}
	
}
