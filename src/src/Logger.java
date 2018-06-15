package src;

public class Logger implements ILogger 
{

	@Override
	public void Log(String s) 
	{
		System.out.println(s);		
	}
	
}
