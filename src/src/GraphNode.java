package src;

public class GraphNode 
{
	char key;
	GraphNode left;
	GraphNode right;
	GraphNode above;
	GraphNode below;
	
	public GraphNode(char k)
	{
		this.key = k;
	}

	@Override
	public String toString() {
		return "GraphNode [key=" + key + ", left=" + NodeValue(left) + ", right=" + NodeValue(right) + ", above=" + NodeValue(above) + ", below=" + NodeValue(below)
				+ "]";
	}
	
	private String NodeValue(GraphNode n)
	{
		if (n == null)
		{
			return "";
		}
		
		return String.valueOf(n.key);
	}

}
