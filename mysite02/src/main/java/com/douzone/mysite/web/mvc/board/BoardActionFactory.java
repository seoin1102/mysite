package com.douzone.mysite.web.mvc.board;


import com.douzone.mysite.web.mvc.guestbook.DeleteSuccess;
import com.douzone.mysite.web.mvc.guestbook.Deleteform;
import com.douzone.mysite.web.mvc.guestbook.Guestbookform;
import com.douzone.mysite.web.mvc.main.DefaultAction;
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
		}  else {
			action = new BoardFormAction();
		}
		
		return action;
	}
}