package web.auth;

import java.io.Serializable;
import java.util.List;
/**
 * 用于分页查询
 * totalRecord：数据库中总的记录数
 * totalPage：总页数
 * pageNum：当前页为第几页
 * start:总共能显示5页，让用户进行点击，7为起始页
 * end：11为能显示的尾页，也就是，如果用户点击第8页，那么start就为6，end就为10，每次都只有5页共点击查询。
 * 
 * @author MI
 *
 */
public class Page<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//通过构造方法得来的
	private int pageNum;  //当前页，从前端请求传过来
	
	private int pageSize;  //每页显示的数据条数，这里也从前端传过来
	
	private int totalRecord;  //数据总条数，从数据库查出
	
	
	//需要计算得来的
	private int totalPage; //总页数，通过totalRecord和pageSize计算得来的
	
	//开始索引，也就是我们要在数据库中从第几行开始拿数据
	private int startIndex;
	
	private List<T> list; //将每页显示的数据放在此集合中
	
	//分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
	private int start;
	
	private int end;

	
	//通过pageSize和totalRecord来计算出totalPage和startIndex
	//构造方法中获取pageNum,pageSize,totalRecord
	public Page(int pageNum, int pageSize, int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		
		//totalPage 总页数
		if(totalRecord%pageSize==0) {
			//总条数整除每页数据条数，显示除法结果
			this.totalPage = totalRecord/pageSize;
		}else {
			//不能整除，显示结果加1
			this.totalPage = (totalRecord/pageSize)+1;
		}
		
		//startIndex 开始索引
		this.startIndex = (pageNum-1)*pageSize;
		
		//显示的页码数，这里定为5
		this.start = 1;
		this.end = 5;
		
		//如果总页数小于5，end等于总页数
		if(totalPage<=5) {
			this.end = totalPage;
		}else {
			//总页数大于5，根据当前页计算显示的页码
			this.start = pageNum-2;
			this.end = pageNum+2;
			
		}
		if(start<=0) {
			//当前页如果是第一页则不符合此规则
			this.start = 1;
			this.end = 5;
		}
		if(end>totalPage) {
			//当前页是最后2页则不符合此规则
			this.start = totalPage-5;
			this.end = totalPage;
		}
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRecord() {
		return totalRecord;
	}


	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	@Override
	public String toString() {
		return "Page [当前页=" + pageNum + ", 每页数据=" + pageSize + "条, 总共=" + totalRecord + "条, 总页数="
				+ totalPage + ", 开始条数=" + startIndex + ", 数据=" + list + ", 开始页=" + start + ", 结束页=" + end
				+ "]";
	}
	
}
