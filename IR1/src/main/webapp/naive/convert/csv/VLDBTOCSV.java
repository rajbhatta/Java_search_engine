import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 */

/**
 * @author mypc
 *
 */
public class VLDBTOCSV {
	public static void main(String[] args) throws IOException
	{
	
		String docsPath = "E:\\A\\naive\\data\\vldb_id.txt"; 
		BufferedReader br = new BufferedReader(new FileReader(docsPath));
		PrintWriter writer = new PrintWriter("E:\\A\\naive\\data\\VLDB_DATA.csv", "UTF-8");
		String readline;
		while ((readline = br.readLine()) != null) {
			readline = readline.replaceAll(",", ";");
			String list[] = readline.split("\\t");
			String completeList[] = new String[30];
			if (list.length > 11) {
				completeList[0] = list[0];
				String title = list[1];
				int count = 0;
				for (int i = 2; i < list.length; i++) {
					if (!convertStringToInterger(list[i])) {
						title = title + " " + list[i];
					} else {
						count = i;
						break;
					}
				}
				completeList[1] = title;
				for (int i = count; i < list.length; i++) {
					completeList[i-count+2] = list[i];
				}
			} else {
				completeList[0] = list[0];
				completeList[1] = list[1] + " " + list[2];
				for (int i = 3; i < list.length; i++) {
					completeList[i-1] = list[i];
				}
			}
			System.out.println("FinalList length:" + completeList.length);
			System.out.println("FinalList[1]:" + completeList[1]);
		    writer.println(completeList[1].replaceAll("[^A-Za-z0-9 ]", "") + "," + completeList[6]);
		}
		writer.close();
	}
	
	
	public static boolean convertStringToInterger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	    
	}

}
