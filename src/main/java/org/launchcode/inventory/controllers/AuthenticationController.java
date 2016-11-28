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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootHandler() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexHandler() {
		return "index";
	}
	
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
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String newUserForm() {
		return "createUser";
	}
	
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Model model) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
        // Perform some validation
        User existingUser = userDao.findByUsername(username);
        if (existingUser != null) {
            return this.displayError(
                    "The username " + username + " already exits in the system. Please select a different username", model);
        }
        else if (!password.equals(confirmPassword)) {
            return this.displayError("Passwords do not match. Try again.", model);
        }

        // Validation passed. Create and persist a new User entity
        User newUser = new User(username, password, firstName, lastName, role);
        userDao.save(newUser);

        return "createUser";
    }
}
