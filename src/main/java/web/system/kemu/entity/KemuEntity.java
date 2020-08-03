package web.system.kemu.entity;

import java.io.Serializable;

public class KemuEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String km_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	@Override
	public String toString() {
		return "KemuEntity [id=" + id + ", km_name=" + km_name + "]";
	}
	
}
