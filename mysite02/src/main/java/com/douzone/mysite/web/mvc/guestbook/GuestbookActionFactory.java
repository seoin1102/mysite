package com.douzone.mysite.web.mvc.guestbook;
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
		}  else {
			action = new Guestbookform();
		}
		
		return action;
	}

}
