package simulator;

import java.util.ArrayList;

public class ItemQueue extends ArrayList<Item> 
{
	private static final long serialVersionUID = 1L;
	
	private final int id;
	
	public ItemQueue(int id) 
	{
		this.id = id;
	}

	public int getId() 
	{
		return id;
	}
	
	public void addOnTop(Item item) 
	{
		this.add(0, item);
	}
}
