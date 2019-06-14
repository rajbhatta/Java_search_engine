package ir.uwindsor.cs.results;


import ir.uwindsor.cs.models.PageRankObject;
import ir.uwindsor.cs.models.SearchResult;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.FSDirectory;

public class Search 
{
	
//		public ArrayList<SearchResult> search(String searchValue,HttpServletResponse response,String basePath) throws Exception 
//		{
//			    ArrayList<SearchResult> result = new ArrayList<SearchResult>();
//				String index = basePath + "/index/citeseer/index-file/";
//				IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
//				IndexSearcher searcher = new IndexSearcher(reader);
//				Analyzer analyzer = new StandardAnalyzer();
//				QueryParser parser = new QueryParser("contents", analyzer);
//				Query query = parser.parse(searchValue);
//				
//				
//				TopDocs results = searcher.search(query, 10000);
//				System.out.println(results.totalHits + "TOTAL DOCUMENTS");
//				for (int i = 0; i < results.totalHits; i++) 
//				{
//					
//					Document doc = searcher.doc(results.scoreDocs[i].doc);				
//					String filePath = doc.get("path");
//					System.out.println((i + 1) + ". " + filePath);
//					String title = doc.get("title");
//				
//					if (title != null)
//					{
//						// System.out.println("Title:" + doc.get("title"));
//					}
//					
//					result.add(new SearchResult(title, filePath));
//					
//				}
//				reader.close();
//				
//				return result;
//			}
//
////	public String manipulateFilePath(String filePath)
////	{
////		
////		String intermediateFilePath = filePath.replace("\\", "/");
////        String[] tempArray = intermediateFilePath.split("citeseer2/");
////        // System.out.println("[0]" + divided[0]);
////        // System.out.println("[1]" + divided[1]);
////        return tempArray[1];
////        
////	}
	
	

	public ArrayList<SearchResult> search(String keyword,HttpServletResponse servletResponse,String url) throws Exception 
	{
		 ArrayList<SearchResult> result = new ArrayList<SearchResult>();
		 String index = url + "/index/citeseer/index/";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser parser = new QueryParser("contents", analyzer);
			Query query = parser.parse(keyword);
			
			TopDocs results = searcher.search(query, 10000);
			System.out.println(results.totalHits + "TOTAL DOCUMENTS");
		
		
		 SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<span style=\"color:#5CB85C\"><b>","</b></span>");
		 SimpleFragmenter fragmenter = new SimpleFragmenter();
		 fragmenter.setFragmentSize(100);
		 Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
		 highlighter.setTextFragmenter(fragmenter);
		 String title=null;
		 String highlightText=null;
		 
		 
		 for (int i = 0; i < results.totalHits; i++) 
		 {
			Document doc = searcher.doc(results.scoreDocs[i].doc);
			title = doc.get("title");
		
			
			
			TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), results.scoreDocs[i].doc, "title",reader.document(results.scoreDocs[i].doc), analyzer);
			highlightText =  highlighter.getBestFragments(tokenStream, title, 2,"...");
			System.out.println(highlightText);
			
			result.add(new SearchResult(title,"defaultValue"));	
			
			System.out.println(title+"Added");
		}
		reader.close();
		
		
		return result;	
		
	}
	
}
