/**
 * 
 */
package system.domain.user;

import java.io.Serializable;

/**
 * @author sming
 *
 */

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import system.domain.role.SysRole;
@Data
@Entity
public class SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SysUser() {
		super();
	}
	public SysUser(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String email;
	private String username;
	private String password;
	private Date joinDate;
	
//	@ManyToOne(cascade={ CascadeType.ALL })
//	@JoinColumn(name="role_id")
	private SysRole role;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
}
