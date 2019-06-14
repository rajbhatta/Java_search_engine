package ir.uwindsor.cs.models;

public class SearchResult
{
	private String resultTitle;
	private String resultPath;
	
	
	public SearchResult(String resultTitle, String resultPath) {
		this.resultTitle = resultTitle;
		this.resultPath = resultPath;
		
	}

	public String getResultTitle() {
		return resultTitle;
	}

	public void setResultTitle(String resultTitle) {
		this.resultTitle = resultTitle;
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}

}
