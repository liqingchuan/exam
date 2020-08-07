package web.system.shijuan.entity;

import java.io.Serializable;
import java.util.Date;

public class ShijuanEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String km_id;
	
	private String km_name;
	
	private String sj_name;
	
	private Date start_time;
	
	private Date end_time;
	
	private Date createtime;

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

	public String getSj_name() {
		return sj_name;
	}

	public void setSj_name(String sj_name) {
		this.sj_name = sj_name;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	@Override
	public String toString() {
		return "ShijuanEntity [id=" + id + ", km_id=" + km_id + ", km_name=" + km_name + ", sj_name=" + sj_name
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", createtime=" + createtime + "]";
	}
	
	
	
	
}
