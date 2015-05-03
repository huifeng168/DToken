package com.connsec.web.component;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Grid 前端控件的组装类
 * 需要提供
 * 		1、当前页码 currentPage
 * 		2、每页显示记录数 pageResults
 * 		3、总记录数 recordsCount
 * 		4、记录的列表 List<T> rows
 * 		5、当前页记录数 total
 * @author Crystal.Sea
 *
 * @param <T>
 */
public  class Grid <T>{
	private final Log log = LogFactory.getLog(getClass());
	
	private int page=0;//当前页
	
	private int total=0;//当前页记录数
	
	private int totalPage=0;//总页数
	
	private Long records=0L;//总记录数
	
	private List<T> rows;//记录列表
	
	/**
	 * 
	 */
	public Grid() {
		log.debug("Grid.");
		
	}
	/**
	 * @param currentPage
	 * @param pageResults
	 * @param recordsCount
	 */
	public Grid(int currentPage,int pageResults,Long recordsCount) {
		pageCount(currentPage,pageResults, recordsCount);
		log.debug("Grid page : "+page+" , records : "+records+" , total : "+total);
	}
	/**
	 * 构造函数
	 * @param currentPage
	 * @param pageResults
	 * @param recordsCount
	 * @param rows
	 */
	public Grid(int currentPage,int pageResults,Long recordsCount,List<T> rows) {
		pageCount(currentPage,pageResults, recordsCount);
		this.rows=rows;
	}
	
	/**
	 * 构造函数
	 * @param currentPage
	 * @param pageResults
	 * @param recordsCount
	 * @param rows
	 */
	public Grid(int currentPage,int pageResults,Integer recordsCount,List<T> rows) {
		pageCount(currentPage,pageResults, recordsCount);
		this.rows=rows;
	}
	
	/**
	 * 构造函数
	 * @param currentPage
	 * @param pageResults
	 * @param totalPage
	 * @param recordsCount
	 * @param rows
	 */
	public Grid(int currentPage,int pageResults,int totalPage,Long recordsCount,List<T> rows) {
		pageCount(currentPage,pageResults, recordsCount);
		this.rows=rows;
		this.totalPage=totalPage;
	}
	
	/**
	 * 构造函数
	 * @param currentPage
	 * @param pageResults
	 * @param totalPage
	 * @param recordsCount
	 * @param rows
	 */
	public Grid(int currentPage,int pageResults,int totalPage,Integer recordsCount,List<T> rows) {
		pageCount(currentPage,pageResults, recordsCount);
		this.rows=rows;
		this.totalPage=totalPage;
	}
	
	/**
	 * 计算分页信息
	 * @param currentPage
	 * @param pageResults
	 * @param recordsCount
	 */
	public void pageCount(int currentPage,int pageResults,Long recordsCount){
		this.page=currentPage;
		//通过总记录数和每页显示记录数计算出当前页记录数
		this.total=(int) ((recordsCount%pageResults>0)?recordsCount/pageResults+1:recordsCount/pageResults);
		this.records=recordsCount;
	}
	
	/**
	 * 计算分页信息
	 * @param currentPage
	 * @param pageResults
	 * @param recordsCount
	 */
	public void pageCount(int currentPage,int pageResults,Integer recordsCount){
		this.page=currentPage;
		//通过总记录数和每页显示记录数计算出当前页记录数
		this.total=(int) ((recordsCount%pageResults>0)?recordsCount/pageResults+1:recordsCount/pageResults);
		this.records=Long.parseLong(recordsCount+"");
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @return the records
	 */
	public Long getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(Long records) {
		this.records = records;
	}
	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grid [log=" + log + ", page=" + page + ", total=" + total
				+ ", totalPage=" + totalPage + ", records=" + records
				+ ", rows=" + rows + "]";
	}
	


}
