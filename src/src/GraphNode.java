package src;

import java.util.List;
import java.util.Set;

public class GraphNode 
{
	char key;
	Set<GraphNode> nbors;
	
	public GraphNode(char k)
	{
		this.key = k;
	}

	@Override
	public String toString() {
		return "GraphNode [key=" + key + ", nbors=" + SerializeNodes(nbors) + "]";
	}

	private String SerializeNodes(Set<GraphNode> nodes) 
	{
		StringBuilder sb = new StringBuilder();
		
		for (GraphNode n : nodes)
		{
			sb.append(n.key);
			sb.append(" ,");
		}
		
		return sb.toString();
	}
}
