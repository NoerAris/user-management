package id.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import id.user.entity.User;


@Transactional
public interface UserService {

    public void changePassword(Long id, String password);
    
    public User findOne(Long id);
    
    public User getByUsername(String username);
    
    public User findTop1ByUsernameOrEmployeeNumber(String username, Long employeeNumber);

    public void activateUser(Long id, boolean activate);

    public Long signUp(User user);
    
    public Long generateNumber(Long id);
    
    public void setEmployeNumber(Long id, Long employeeNumber);
    
    public void save(User user);
    
    public Page<User>findUserPaging(Pageable pageable);
}
