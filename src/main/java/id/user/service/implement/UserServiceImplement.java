package id.user.service.implement;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.user.entity.User;
import id.user.service.UserService;

@Service
public class UserServiceImplement extends BaseServiceImplement implements UserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findTop1ByUsername(username);
    }

	@Override
	public void changePassword(Long id, String password) {
		userRepository.updatePassword(id, password);
	}

	@Override
	public void activateUser(Long id, boolean activate) {
		userRepository.activateUser(id, activate);
	}

	@Override
	public Long signUp(User user) {
		userRepository.save(user);
		userRepository.flush();
		return user.getUserId();
	}

	@Override
	public Long generateNumber(Long id) {
        String result = "";
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();

        String padded = String.format("%03d", id);
        result = year + padded;
        return Long.parseLong(result);
	}

	@Override
	public void setEmployeNumber(Long id, Long employeeNumber) {
		userRepository.updateEmployeeNumber(id, employeeNumber);
	}

	@Override
	public void save(User user) {
		 userRepository.save(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findTop1ByUsernameOrEmployeeNumber(String username, Long employeeNumber) {
		return userRepository.findTop1ByUsernameOrEmployeeNumber(username, employeeNumber);
	}

	@Override
	public Page<User> findUserPaging(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}

