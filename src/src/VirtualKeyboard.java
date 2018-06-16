package src;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VirtualKeyboard 
{
	private static ILogger logger = new Logger();
	
	public static void main(String[] args) throws IOException, ParseException 
	{
		// TODO Auto-generated method stub
		
		String path = "C:\\Users\\surab\\eclipse-workspace\\VirtualKeyboard2\\testFiles\\testCase1.json";
		//String path = "C:\\Users\\abhip\\git\\VirtualKeyboard\\testCases\\test1.json";
		
		JSONParser parser = new JSONParser();
		
		JSONArray inputs = (JSONArray) parser.parse(new FileReader(path));
		
		for (Object obj : inputs)
		{
			JSONObject input = (JSONObject) obj;
			ProcessInput(input);
			
			System.out.println(input);
		}

        
        System.out.println("All Done!");
	}

	// JSONObject.put method doesn't support generics properly, hence suppress warnings
	@SuppressWarnings("unchecked")
	private static void ProcessInput(JSONObject input) 
	{
		//System.out.println(input);
		JSONArray keys = (JSONArray) input.get("alphabet");
		
		char[] keyChars = new char[keys.size()];
		
		for (int i = 0; i < keys.size(); i++)
		{
			String k = (String) keys.get(i);
			keyChars[i] = k.charAt(0);
		}
		
		long rl = (long) input.get("rowLength");
		int rowLength = (int) rl;
		
		KeyboardGraph kbGraph = new KeyboardGraph(keyChars, rowLength);
		
		String start = (String) input.get("startingFocus");
		String word = (String) input.get("word");
		
		StringBuilder path = new StringBuilder();
		int d = kbGraph.GetShortedPathForWord(start.charAt(0), word, path);
		
		input.put("distance", d);
		JSONArray pathArray = new JSONArray();
		
		for (int i = 0; i < path.length(); i++)
		{
			pathArray.add(String.valueOf(path.charAt(i)));
		}
		
		input.put("path", pathArray);
	}
}
