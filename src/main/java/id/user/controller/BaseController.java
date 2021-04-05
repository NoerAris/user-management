package id.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import id.user.service.RoleService;
import id.user.service.UserService;

@Controller
public class BaseController {

    @Autowired
    protected UserService userService;
    
    @Autowired
    protected RoleService roleService;

}
