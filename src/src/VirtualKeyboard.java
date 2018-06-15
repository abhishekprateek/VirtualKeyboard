package src;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.json.*;

public class VirtualKeyboard 
{
	private static ILogger logger = new Logger();
	
	public static void main(String[] args) throws IOException 
	{
		// TODO: do parameter validations for args
		
		if (args.length != 2)
		{
			logger.Log("Incorrect number of arguments");
		}
		
		String fileName = args[1];
	}
}
