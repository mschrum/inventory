package org.launchcode.inventory.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.inventory.models.User;
import org.launchcode.inventory.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@Autowired
	private  UserDao userDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		//get parameters from request
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//get user by their Username
		User user= userDao.findByUsername(username);
		
		//check password is correct pass error if incorrect
		if(!user.isMatchingPassword(password)){
			model.addAttribute("error", "Invalid password");
			return "login";
		}
		
		//log them in, if so use setUserInSession(HttpSession session, User user)
		HttpSession thisSession=request.getSession();
		setUserInSession(thisSession, user);
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/login";
	}
}
