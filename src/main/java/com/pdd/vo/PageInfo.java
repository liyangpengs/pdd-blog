package com.pdd.vo;

public class PageInfo {
	private long totalPages;
	private long counts;
	private long pageIndex;
	private long pageSize;
	public PageInfo() {
		super();
	}
	public PageInfo(long totalPages, long counts, long pageIndex, long pageSize) {
		super();
		this.totalPages = totalPages;
		this.counts = counts;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getCounts() {
		return counts;
	}
	public void setCounts(long counts) {
		this.counts = counts;
	}
	public long getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
}
