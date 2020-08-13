package web.system.shijuan.entity;

import java.io.Serializable;

public class StToSjEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sj_id;
	
	private String st_id;

	

	public StToSjEntity(String sj_id, String st_id) {
		this.sj_id = sj_id;
		this.st_id = st_id;
	}

	public String getSj_id() {
		return sj_id;
	}

	public void setSj_id(String sj_id) {
		this.sj_id = sj_id;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	@Override
	public String toString() {
		return "StToSjEntity [sj_id=" + sj_id + ", st_id=" + st_id + "]";
	}
	
	
}
