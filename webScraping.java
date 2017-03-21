import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class webScraping {
	
	public static ArrayList <String> getStockSymbols()
	{
		
		ArrayList<String> stockSymbols = new ArrayList<String>();
		
		File textFile = new File("/Users/MehtaUser/Documents/EclipseProjects2016/Stocks/symbols.txt");
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
	
	public static void main(String[]args) throws IOException
	{
		//Get all stocks to check 
		ArrayList<String> stockSymbols = new ArrayList<String>();
		stockSymbols = getStockSymbols(); 
		
		for(int i=0;i<stockSymbols.size();i++)
		{
			System.out.println("Symbol: "+ stockSymbols.get(i)); 
			String price = findPrice(getUrlSource(stockSymbols.get(i)));	
			
			System.out.println("Price: " + price);
			
			File textFile; 
			FileWriter out; 
			
			try 
			{
				textFile = new File("/Users/MehtaUser/Documents/EclipseProjects2016/Stocks/hourlyPrices.txt"); 
				out = new FileWriter(textFile, true); 
				BufferedWriter writeFile = new BufferedWriter(out);
				 
				writeFile.write(price + " ");
				
				writeFile.close();
				out.close();
			}
			
			catch(IOException e)
			{
				System.out.println("Problem Writing to File");
		        System.err.println("IOException: "+e.getMessage());
			}
			
		}
		
		
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
        String line = " ";
		URL website = new URL("http://www.nasdaq.com/symbol/"+url);
        URLConnection yc = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;

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
