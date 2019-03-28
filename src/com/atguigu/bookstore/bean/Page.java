package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 分页类
 * @author Administrator
 *
 * @param <T>
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查询分页图书集合的起始索引
	 * 	index = (pageNumber-1)*size
	 */
	private int index;
	/**
	 * 每页显示的分页记录条数
	 * 	我们自己设置的
	 */
	private int size;
	/**
	 * 分页页码
	 * 	用户访问分页数据时提交的
	 */
	private int pageNumber;
	/**
	 * 图书的总记录条数
	 * 	查询bs_book表记录总条数得到
	 */
	private int totalCount;
	/**
	 * 分页总页码
	 * 	totalCount/size
	 */
	private int totalPage;
	/**
	 * 分页需要显示的图书集合
	 * 	根据size和index查询得到
	 */
	private List<T>data;
	/**
	 * 分页使用的前段路径
	 * 	截取得到，分页导航栏需要使用
	 */
	private String path;
	public int getIndex() {
		//计算index并返回
		index = (getPageNumber()-1)*size;
		return index;
	}
	/*public void setIndex(int index) {
		this.index = index;
	}*/
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageNumber() {
		//避免pageNumber超过范围， 导致计算的index查询不到数据
		if(pageNumber<1) {
			pageNumber = 1;
		}else if(pageNumber> getTotalPage()) {
			pageNumber = getTotalPage();
		}
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 *  totalCount	size	totalPage
	 *  	10		3			4
	 *  	10		5			2
	 *  	10		11			1
	 *  	10		2			5
	 * 
	 * @return
	 */
	public int getTotalPage() {
		if(totalCount%size==0) {
			totalPage = totalCount/size;
		}else {
			totalPage = totalCount/size+1;
		}
		return totalPage;
	}
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Page(int index, int size, int pageNumber, int totalCount, int totalPage, List<T> data) {
		super();
		this.index = index;
		this.size = size;
		this.pageNumber = pageNumber;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.data = data;
	}
	public Page() {
		super();
	}
	@Override
	public String toString() {
		return "Page [index=" + index + ", size=" + size + ", pageNumber=" + pageNumber + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", data=" + data + "]";
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
