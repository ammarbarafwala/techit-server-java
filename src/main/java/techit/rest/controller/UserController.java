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

<<<<<<< HEAD
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser( @PathVariable Long id )
    {
        return userDao.getUser( id );
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers()
    {
        return userDao.getUsers();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User addUser(@ModelAttribute("currentUser") User currentUser,@RequestBody User user )
    {
    	if(!currentUser.getPost().equals(User.Position.SYS_ADMIN))
    	{	
    		throw new RestException(401,"Unauthorized user");
    	}
    	
    	if( user.getUsername() == null || user.getPassword() == null )
            throw new RestException( 400, "Missing username and/or password." );

        return userDao.saveUser( user );
    }
=======
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long userId, @ModelAttribute("currentUser") User currentUser) {
		if (currentUser.getId() == userId || currentUser.getPost() == User.Position.SYS_ADMIN)
			return userDao.getUser(userId);
		throw new RestException(403, "Unauthorized Access");
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers(@ModelAttribute("currentUser") User currentUser) {
		if (currentUser.getPost() == User.Position.SYS_ADMIN)
			return userDao.getUsers();

		throw new RestException(403, "Unauthorized Access");
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		if (user.getUsername() == null || user.getPassword() == null)
			throw new RestException(400, "Missing username and/or password.");
		user.setHash(SecurityUtils.encodePassword(user.getPassword()));
		return userDao.saveUser(user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public User editUser(@PathVariable Long userId, @RequestBody User user,
			@ModelAttribute("currentUser") User currentUser) {

		if (currentUser.getId() != userId && currentUser.getPost() != User.Position.SYS_ADMIN)
			throw new RestException(403, "Unauthorized Access");

		if (user.getUsername() == null || user.getPassword() == null)
			throw new RestException(400, "Missing username and/or password.");

		user.setHash(SecurityUtils.encodePassword(user.getPassword()));
		return userDao.saveUser(user);
	}
>>>>>>> b6a1798f5d570f710beb2c2a6603b69783b13fad

}
