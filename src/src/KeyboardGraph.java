package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class KeyboardGraph 
{
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
		
		System.out.println(node);
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
