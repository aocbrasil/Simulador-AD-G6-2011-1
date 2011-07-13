package simulator;

import java.io.IOException;

public class Main 
{
	final static int PARAMETER_INDEX_RO = 0;
	final static int PARAMETER_INDEX_MI = 1;
	final static int PARAMETER_INDEX_TRANSIENT_SIZE = 2;
	final static int PARAMETER_INDEX_TRANSIENT_NUMBER_OF_ROUNDS = 3;
	final static int PARAMETER_INDEX_ROUND_SIZE = 4;
	final static int PARAMETER_INDEX_HIDE_TRANSIENT_FASE = 5;

	
	public static void main(String[] args) throws IOException 
	{
		try 
		{
			if (args.length != 6) 
			{
				String msg = "command line structure: java -jar simulador.jar <ro> <mi> <ttrans> <nrod> <trod> <om>\n";
				msg += "\tro: ro ratio\n";
				msg += "\tmi: mi ratio\n";
				msg += "\tttrans: transient size\n";
				msg += "\tnrod: number of rounds\n";
				msg += "\ttrod: rond size\n";
				msg += "\tom: hide transient fase\n";
				throw new IllegalArgumentException(msg);
			}
	
			float ro = getFloatParameter(args, PARAMETER_INDEX_RO);
			float mi = getFloatParameter(args, PARAMETER_INDEX_MI);
			int transientFaseSize = getIntParameter(args, PARAMETER_INDEX_TRANSIENT_SIZE);
			int numberOfRounds = getIntParameter(args, PARAMETER_INDEX_TRANSIENT_NUMBER_OF_ROUNDS);
			int roundSize = getIntParameter(args, PARAMETER_INDEX_ROUND_SIZE);
			boolean hideTransientFase = Boolean.getBoolean(args[PARAMETER_INDEX_HIDE_TRANSIENT_FASE]);
			
			Simulator simulator = new Simulator(ro, mi, transientFaseSize, numberOfRounds, roundSize, hideTransientFase);
			simulator.begin();
		} 
		catch (IllegalArgumentException e) 
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static float getFloatParameter(String[] args, int index) throws IllegalArgumentException
	{
		 try
		 {
			float value = Float.parseFloat(args[index]);
			return value;
		 }
		 catch (NumberFormatException e) 
		 {
			throw new IllegalArgumentException("Invalid parameter, parameter index: "+index, e);
		}
	}
	
	private static int getIntParameter(String[] args, int index) throws IllegalArgumentException
	{
		 try
		 {
			int value = Integer.parseInt(args[index]);
			return value;
		 }
		 catch (NumberFormatException e) 
		 {
			throw new IllegalArgumentException("Invalid parameter, parameter index: "+index, e);
		}
	}
}
