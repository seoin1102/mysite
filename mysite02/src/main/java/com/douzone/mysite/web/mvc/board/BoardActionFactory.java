package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeForm".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if("modifyForm".equals(actionName)) {
			action = new ModifyFormAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("guestIndex".equals(actionName)) {
			action = new GuestIndexAction();
		}  else {
			action = new IndexAction();
		}
		
		return action;
	}
}