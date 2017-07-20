package system.domain.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	@Column(nullable=false, unique=true)
	private String name;
	
	/**
	 * 一个角色有多个用户
	 */
	/**
     * @OneToMany 与 OneToOne相似的也用mappedBy
     */
	@OneToMany(mappedBy="role")//OneToMany指定了一对多的关系，mappedBy="role"指定了由多的那一方来维护关联关系，mappedBy指的是多的一方对1的这一方的依赖的属性，(注意：如果没有指定由谁来维护关联关系，则系统会给我们创建一张中间表)
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
