package id.user.repository;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Set<Role> findByNameIn(String[] name);
	
	List<Role> findByStatusOrderByNameAsc(Integer status);
	
	Role findOneByName(String name);
	
	@Transactional
	@Modifying
	@Query("update Role r set r.status = ?1 where r.id = ?2")
	int updateStatusRole(Integer status, Long id);
}
