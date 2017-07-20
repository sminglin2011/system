package system.domain.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import system.domain.user.SysUser;

@Data
@Entity
public class SysRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "role_id", nullable = false)
	@GeneratedValue
	private Long roleId;
	@Column(nullable=false, unique=true)
	private String name;
	
	/**
	 * 一个角色有多个用户
	 */
//	@OneToMany(cascade={CascadeType.ALL})
//    @JoinColumn(name="role_id")
//	@OneToMany //只注释oneToMany会以关联表联系
	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	private Collection<SysUser> sysUsers = new ArrayList<SysUser>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Collection<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
