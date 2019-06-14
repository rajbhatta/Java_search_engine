
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.document.DoubleDocValuesField;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.BytesRef;

public class CalculatePageRank 
{
public static void main(String[] args) throws IOException{
		
		String path = "E:/irs notes/sigmod_subgraph.txt";
		FileReader readSigmodSubGraph=new FileReader(path);
		BufferedReader myBufferedReaderFromDoc=new BufferedReader(readSigmodSubGraph);
		String line = null;
		ArrayList<String[]> edgesList= new ArrayList<String[]>();
		while ((line=myBufferedReaderFromDoc.readLine())!=null)
		{
			String[] pairPair = line.split("\t");
			edgesList.add(pairPair);
		}
		
		calcPageRank(edgesList);
		
	}
	
	public static void calcPageRank(ArrayList<String[]> edges) throws IOException{
				
				int Edge=0;
				HashSet<String> set = new HashSet<String>();
				for(int in = 0; in < edges.size(); in ++ )
				{
					set.add(edges.get(in)[0]);
					set.add(edges.get(in)[1]);
				}
				
				
				//number o f nodes
		        int NodesNum=set.size();
		       System.out.println(NodesNum);
		       //System.exit(0);
		        
		        
		        double[][] adjacentMatrix=new double[NodesNum][NodesNum];
				//String[] Nodes=new String[NodesNum];
				
				ArrayList<String> nodelist = new ArrayList<String>();
				nodelist.addAll(set);
				
				
				
				System.out.println("******************************************"+String.valueOf(NodesNum));
				//myFileReader1.close();
			    //initiate adjacent matrix to 0
				 for (int m=0;m<NodesNum;m++){
				    	for (int n=0;n<NodesNum;n++){
				    		adjacentMatrix[m][n]=0.0;
				    		}
					    		
				 }
				//construct the adjcent matrix
				//FileReader myFileReader2=new FileReader("d:/pagerank/ss.txt");
				//FileReader myFileReader2=new FileReader("d:/pagerank/adjcenttable.txt");
				//BufferedReader myBufferedReader3=new BufferedReader(myFileReader2);
				String myStringNew1=null;
				//while ((myStringNew1=myBufferedReader3.readLine())!=null) {
				for(int i = 0; i < edges.size(); i ++ ){
					String e1=edges.get(i)[0];
					String e2=edges.get(i)[1];
					int RowIndex,ColIndex = 0;
					RowIndex = nodelist.indexOf(e1);
					ColIndex = nodelist.indexOf(e2);
					adjacentMatrix[RowIndex][ColIndex]=1.0;
				}
				   
				
		     
			   double[] outDegree=new double[NodesNum];
			   //deal with the dead end
			   for (int m=0;m<NodesNum;m++){
				    	for (int n=0;n<NodesNum;n++){
				    		//AdjcentMatrix[m][n]="0";
				    		outDegree[m]=outDegree[m]+adjacentMatrix[m][n];
				    			       
				    		System.out.print(adjacentMatrix[m][n]+" ");
				    	}
				    	// if it is a zero vector, set the value to 1/n
					    if (outDegree[m]<0.0000001){
					      	for(int r=0;r<NodesNum;r++){
					      		adjacentMatrix[m][r]=1.0/NodesNum;
					       		System.out.print(adjacentMatrix[m][r]+" ");
					      	}
					      	outDegree[m]=1.0;
					    }
					    
				    	System.out.println("\r\n");
				    }
			   
			   double[][] pMatrix=new double[NodesNum][NodesNum];
			   for (int m=0;m<NodesNum;m++){
			    	for (int n=0;n<NodesNum;n++){
			    		//AdjcentMatrix[m][n]="0";
			    		double p=0.90*adjacentMatrix[m][n]/outDegree[m]+0.10/NodesNum;
				        pMatrix[m][n]=p;
			    		System.out.printf("%7.5f ",p);
			    	}
			    	System.out.println("\r\n");
			    }
			
			   
			// Use the power method to compute page ranks. 
			   int trials=50;//iteration times
		       double[] rank = new double[NodesNum]; 
		       rank[0] = 1.0; 
		       for (int t = 0; t < trials; t++) {

		           // Compute effect of next move on page ranks. 
		           double[] newRank = new double[NodesNum]; 
		           for (int j = 0; j < NodesNum; j++) {
		               //  New rank of page j is dot product of old ranks and column j of p[][]. 
		               for (int k = 0; k < NodesNum; k++) 
		                  newRank[j] += rank[k]*pMatrix[k][j]; 
		           } 

		           // Update page ranks.
		           rank = newRank;
		       } 

		       
		       // print page ranks
		       for (int ip = 0; ip < NodesNum; ip++) 
		       {
		    	   System.out.printf("%8.5f", rank[ip]);  
		       }
		       System.out.println();

		       System.out.println(); 
		       // print page ranks
		       FileWriter fw = new FileWriter(new File("E:/irs notes/pagerank_sigmod_id.txt"));
		       for (int ip = 0; ip < NodesNum; ip++) {
		    	   //System.out.printf("%2d  %5.4f\t", ip, rank[ip]);  
		    	   System.out.println(nodelist.get(ip)+"\t"+rank[ip]);
		    	   fw.write(nodelist.get(ip)+"\t"+rank[ip]+"\n");
		       }
		       
		       
			   fw.close();
				//myFileReader2.close();
				//resultFile.close();
			
				
	}

}
