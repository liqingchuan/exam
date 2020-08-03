package web.system.role.entity;

import java.io.Serializable;
/**
 * 角色实体类
 * @author MI
 *
 */
public class RoleEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String role_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role_name=" + role_name + "]";
	}
	
}
