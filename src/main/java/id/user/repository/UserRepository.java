package id.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from User x where x.username = ?1 and x.isActive = true")
    User findOneByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update User r set r.employeeNumber = ?2 where r.userId = ?1")
	int updateEmployeeNumber(Long id, Long employeeNumber);
	
	@Transactional
	@Modifying
	@Query("update User r set r.isActive = ?2 where r.userId = ?1")
	int activateUser(Long id, boolean active);
	
	public User findTop1ByUsername(String username);
	
	public User findTop1ByUsernameOrEmployeeNumber(String username, Long employeeNumber);
	
	@Transactional
	@Modifying
	@Query("update User r set r.password = ?2 where r.userId = ?1")
	int updatePassword(Long id, String password);
	
}
