package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.mysite.web.mvc.user.JoinAction;
import com.douzone.mysite.web.mvc.user.JoinFormAction;
import com.douzone.mysite.web.mvc.user.JoinSuccess;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("add".equals(actionName)) {
			action = new AddAction();
		} else if("deleteform".equals(actionName)) {
			action = new Deleteform();
		} else if("delete".equals(actionName)) {
			action = new DeleteSuccess();
		}else if("".equals(actionName)) {
			action = new Guestbookform();
		}  else {
			action = new DefaultAction();
		}
		
		return action;
	}

}
