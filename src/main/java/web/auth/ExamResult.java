package web.auth;

import java.io.Serializable;

/**
 * 统一发送前端数据格式
 * @param status
 * @param msg
 * @param data<T>
 */
public class ExamResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int status;
	
	private String msg;
	
	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "status=" + status + ", msg=" + msg + ", data=" + data;
	}



	
	
}
