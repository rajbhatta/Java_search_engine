package ir.uwindsor.cs.results;


import ir.uwindsor.cs.models.Input;
import ir.uwindsor.cs.models.NaiveResult;
import ir.uwindsor.cs.models.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NaiveTest 
{
	public NaiveResult computNaiveBayesResult(String keyword,HttpServletResponse servletResponse,String url,HttpServletRequest servletRequest, InputStream fileContent) throws Exception 
	{
		//ArrayList<SearchResult> result = new ArrayList<SearchResult>();
		 
		long startTime = System.currentTimeMillis();
		String docsPath = url + "/naive/word.csv";
		BufferedReader br = new BufferedReader(new FileReader(docsPath));
		double logprior[] = new double[2];
		
		String priorLine = br.readLine(); 
		String[] priorList = priorLine.split(",");
		logprior[0] = Double.parseDouble(priorList[1]);
		logprior[1] = Double.parseDouble(priorList[3]);
		
		double logCondProbZeroICSE = -12.153040998978367; // log value when there's no instance of this word in ICSE
		double logCondProbZeroVLDB = -11.33866731138583; // log value when there's no instance of this word in VLDB
		
		br.readLine(); // to remove 2nd line
		String sCurrentLine;
		ArrayList<Word> totalWord= new ArrayList<Word>();
		while ((sCurrentLine = br.readLine()) != null) {
			String list[] = sCurrentLine.split(",");
			Word temp = new Word(list[0],Integer.parseInt(list[1]));
			temp.setCountClassICSE(Integer.parseInt(list[2]));
			temp.setCountClassVLDB(Integer.parseInt(list[3]));
			temp.setCountDocClassICSE(Integer.parseInt(list[4]));
			temp.setCountDocClassVLDB(Integer.parseInt(list[5]));
			temp.setLogCondProbICSE(Double.parseDouble(list[6])); // double
			temp.setLogCondProbVLDB(Double.parseDouble(list[7])); // double
			if (temp.getCountClassICSE() == 0) {
				logCondProbZeroICSE = temp.getLogCondProbICSE();
			}
			if (temp.getCountClassVLDB() == 0) {
				logCondProbZeroVLDB = temp.getLogCondProbVLDB();
			}
			totalWord.add(temp);
		}
		
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileContent));
		bufferedReader.readLine(); // to remove 1st line
		String tCurrentLine;
		
		
		ArrayList<Input> inputDataList= new ArrayList<Input>();
		int countError = 0;
		int TN = 0;
		int TP = 0;
		int FN = 0;
		int FP = 0;
		int total = 0;
		
		
		while ((tCurrentLine = bufferedReader.readLine()) != null) {
			String list[] = tCurrentLine.split(",");
			Input inputData = new Input(list[0], list[1]);
			String wordList[] = list[0].split(" ");
			double scoreICSE = logprior[0];
			double scoreVLDB = logprior[1];
			for (int i = 0; i < wordList.length; i++) {
				int index = 0;
				if (!convertStringToInteger(wordList[i]) && !wordList[i].equals("")) { // dont use integer or blank
					while (index < totalWord.size()) {
						if(totalWord.get(index).getWord().equals(wordList[i])) { // found in model
							scoreICSE = scoreICSE + totalWord.get(index).getLogCondProbICSE();
							scoreVLDB = scoreVLDB + totalWord.get(index).getLogCondProbVLDB();
							break;
						} else {
							index++;						
						}
					}
				}
			}
			// get score of the title
			inputData.setScoreOnICSE(scoreICSE);
			inputData.setScoreOnVLDB(scoreVLDB);
			if(scoreICSE>scoreVLDB) {
				inputData.setPredictClass("icse");
			} else {
				inputData.setPredictClass("vldb");
			}
			if(!inputData.getRealClass().equals(inputData.getPredictClass())){
				countError++;
				if (inputData.getRealClass().equals("icse")) {
					FN++;
				} else {
					FP++;
				}
			}
			if(inputData.getRealClass().equals(inputData.getPredictClass())) {
				if (inputData.getRealClass().equals("icse")) {
					TN++;
				} else {
					TP++;
				}
			}
			total++;
			inputDataList.add(inputData);
		}
		
		PrintWriter writer = new PrintWriter(url+"/naive/result.csv", "UTF-8");
		writer.println("Heading,realClass,predictClass,scoreOnICSE,scoreOnVLDB");
		for (int i = 0; i < inputDataList.size(); i++) {
			writer.println(inputDataList.get(i).getStringTItle()+ "," + inputDataList.get(i).getRealClass() + "," + inputDataList.get(i).getPredictClass()
				   + "," + inputDataList.get(i).getScoreOnICSE() + "," + inputDataList.get(i).getScoreOnVLDB());
		}
		writer.close();
		
		NaiveResult nr=new NaiveResult();
		
		System.out.println("Total data count: " + total);
		System.out.println("Number of error: " + countError);
		double precision = TP/((TP+FP)*1.0);
		double recall = TP/((TP+FN)*1.0);
		System.out.println("Precision Value : " + precision);
		System.out.println("F1 Value:" + 2*((precision*recall)/(precision+recall)));
		System.out.println("Error : " + (double)countError/total);
		System.out.println("Accuracy Value: " + (1-(double)countError/total));
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println("Time to run: " +duration);
		
		nr.setTotalCount(total);
		nr.setError((double)countError/total);
		nr.setPrecision(precision);
		nr.setF1Value(2*((precision*recall)/(precision+recall)));
		nr.setAccuracy((1-(double)countError/total));
		
		return nr;
		
	}
	public static boolean convertStringToInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
		
	}
	
}
