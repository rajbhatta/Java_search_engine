package cs.uwindsor.ir.index.search;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;


public class Search {
	public static void main(String[] args) throws Exception {
		String index = "E://ir notes//project/index";
		
		String searchValue="information"; //provide search parameter
				
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("contents", analyzer);
		Query query = parser.parse(searchValue);
		
		
		TopDocs results = searcher.search(query, 10000);
		System.out.println(results.totalHits + "TOTAL DOCUMENTS");
		for (int i = 0; i < results.totalHits; i++) 
		{
			
			Document doc = searcher.doc(results.scoreDocs[i].doc);				
			String filePath = doc.get("path");
			System.out.println((i + 1) + ". " + filePath);
			String title = doc.get("title");
		
			if (title != null)
			{
				 System.out.println("Title:" + title);
			}
			
			
			
		}
		reader.close();
		
	
	}
}