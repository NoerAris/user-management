package id.user.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.user.repository.JwtTokenRepository;
import id.user.repository.RoleRepository;
import id.user.repository.UserRepository;

@Service
public class BaseServiceImplement {

    @Autowired
    protected UserRepository userRepository;
    
    @Autowired
    protected RoleRepository roleRepository;
    
    @Autowired
    protected JwtTokenRepository jwtTokenRepository;

  }
