package web.system.question.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.TypeConstants.DangerousMethod;

/**
 * 
 * 试题实体类
 * 
 */
public class ShitiEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String id;
	
	private String km_id;
	
	private String km_name;
	
	private String tm_name;
	
	private String st_lb;
	
	private Date create_time;

	private List<DaanEntity> dans;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKm_id() {
		return km_id;
	}

	public void setKm_id(String km_id) {
		this.km_id = km_id;
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	public String getTm_name() {
		return tm_name;
	}

	public void setTm_name(String tm_name) {
		this.tm_name = tm_name;
	}

	public String getSt_lb() {
		return st_lb;
	}

	public void setSt_lb(String st_lb) {
		this.st_lb = st_lb;
	}

	public List<DaanEntity> getDans() {
		return dans;
	}

	public void setDans(List<DaanEntity> dans) {
		this.dans = dans;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "ShitiEntity [id=" + id + ", km_id=" + km_id + ", km_name=" + km_name + ", tm_name=" + tm_name
				+ ", st_lb=" + st_lb + ", create_time=" + create_time + ", dans=" + dans + "]";
	}

	
}
