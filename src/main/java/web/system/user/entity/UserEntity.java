package web.system.user.entity;

import java.io.Serializable;
/**
 * 用户实体类
 * @author MI
 *
 */
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String username;
	
	private String nickname;

	private String role_id;
	
	private String role_name;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", nickname=" + nickname + ", role_id=" + role_id
				+ ", role_name=" + role_name + ", status=" + status + "]";
	}
	
	
	
}
