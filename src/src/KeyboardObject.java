package src;

import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class KeyboardObject 
{
	private JSONObject input;
	private char[] alphabets;
	private int rowLength;
	private char startingFocus;
	private String word;
	
	private int distance;
	private String path;
	
	
	public KeyboardObject(JSONObject input)
	{
		this.input = input;
				
		JSONArray keys = (JSONArray) input.get("alphabet");
		this.alphabets = new char[keys.size()];
		for (int i = 0; i < keys.size(); i++)
		{
			String k = (String) keys.get(i);
			alphabets[i] = k.charAt(0);
		}
		
		long rl = (long) input.get("rowLength");
		this.rowLength = (int) rl;
		
		String start = (String) input.get("startingFocus");
		this.startingFocus = start.charAt(0);
		
		this.word = (String) input.get("word");
	}

	public char[] getAlphabets() {
		return alphabets;
	}

	public int getRowLength() {
		return rowLength;
	}

	public char getStartingFocus() {
		return startingFocus;
	}

	public String getWord() {
		return word;
	}

	public int getDistance() {
		return distance;
	}

	public String getPath() {
		return path;
	}

	// JSONObject.put method doesn't support generics properly, hence suppress warnings
	@SuppressWarnings("unchecked")
	public void setDistance(int distance) {
		this.distance = distance;
		input.put("distance", distance);
	}

	// JSONObject.put method doesn't support generics properly, hence suppress warnings
	@SuppressWarnings("unchecked")
	public void setPath(String path) {
		
		this.path = path;
		
		JSONArray pathArray = new JSONArray();
		for (int i = 0; i < path.length(); i++)
		{
			pathArray.add(String.valueOf(path.charAt(i)));
		}
		
		input.put("path", pathArray);
	}

	@Override
	public String toString() 
	{
		return input.toJSONString();
	}
}
