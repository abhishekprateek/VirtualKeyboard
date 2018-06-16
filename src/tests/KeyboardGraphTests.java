package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import src.KeyboardGraph;
import src.KeyboardObject;

class KeyboardGraphTests {
	
	private JSONParser parser = new JSONParser();

	@Test
	void GetShortedPathForWord_SampleInput1() 
	{
		// arrange:
		String input = "{\r\n" + 
				"\"alphabet\":[\"Q\", \"W\", \"E\", \"R\", \"T\", \"Y\", \"U\", \"I\", \"B\", \"P\", \"A\", \"S\"],\r\n" + 
				"\"rowLength\": 5,\r\n" + 
				"\"startingFocus\": \"B\",\r\n" + 
				"\"word\": \"BAR\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		
		StringBuilder path = new StringBuilder();
		
		// act:
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		// assert:
		assertEquals(6, d);
	}
	
	private KeyboardObject GetKeyboard(String input)
	{
		
		try 
		{
			JSONObject jsonObj = (JSONObject) parser.parse(input);
			KeyboardObject keyboard = new KeyboardObject(jsonObj);
			return keyboard;
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
