package org.launchcode.inventory.controllers;

import org.launchcode.inventory.models.User;
import org.launchcode.inventory.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    @Autowired
    protected UserDao userDao;

    private static final String errorTemplate = "error";
    private static final String errorMessageIdentifier = "message";

    public static final String userSessionKey = "user_id";

    public String displayError(String message, Model model) {
        model.addAttribute(errorMessageIdentifier, message);
        return errorTemplate;
    }

    protected User getUserFromSession(HttpServletRequest request){
        int userId = (int) request.getSession().getAttribute(userSessionKey);
        return userDao.findByUid(userId);
    }
    
    protected void setUserInSession(HttpSession session, User user) {
    	session.setAttribute(userSessionKey, user.getUid());
    }

}
