import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class webScraping {
	
	public static void main(String[]args) throws IOException
	{
		//System.out.println(getUrlSource("aapl")); 
		System.out.println("Price: " + findPrice(getUrlSource("aapl")));
	}

	public static double findPrice(String line)
	{
		double num = 0.00; 
		String temp = "";
		
		for(int i=0;i<line.length();i++)
		{
			if (line.substring(i,i+1).equals(";"))
			{
				temp = line.substring(i+1,i+7);
				num = Double.parseDouble(temp); 
			}
		}
		
		return num; 
	}
	
	private static String getUrlSource(String url) throws IOException {
        int num = 0; 
        String line = "";
		URL website = new URL("http://www.nasdaq.com/symbol/"+url);
        URLConnection yc = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        //StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
        	num++; 
        	//a.append(inputLine);
        	
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
