
package tests;

import static org.junit.Assert.assertEquals;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import src.KeyboardGraph;
import src.KeyboardObject;

public class KeyboardGraphTests {
	
	private JSONParser parser = new JSONParser();
	
	public KeyboardGraphTests() 
	{
		
	}

	@Test
	public void GetShortedPathForWord_SampleInput1() 
	{
		// 1st sample input given in assignment
		String input = "{\r\n" + 
				"\"alphabet\":[\"Q\", \"W\", \"E\", \"R\", \"T\", \"Y\", \"U\", \"I\", \"B\", \"P\", \"A\", \"S\"],\r\n" + 
				"\"rowLength\": 5,\r\n" + 
				"\"startingFocus\": \"B\",\r\n" + 
				"\"word\": \"BAR\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		assertEquals(6, d);
		assertEquals(6 + 3, path.length());
	}
	
	@Test
	public void GetShortedPathForWord_SampleInput2() 
	{
		// 2nd sample input given in assignment
		String input = "{\r\n" + 
				"\"alphabet\":[\"R\", \"T\", \"Y\", \"A\", \"S\", \"D\", \"E\", \"U\", \"I\", \"O\", \"L\"],\r\n" + 
				"\"rowLength\": 3,\r\n" + 
				"\"startingFocus\": \"Y\",\r\n" + 
				"\"word\": \"TILT\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		assertEquals(6, d);
		assertEquals(6 + 4, path.length());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void GetShortedPathForWord_InvalidStartingFocus() 
	{
		// Starting focus is Z which is not present on the keyboard
		String input = "{\r\n" + 
				"\"alphabet\":[\"R\", \"T\", \"Y\", \"A\", \"S\", \"D\", \"E\", \"U\", \"I\", \"O\", \"L\"],\r\n" + 
				"\"rowLength\": 3,\r\n" + 
				"\"startingFocus\": \"Z\",\r\n" + 
				"\"word\": \"TILT\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void GetShortedPathForWord_InvalidKeyInWord() 
	{
		// Word contains letter Z which is not present on the keyboard
		String input = "{\r\n" + 
				"\"alphabet\":[\"R\", \"T\", \"Y\", \"A\", \"S\", \"D\", \"E\", \"U\", \"I\", \"O\", \"L\"],\r\n" + 
				"\"rowLength\": 3,\r\n" + 
				"\"startingFocus\": \"Y\",\r\n" + 
				"\"word\": \"TZLT\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
	
		kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
	}
	
	@Test
	public void GetShortedPathForWord_SingleLetterKeyboard() 
	{
		// 1st sample input given in assignment
		String input = "{\r\n" + 
				"\"alphabet\":[\"A\"],\r\n" + 
				"\"rowLength\": 1,\r\n" + 
				"\"startingFocus\": \"A\",\r\n" + 
				"\"word\": \"AAA\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		assertEquals(0, d);
		assertEquals(0 + 3, path.length());
	}
	
	@Test
	public void GetShortedPathForWord_SingleRowKeyboard() 
	{
		// rowLength = num keys. Meaning keyboard looks like:
		// R T Y A S D E U I O L
		String input = "{\r\n" + 
				"\"alphabet\":[\"R\", \"T\", \"Y\", \"A\", \"S\", \"D\", \"E\", \"U\", \"I\", \"O\", \"L\"],\r\n" + 
				"\"rowLength\": 11,\r\n" + 
				"\"startingFocus\": \"Y\",\r\n" + 
				"\"word\": \"TILT\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		assertEquals(9, d);
		assertEquals(9 + 4, path.length());
	}
	
	@Test
	public void GetShortedPathForWord_SingleColumnKeyboard() 
	{
		// rowLength is 1. Meaning keyboard looks like:
		// R
		// T
		// Y
		// and so on
		String input = "{\r\n" + 
				"\"alphabet\":[\"R\", \"T\", \"Y\", \"A\", \"S\", \"D\", \"E\", \"U\", \"I\", \"O\", \"L\"],\r\n" + 
				"\"rowLength\": 1,\r\n" + 
				"\"startingFocus\": \"Y\",\r\n" + 
				"\"word\": \"TILT\"\r\n" + 
				"}";
		
		KeyboardObject keyboard = GetKeyboard(input);
		KeyboardGraph kbGraph = new KeyboardGraph(keyboard.getAlphabets(), keyboard.getRowLength());
		StringBuilder path = new StringBuilder();
		
		int d = kbGraph.GetShortedPathForWord(keyboard.getStartingFocus(), keyboard.getWord(), path);
		
		assertEquals(9, d);
		assertEquals(9 + 4, path.length());
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
