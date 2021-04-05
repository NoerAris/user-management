package id.user.service.implement;

import java.util.List;
import org.springframework.stereotype.Service;

import id.user.entity.Role;
import id.user.service.RoleService;

@Service
public class RoleServiceImplement extends BaseServiceImplement implements RoleService{

	@Override
	public Role findOneByName(String name) {
		return roleRepository.findOneByName(name);
	}

	@Override
	public List<Role> findByStatusOrderByNameAsc(Integer status) {
		return roleRepository.findByStatusOrderByNameAsc(status);
	}

	@Override
	public Role findOne(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public int updateStatusRole(Integer status, Long id) {
		return roleRepository.updateStatusRole(status, id);
	}
}
