package id.user.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.user.base.constant.CommonConstant;
import id.user.base.constant.ResponseState;
import id.user.base.util.Md5Util;
import id.user.entity.Role;
import id.user.entity.User;
import id.user.model.RoleName;

@RequestMapping("/api/user")
@RestController
public class UserController extends BaseController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/signUp")
	public ResponseState signUp(@Valid @RequestBody User user) {
		ResponseState response = new ResponseState();
		Set<Role> roles = new HashSet<>();
		try {
			Role role = roleService.findOneByName(RoleName.ROLE_ADMIN);
			roles.add(role);
			user.setRoles(roles);
			user.setPassword(Md5Util.encode(user.getPassword()));
			user.setActive(true);
			
			Long id = userService.signUp(user);
			Long res = userService.generateNumber(id);
			userService.setEmployeNumber(id, res);
			
			response.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
		}
		
		return response;
	}
	
	@Secured({RoleName.ROLE_ADMIN})
	@PostMapping("/save")
	public ResponseState Save(@Valid @RequestBody User user) {
		ResponseState response = new ResponseState();
		Set<Role> roles = new HashSet<>();
		Role role = roleService.findOneByName(RoleName.ROLE_ADMIN);
		try {
			 if (user.getUserId() == null) {
				roles.add(role);
				user.setRoles(roles);
				user.setPassword(Md5Util.encode(user.getPassword()));
				user.setActive(true);
				
				Long id = userService.signUp(user);
				Long res = userService.generateNumber(id);
				userService.setEmployeNumber(id, res);
			 } else {
				User usr = userService.findOne(user.getUserId());
			 	if (usr != null) {
				    if (!usr.getPassword().equals(user.getPassword())) {
				        user.setPassword(Md5Util.encode(user.getPassword()));
				    }
				    roles.add(role);
				    user.setRoles(roles);
				    user.setEmployeeNumber(usr.getEmployeeNumber());
				    user.setActive(true);
				    userService.save(user);
			 	}
			 }				
			
			response.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
		}
		
		return response;
	}

	@Secured({RoleName.ROLE_ADMIN})
	@GetMapping("/activateUser")
	public ResponseState activateUser(@RequestParam(required = true) Long id, @RequestParam (required = true) boolean active) {
		ResponseState response = new ResponseState();

		try {				
			userService.activateUser(id, active);
			response.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
		}
		
		return response;
	}

	@Secured({RoleName.ROLE_ADMIN, RoleName.ROLE_USER})
	@GetMapping("/findByEmailOrEmployeeNumber")
	public User findUser(@RequestParam(required = false) String email, @RequestParam (required = false) Long id) {		
		return userService.findTop1ByUsernameOrEmployeeNumber(email, id);
	}
	
	@Secured({RoleName.ROLE_ADMIN, RoleName.ROLE_USER})
	@GetMapping("/findUserPaging")
    public Page<User> getAllPosts(Pageable pageable) {
        return userService.findUserPaging(pageable);
    }

	@Secured({RoleName.ROLE_ADMIN})
	@PostMapping("/updatePassword")
	public ResponseState Save(@RequestParam(required = true) Long id, @RequestParam(required = true) String password) {
		ResponseState response = new ResponseState();
		try {
			User user = userService.findOne(id);
			if (user != null) {
				user.setPassword(Md5Util.encode(password));
			    user.setActive(true);
			    userService.save(user);
				
				response.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
				response.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
			}
		    
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
			response.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
		}
		
		return response;
	}
}
