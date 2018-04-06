package techit.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import techit.model.User;
import techit.model.dao.UserDao;
import techit.rest.error.RestException;
import techit.security.SecurityUtils;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long userId, @ModelAttribute("currentUser") User currentUser) {
		if (currentUser.getId() == userId || currentUser.getPost() == User.Position.SYS_ADMIN) {
			User user =  userDao.getUser(userId);
			if (user == null) throw new RestException(404, "User Not Found");
			return user;
		}
		throw new RestException(403, "Unauthorized Access");
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers(@ModelAttribute("currentUser") User currentUser) {
		if (currentUser.getPost() == User.Position.SYS_ADMIN)
			return userDao.getUsers();

		throw new RestException(403, "Unauthorized Access");
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addUser(@ModelAttribute("currentUser") User currentUser, @RequestBody User user) {
        if (currentUser.getPost() != User.Position.SYS_ADMIN)
            throw new RestException(403, "Forbidden");
		if (user.getUsername() == null || user.getPassword() == null || user.getUsername() == "" || user.getPassword() == "")
			throw new RestException(400, "Missing username and/or password.");
		user.setHash(SecurityUtils.encodePassword(user.getPassword()));
		return userDao.saveUser(user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public User editUser(@PathVariable Long userId, @RequestBody User user,
			@ModelAttribute("currentUser") User currentUser) {
	    User user2 =userDao.getUser(userId);
		if (currentUser.getId() != userId && currentUser.getPost() != User.Position.SYS_ADMIN)
			throw new RestException(403, "Unauthorized Access");

		if (user.getUsername() == null || user.getUsername() == "")
            throw new RestException(403, "Unauthorized Access");

		if( user.getPassword() == null)
		    user.setHash(user2.getHash());
		else
		    user.setHash(SecurityUtils.encodePassword(user.getPassword()));
		return userDao.saveUser(user);
	}


}
