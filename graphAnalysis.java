package graphAnalysis;
import java.io.*;
import java.util.*;

public class graphAnalysis {

	public static boolean decayTrend (Double[] prices){
		
		double ratio = 0.0; 
		
		// finds the initial base ratio
		if ((ratio = prices[1]/prices[0]) < 1){
			
			double hiBuffer;
			double loBuffer;
			
			// adjusts hiBuffer so it remains under 1.0
			if (ratio >= 0.9){
				hiBuffer = 0.98;
				loBuffer = ratio-0.1;
			}
			
			// adjusts loBuffer so it remains above 0.0
			else if (ratio <= 0.1){
				loBuffer = 0.2;
				hiBuffer = ratio+0.1;
package graphAnalysis;
import java.io.*;
import java.util.*;

public class graphAnalysis2 {

	public static boolean decayTrend (Double[] prices){
		
		double ratio = 0.0; 
		
		// finds the initial base ratio
		if ((ratio = prices[1]/prices[0]) < 1){
			
			double hiBuffer;
			double loBuffer;
			
			// adjusts hiBuffer so it remains under 1.0
			if (ratio >= 0.9){
				hiBuffer = 0.98;
				loBuffer = ratio-0.1;
			}
			
			// adjusts loBuffer so it remains above 0.0
			else if (ratio <= 0.1){
				loBuffer = 0.2;
				hiBuffer = ratio+0.1;
			}
			
			// sets a margin of error of +- 0.1
			else {
				hiBuffer = ratio+0.1;
				loBuffer = ratio-0.1;
			}
		
			// checks the ratios between the remaining values to see if they follow the same trend 
			for (int i = 1; i < (prices.length-1); i++){
		
				if ((prices[i+1]/prices[i])>=hiBuffer || (prices[i+1]/prices[i])<=loBuffer){
					return false;
				}
			}
			
			return true;
		
		}
		
		else {
			return false;
		}
	
	}
	
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/graphAnalysis/src/graphAnalysis/prices.txt");
		
		FileReader readFile = new FileReader(file);
		BufferedReader read = new BufferedReader (readFile);
		
		String lineOfText = "";
		Double[] prices = new Double[4];
		
		while ((lineOfText = read.readLine()) != null){
			
			StringTokenizer s = new StringTokenizer(lineOfText);
			
			// creates array of the prices 
			int n = 0;
			
			// fills the array and parses strings to doubles
			while (s.hasMoreTokens()){		
				prices[n] = Double.parseDouble(s.nextToken());
				n++;	
			}
			
			// outputs 'yes' or 'no' depending on whether the data follows the trend 
			if (decayTrend(prices)){
				System.out.print("Yes\n");
			}
			
			else {
				System.out.print("No\n");
			}
			
		}
	
	}

}
				
------------------------------------------------------------------------------------------------------------------------
					
package graphAnalysis;
import java.io.*;
import java.util.*;

public class graphAnalysis2 {

	public static boolean decayTrend (Double[] prices){
		
		double ratio = 0.0; 
		
		// finds the initial base ratio
		if ((ratio = prices[1]/prices[0]) < 1){
			
			double hiBuffer;
			double loBuffer;
			
			// adjusts hiBuffer so it remains under 1.0
			if (ratio >= 0.9){
				hiBuffer = 0.98;
				loBuffer = ratio-0.1;
			}
			
			// adjusts loBuffer so it remains above 0.0
			else if (ratio <= 0.1){
				loBuffer = 0.2;
				hiBuffer = ratio+0.1;
			}
			
			// sets a margin of error of +- 0.1
			else {
				hiBuffer = ratio+0.1;
				loBuffer = ratio-0.1;
			}
		
			// checks the ratios between the remaining values to see if they follow the same trend 
			for (int i = 1; i < (prices.length-1); i++){
package graphAnalysis;
import java.io.*;
import java.util.*;

public class graphAnalysis2 {

	public static boolean decayTrend (Double[] prices){
		
		double ratio = 0.0; 
		
		// finds the initial base ratio
		if ((ratio = prices[1]/prices[0]) < 1){
			
			double hiBuffer;
			double loBuffer;
			
			// adjusts hiBuffer so it remains under 1.0
			if (ratio >= 0.9){
				hiBuffer = 0.98;
				loBuffer = ratio-0.1;
			}
			
			// adjusts loBuffer so it remains above 0.0
			else if (ratio <= 0.1){
				loBuffer = 0.2;
				hiBuffer = ratio+0.1;
			}
			
			// sets a margin of error of +- 0.1
			else {
				hiBuffer = ratio+0.1;
				loBuffer = ratio-0.1;
			}
		
			// checks the ratios between the remaining values to see if they follow the same trend 
			for (int i = 1; i < (prices.length-1); i++){
		
				if ((prices[i+1]/prices[i])>=hiBuffer || (prices[i+1]/prices[i])<=loBuffer){
					return false;
				}
			}
			
			return true;
		
		}
		
		else {
			return false;
		}
	
	}
	
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/graphAnalysis/src/graphAnalysis/prices.txt");
		
		FileReader readFile = new FileReader(file);
		BufferedReader read = new BufferedReader (readFile);
		
		String lineOfText = "";
		ArrayList<ArrayList<Double>> prices = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> temp = new ArrayList<Double>();
		StringTokenizer s;
		Double parseDouble;
		
		while ((lineOfText = read.readLine()) != null){
			
			s = new StringTokenizer(lineOfText);
			
			// fills the array and parses strings to doubles
			while (s.hasMoreTokens()){	
				parseDouble = Double.parseDouble(s.nextToken());
				temp.add(parseDouble);	
			}
			
			prices.add(temp);
			temp.clear();
			//n++;
		}
		
		
		for (int i = 0; i < prices.size(); i++){
			for (int j = 0; j < prices.get(0).size(); j++){
				System.out.println(prices.get(i).get(j) + " ");
			}
			System.out.println("\n");
		}
		
		
		
		
		
		
		
		/*// outputs 'yes' or 'no' depending on whether the data follows the trend
		if (decayTrend(prices)){
			System.out.print("Yes\n");
		}
		
		else {
			System.out.print("No\n");
		}*/
	
	}

}

