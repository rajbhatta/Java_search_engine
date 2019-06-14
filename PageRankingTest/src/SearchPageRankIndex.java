import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;


public class SearchPageRankIndex {

	public static void main(String[] args) throws Exception {
	
		String index = "E:/irs notes/project/pagerank-sigmode-index/2";
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse("information");
		TopDocs results = searcher.search(query, 10000);
		System.out.println(results.totalHits + " total matching documents");
		for (int i = 0; i < results.totalHits; i++) {
			Document doc = searcher.doc(results.scoreDocs[i].doc);
			
			//String path = doc.get("path");
			//System.out.println((i + 1) + ". " + path);
			
			String title = doc.get("title");
			if (title != null) {
				System.out.println("   Title: " + doc.get("title")+"---Page Rank is---"+doc.get("rank"));
				
			}
		}
		reader.close();
	}

}
