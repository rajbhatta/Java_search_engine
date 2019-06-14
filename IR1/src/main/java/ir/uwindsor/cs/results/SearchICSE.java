package ir.uwindsor.cs.results;

import ir.uwindsor.cs.models.ICSEObject;
import ir.uwindsor.cs.models.PageRankObject;

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
import java.util.Map.Entry;
import java.util.Set;

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

/**
 * @author raj-pc
 *
 */
public class SearchICSE 
{
	
	public ArrayList<ICSEObject> searchICSE(String keyword,HttpServletResponse servletResponse,String url) throws Exception 
	{
		ArrayList<ICSEObject> ret = new ArrayList<ICSEObject>();
		String index = url + "/index/icse/";
		int total;
		
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse(keyword);
		
		TopDocs results = searcher.search(query, 100000);
		System.out.println(results.totalHits + " total matching documents");
		total=results.totalHits;
		
		
		 HashMap<String,String> map = new HashMap<String,String>();
		 ArrayList out = new ArrayList();
		 SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<span style=\"color:#AC00FF\"><b>","</b></span>");
		 SimpleFragmenter fragmenter = new SimpleFragmenter();
		 fragmenter.setFragmentSize(100);
		 Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
		 highlighter.setTextFragmenter(fragmenter);
		 String title=null;
		 
		 
		 for (int i = 0; i < results.totalHits; i++) 
		 {
			ArrayList al = new ArrayList();
			Document doc = searcher.doc(results.scoreDocs[i].doc);
			String docid = doc.get("docid");
			title = doc.get("title");
			String year=doc.get("year");
			String date=doc.get("date");
			String conf=doc.get("conf");
			String pageRankValue=doc.get("pagerank");
			TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), results.scoreDocs[i].doc, "title",reader.document(results.scoreDocs[i].doc), analyzer);
			title =  highlighter.getBestFragments(tokenStream, title, 2,"...");
			map.put(title, pageRankValue);
			
			al.add(title);		
			al.add(year);
			al.add(date);
			al.add(conf);
			al.add(docid);
			out.add(al);				
		}
		reader.close();
		
		
		
		Map<String,String> result = sortByValues(map);
		Set mapSet = (Set) result.entrySet();
        //Create iterator on Set 
        Iterator mapIterator = mapSet.iterator();
        System.out.println("Display the key/value of HashMap.");
        while (mapIterator.hasNext()) 
        {
                Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                // getKey Method of HashMap access a key of map
                String keyValue = (String) mapEntry.getKey();
                ArrayList in=new ArrayList();
                String extra=null;
                String extra1=null;
                int count = 1;
                for(Iterator c = out.iterator(); c.hasNext();)
                {
                for(Iterator d = ((ArrayList)c.next()).iterator();
                d.hasNext();)
                {                    	
                	
                	extra=(String)d.next();
                	
                }
                
                count++;
                }
              
                String value = (String) mapEntry.getValue();
                ret.add(new ICSEObject(keyValue,value,total));
                System.out.println("Key : " + keyValue + "= Value : " + value);
                
        }
		return ret;	
		
	}
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map)
	{
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() 
        	{
            	public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(Map.Entry<K,V> entry: entries)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}


