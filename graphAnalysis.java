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
			}
			
			// sets a margin of error of +- 0.1
			else {
				hiBuffer = ratio+0.1;
				loBuffer = ratio-0.1;
			}
		
			// checks the ratios between the remaining values to see if they follow the same trend 
			for (int i = 1; i < 3; i++){
		
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
		StringTokenizer s = new StringTokenizer(read.readLine());
		
		// creates array of the prices 
		Double[] prices = new Double[4];
		int n = 0;
		
		// fills the array and parses strings to doubles
		while (s.hasMoreTokens()){		
			prices[n] = Double.parseDouble(s.nextToken());
			n++;	
		}
		
		// outputs 'yes' or 'no' depending on whether the data follows the trend 
		if (decayTrend(prices)){
			System.out.print("Yes");
		}
		
		else {
			System.out.print("No");
		}
	
	}

}
