package id.user.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import id.user.entity.Role;

@Transactional
public interface RoleService {

	public Role findOneByName(String name);
	
	public List<Role> findByStatusOrderByNameAsc(Integer status);
	
	public Role findOne(Long id);
	
	public void save(Role role);
	
	public int updateStatusRole(Integer status, Long id);
}
