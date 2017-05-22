package com.faith.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName=invocation.getProxy().getActionName();
		if("admin_login".equals(actionName)){
            return invocation.invoke();
        }
		if(invocation.getInvocationContext().getSession().get("username")==null){
			return "login";
		}
		return invocation.invoke();
	}


}
