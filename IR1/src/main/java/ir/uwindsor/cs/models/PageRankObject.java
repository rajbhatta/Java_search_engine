package ir.uwindsor.cs.models;

/**
 * @author raj-pc
 *
 */

public class PageRankObject 
{
	private String title;
	private String pageRankValue;
	private int resultsSize;
	
	
	public PageRankObject(String title, String pageRankValue, int resultsSize) 
	{
		this.title = title;
		this.pageRankValue = pageRankValue;
		this.resultsSize = resultsSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPageRankValue() {
		return pageRankValue;
	}
	public void setPageRankValue(String pageRankValue) {
		this.pageRankValue = pageRankValue;
	}
	public int getResultsSize() {
		return resultsSize;
	}
	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}
	
	
}
