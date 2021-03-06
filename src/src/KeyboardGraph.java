package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class KeyboardGraph 
{
	public final char PressChar = 'p';
	public final char LeftChar = 'l';
	public final char RightChar = 'r';
	public final char UpChar = 'u';
	public final char DownChar = 'd';
	
	private Map<Character, GraphNode> nodeMap;
		
	private char[] keys;
	private int rowLength;
	private int minRowLength;
	private int minRows;
	private int maxRows;
		
	public KeyboardGraph(char[] keys, int rowLength) 
	{
		this.nodeMap = new HashMap<>();
		this.keys = keys;
		this.rowLength = rowLength;
		this.minRowLength = keys.length % rowLength == 0 ? rowLength : keys.length % rowLength;
		this.minRows = keys.length / rowLength;
		this.maxRows = keys.length % rowLength == 0 ? minRows : minRows + 1;
				
		for (char key : keys)
		{
			GraphNode n = new GraphNode(key);
			nodeMap.put(key, n);
		}
		
		InitializeNeighbors();
	}
	
	public int GetShortedPathForWord(char start, String word, StringBuilder path)
	{
		if (word == null)
		{
			return -1;
		}
		
		if (word.length() == 0)
		{
			return -1;
		}
		
		if (!nodeMap.containsKey(start))
		{
			throw new IllegalArgumentException("Invalid start key: " + start);
		}
		
		for (int i = 0; i < word.length(); i++)
		{
			if (!nodeMap.containsKey(word.charAt(i)))
			{
				throw new IllegalArgumentException("Invalid character '" + word.charAt(i) + "' in word: " + word);
			}
		}
				
		char prevKey = start;
		int distance = 0;
		
		for (int i = 0; i < word.length(); i++)
		{
			char curKey = word.charAt(i);
			
			if (prevKey == curKey)
			{
				path.append(PressChar);
			}
			else
			{
				String subPath = DoBFS(prevKey, curKey);
				distance += subPath.length();
				
				path.append(subPath);
				path.append(PressChar);
				prevKey = curKey;
			}
		}
		
		return distance;
	}	
	
	@Override
	public String toString() {
		return "KeyboardGraph [nodeMap=" + nodeMap + "]";
	}
	
	private String DoBFS(char start, char end)
	{
        Queue<GraphNode> nextNodes = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        
        GraphNode startNode = nodeMap.get(start);
        
        nextNodes.add(startNode);
        visited.add(startNode.key);
                
        Map<String, String> shortestPathMap = new HashMap<>();
        shortestPathMap.put(String.valueOf(start) + start, "");
        
        while (!nextNodes.isEmpty())
        {
        	GraphNode n = nextNodes.remove();
        	
        	if (n.key == end)
        	{
        		break;
        	}
        	
        	ProcessNeighbor(start, n, n.left, LeftChar, visited, nextNodes, shortestPathMap);
        	ProcessNeighbor(start, n, n.right, RightChar, visited, nextNodes, shortestPathMap);
        	ProcessNeighbor(start, n, n.above, UpChar, visited, nextNodes, shortestPathMap);
        	ProcessNeighbor(start, n, n.below, DownChar, visited, nextNodes, shortestPathMap);
        }
        
        return shortestPathMap.get(String.valueOf(start) + end);
	}
	

	private void ProcessNeighbor(
			char startKey,
			GraphNode prevNode,
			GraphNode node,
			char direction,
			Set<Character> visited,
			Queue<GraphNode> nextNodes,
			Map<String, String> shortestPathMap) 
	{
		if (visited.contains(node.key))
		{
			return;
		}
		
		String prevLookupKey = String.valueOf(startKey) + prevNode.key;
		String lookupKey = String.valueOf(startKey) + node.key;
		
		shortestPathMap.put(lookupKey, shortestPathMap.get(prevLookupKey) + direction);
		
		visited.add(node.key);
		nextNodes.add(node);
	}

	private void InitializeNeighbors() 
	{
		for (int i = 0; i < keys.length; i++)
		{	
			AddNeighboringPoints(i);
		}
	}
	
	private void AddNeighboringPoints(int index) 
	{
		int[] point = GetPointFromIndex(index);
		
		int row = point[0];
		int col = point[1];
		
		int[] right = new int[2];
		right[0] = row;
		right[1] = (col + 1) % rowLength;
		
		if (!IsPointValid(right))
		{
			right[1] = 0;
		}
		
		int[] left = new int[2];
		left[0] = row;
		
		if (col == 0)
		{
			if (row == maxRows - 1)
			{
				left[1] = minRowLength - 1;
			}
			else
			{
				left[1] = rowLength - 1;
			}
		}
		else
		{
			left[1] = col - 1;
		}
		
		int[] below = new int[2];
		below[0] = (row + 1) % maxRows;
		below[1] = col;
		
		if (!IsPointValid(below))
		{
			below[0] = 0;
		}
		
		int[] above = new int[2];
		above[1] = col;
		
		if (row == 0)
		{
			above[0] = maxRows - 1;
			
			if (!IsPointValid(above))
			{
				above[0] = minRows - 1;
			}
		}
		else
		{
			above[0] = row - 1;
		}
		
		GraphNode node = nodeMap.get(keys[index]);
		node.left = nodeMap.get(keys[GetIndexFromPoint(left)]);
		node.right = nodeMap.get(keys[GetIndexFromPoint(right)]);
		node.above = nodeMap.get(keys[GetIndexFromPoint(above)]);
		node.below = nodeMap.get(keys[GetIndexFromPoint(below)]);
	}

	private int[] GetPointFromIndex(int index)
	{
		int[] point = new int[2];
		point[0] = index / rowLength;
		point[1] = index % rowLength;
		
		return point;
	}

	private int GetIndexFromPoint(int[] point)
	{
		return point[0] * rowLength + point[1];
	}
	
	private boolean IsPointValid(int[] point)
	{
		return GetIndexFromPoint(point) < keys.length;
	}
}
