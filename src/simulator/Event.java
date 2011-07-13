package simulator;

public class Event 
{
	public static final int TYPE_ENTER_IN_QUEUE_1 = 0;
	public static final int TYPE_ENTER_IN_SERVICE_1 = 1;
	public static final int TYPE_ENTER_IN_QUEUE_2 = 2;
	public static final int TYPE_ENTER_IN_SERVICE_2 = 3;
	public static final int TYPE_SYSTEM_EXIT = 4;
	
	private final Item item;
	private final double time;
	private final int type;
		
	public Event(Item item, int type, double time) 
	{
		this.item = item;
		this.type = type;
		this.time = time;
	}

	public Item getItem() 
	{
		return item;
	}

	public double getTime() 
	{
		return time;
	}

	public int getType() 
	{
		return type;
	}
	
	public boolean equals(Object object) 
	{
		if (object == null) 
			return this == null;
		
		if (!(object instanceof Event)) 
			return false;
		
		Event event = (Event) object;
		
		return event.getItem().getId() == this.getItem().getId();
	}
	
}
