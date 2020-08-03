package web.system.menu.entity;

import java.io.Serializable;

/**
 * 菜单实体类
 * @author MI
 *
 */
public class MenuEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String cd_url;
	
	private String cd_name;
	
	private String parend_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCd_url() {
		return cd_url;
	}

	public void setCd_url(String cd_url) {
		this.cd_url = cd_url;
	}

	public String getCd_name() {
		return cd_name;
	}

	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}

	public String getParend_id() {
		return parend_id;
	}

	public void setParend_id(String parend_id) {
		this.parend_id = parend_id;
	}

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", cd_url=" + cd_url + ", cd_name=" + cd_name + ", parend_id=" + parend_id
				+ "]";
	}
	
	
	
}
