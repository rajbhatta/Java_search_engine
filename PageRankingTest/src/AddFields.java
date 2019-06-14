import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleDocValuesField;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import org.apache.uima.internal.util.StringUtils;
import org.apache.commons.*;

public class AddFields {
	public static void main(String[] args) throws IOException{
		  
		  String indexPath = "E:/irs notes/project/pagerank-sigmode-index/2";
		  String dataPath = "E:/irs notes/project/sigmode-index/2";
		  FieldType ft = new FieldType();
		
		  ft.setStored(true);
	      ft.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
	      
		  //create writer
		  Directory dir = FSDirectory.open(Paths.get(indexPath));
	      StandardAnalyzer analyzer = new StandardAnalyzer();
	      analyzer.setVersion(Version.LUCENE_6_2_0);
	      IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
	      iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
	      IndexWriter writer = new IndexWriter(dir, iwc);
	      
	      //create reader
	      Directory dir1 = FSDirectory.open(Paths.get(dataPath));
		  DirectoryReader reader = DirectoryReader.open(dir1);
	      
		  String path = "E:/irs notes/pagerank_sigmod_id.txt";
		  InputStreamReader read = new InputStreamReader(new FileInputStream(path));
          BufferedReader bufferedReader = new BufferedReader(read);
          String lineTxt = null;
		  HashMap<String,String> map = new HashMap<String,String>();
		  while ((lineTxt = bufferedReader.readLine()) != null)
          {
			  String[] splits = lineTxt.split("\t");
			  map.put(splits[0], splits[1]);
          }
		  System.out.println("limit"+reader.maxDoc());
		  for(int i = 0; i < reader.maxDoc(); i ++ )
		  {
			  Document doc = reader.document(i);
			  String docId = doc.get("id");
			  long docId1=Long.parseLong(docId,16);
			  Double d1;
			  
			 
			  if(map.get(docId1+"")==null)
				  d1=0.0;
			 else
				 d1=Double.parseDouble(map.get(docId1+""));
			  	 doc.add(new DoubleDocValuesField("pagerank",d1));
			  	 System.out.println("success");
			  	 writer.addDocument(doc);
		  }
	     writer.forceMerge(1);
	     writer.close();
	}

	
}
