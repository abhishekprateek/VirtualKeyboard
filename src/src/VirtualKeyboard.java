package src;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VirtualKeyboard 
{
	private static ILogger logger = new Logger();
	
	public static void main(String[] args) throws IOException, ParseException 
	{
		System.out.println("Hello2");
		// TODO Auto-generated method stub
		
		String path = "C:\\Users\\surab\\eclipse-workspace\\VirtualKeyboard2\\testFiles\\testCase1.json";
		
		JSONParser parser = new JSONParser();
		
		JSONArray inputs = (JSONArray) parser.parse(new FileReader(path));

        
        System.out.println(inputs);
		
        /*
		try (Scanner scanner = new Scanner( new File(path)))
		{
			String text = scanner.useDelimiter("\\A").next();
			
			for (String input : text.split(","))
			{
				input.replaceAll("[", "");
				input.replaceAll("]", "");
				
				System.out.println(text);
			}
			
			JSONObject obj = new JSONObject(text);
			
			System.out.println(text);
		}
		*/
	

	}
}
