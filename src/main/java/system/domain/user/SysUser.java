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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Column(nullable=false, unique=true)
	private String email;
	private String username;
	private String password;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joinDate;
	
	/**
     * @ManyToOne：多对一,cascade：级联
      * fetch = FetchType.LAZY,延迟加载策略,如果不想延迟加载可以用FetchType.EAGER
      * ManyToOne指定了多对一的关系，fetch=FetchType.LAZY属性表示在多的那一方通过延迟加载的方式加载对象(默认不是延迟加载)
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="role_id")
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
