package com.cafe24.mysite.action.user;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;
import com.cafe24.mysite.action.main.IndexAction;

public class UserActionFactory extends AbstractActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		// System.out.println(actionName);
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if("join".equals(actionName)) {
				action = new JoinAction();
		} else if("login".equals(actionName)) {
			action = new LoginAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("modifysuccess".equals(actionName)) {
			action = new ModifySuccessAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}
