package web.system.question.entity;

import java.io.Serializable;

/**
 * 答案实体类
 */
public class DaanEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String st_id;
	
	private String da_name;
	
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public String getDa_name() {
		return da_name;
	}

	public void setDa_name(String da_name) {
		this.da_name = da_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DaanEntity [id=" + id + ", st_id=" + st_id + ", da_name=" + da_name + ", code=" + code + "]";
	}
	
	
	
	
	
}
