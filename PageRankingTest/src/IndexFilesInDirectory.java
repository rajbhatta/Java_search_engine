
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class IndexFilesInDirectory {

	static int counter = 0;
	
	public static void main (String[] args) throws Exception {
			
		    String indexPath = "E:/irs notes/project/sigmode-index/2";
			//String docsPath = "E:/irs notes/project/2";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(dir, iwc);
			
			String path = "E:/irs notes/sigmod_id.txt";
			FileReader readSigmodSubGraph=new FileReader(path);
			BufferedReader myBufferedReaderFromDoc=new BufferedReader(readSigmodSubGraph);
			String line = null;
			ArrayList<String[]> edgesList= new ArrayList<String[]>();
			
			while ((line=myBufferedReaderFromDoc.readLine())!=null)
			{
				String[] split = line.split("\t");
				long id = Long.parseLong(split[0],16);
				System.out.println(id);
				String title = split[1];
					
				
				System.out.println("Split Value is"+split[2]);
				
				Document doc = new Document();
				//doc.add(new StringField("path", file.toString(), Field.Store.YES));
				doc.add(new TextField("id", id+"",Field.Store.YES));
				doc.add(new TextField("title", title,Field.Store.YES));
				doc.add(new StringField("title", title, Field.Store.YES));
				//edgesList.add(pairPair);
				writer.addDocument(doc);
			}
			
			
			//indexDocs(writer, Paths.get(docsPath));
			writer.close();
		}

		static void indexDocs(final IndexWriter writer, Path path) throws IOException {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() 
			{
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					indexDoc(writer, file);
					return FileVisitResult.CONTINUE;
				}
			});
		}

		/** Indexes a single document */
		static void indexDoc(IndexWriter writer, Path file) throws IOException {
			InputStream stream = Files.newInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			String title = br.readLine();
			Document doc = new Document();
			doc.add(new StringField("path", file.toString(), Field.Store.YES));
			doc.add(new TextField("contents", br));
			doc.add(new StringField("title", title, Field.Store.YES));
			writer.addDocument(doc);
			counter++;
			if (counter % 1000 == 0)
				System.out.println("indexing " + counter + "-th file " + file.getFileName());
			;
		}

}
