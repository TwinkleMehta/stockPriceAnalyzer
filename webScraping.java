package graphAnalysis;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class webScrapingfinal {
	
	public static void main(String[]args) throws IOException
	{
		//Adds all stock symbols to array list stockSymbols
		ArrayList<String> stockSymbols = new ArrayList<String>();
		stockSymbols = getStockSymbols(); 
		
		File textFile; 
		FileWriter out;  
		textFile = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/graphAnalysis/src/graphAnalysis/prices.txt"); 
		out = new FileWriter(textFile, true); 
		BufferedWriter writeFile = new BufferedWriter(out);

		
		for(int i=0;i<stockSymbols.size();i++)
		{

			//finds real time price of stock
			String price = findPrice(getUrlSource(stockSymbols.get(i)));
			price.replace(" ", "");
			
			//outputs symbol and price in terminal
			System.out.print("Symbol: "+ stockSymbols.get(i) + " ");
			System.out.println(" Price: " + price);
			
			//writing hourly prices of stock to hourlyPrice.txt
			try 
			{			 
				writeFile.write(price + " ");
				
			}
			
			catch(IOException e)
			{
				System.out.println("Problem Writing to File");
		        System.err.println("IOException: "+e.getMessage());
			}
			
		}
		
		writeFile.write("\n");
		
		writeFile.close();
		out.close();
		System.out.print("done");
	}
	
	public static ArrayList <String> getStockSymbols()
	{
		//new array list of all stock symbols
		ArrayList<String> stockSymbols = new ArrayList<String>();
		
		//reads symbols.txt to retrieve all stock symbols to be analyzed
		File textFile = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/graphAnalysis/src/graphAnalysis/symbols.txt");
		FileReader in; 
		BufferedReader readFile; 
		String lineOfText = null; 
		
		try 
		{
			in = new FileReader(textFile); 
			readFile = new BufferedReader(in);

			while ((lineOfText = readFile.readLine()) != null)
			{
				stockSymbols.add(lineOfText); 		
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File does not exist or could not be found");
            System.err.println("FileNotFoundException: "+e.getMessage());
		}
		
		catch (IOException e)
		{
			System.out.println("Problem Reading File");
            System.err.println("IOException: "+e.getMessage());
		}
		
		return(stockSymbols);
		
	}
	
	public static String findPrice(String line)
	{
		int start = 0;  
		int end = 0;
		
		for(int i=0;i<line.length();i++)
		{
			if (line.substring(i,i+1).equals(";"))
			{
				start = i+1; 
				for(int a=start;a<line.length();a++)
				{
					if(line.substring(a,a+1).equals("<"))
					{
						end = a; 
						break;
					}
				}
				break; 
			}
		
		}
		
		String price = line.substring(start, end); 
		
		return price; 
	}
	
	private static String getUrlSource(String url) throws IOException {
        
		//accessing source code
		String line = " ";
		URL website = new URL("http://www.nasdaq.com/symbol/"+url);
        URLConnection yc = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;
        
        //searching source code for "LastSale"
        while ((inputLine = in.readLine()) != null)
        {
        	if(inputLine.contains("LastSale"))
        	{
        		line = inputLine; 
        		break; 
        	}
        }
            
        in.close();
        return line;
    }

}
