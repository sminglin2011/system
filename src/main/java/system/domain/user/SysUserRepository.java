package system.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long>{
	
	SysUser findByEmail(String email);

	SysUser findByUsernameOrEmail(String email, String username);
}
