package src;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VirtualKeyboard 
{	
	public static void main(String[] args) throws IOException, ParseException 
	{		
		Path path;
		
		if (args.length < 1)
		{
			path = Paths.get("samples", "input1.json");
		}
		else
		{
			path = Paths.get(args[0]);
		}
		
		System.out.println("Input JSON file: " + path);
		
		JSONParser parser = new JSONParser();
		
		JSONArray inputs = (JSONArray) parser.parse(new FileReader(path.toFile()));
		
		for (Object obj : inputs)
		{
			JSONObject input = (JSONObject) obj;
			
			KeyboardObject keyboard = new KeyboardObject(input);
			
			ProcessInput(keyboard);
			
			System.out.println(keyboard.toString());
		}
	}

	private static void ProcessInput(KeyboardObject keyboard) 
	{	
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		
		StringBuilder path = new StringBuilder();
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		keyboard.setPath(path.toString());
		keyboard.setDistance(d);
	}
}
