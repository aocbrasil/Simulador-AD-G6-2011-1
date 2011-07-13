package simulator;

public class Item 
{
	private final int id;
	private int queueId;
	private int n1;
	private int nq1;
	private double n2;
	private int nq2;
	private double queue1ArriveTime;
	private double queue2ArriveTime;
	private double service2ArriveTime;
	private double timeInService1;
	private double timeInService2;
	private double W1;
	private double W2;
	private double queue2FirstArrival;
	
	public int getQueueId() 
	{
		return queueId;
	}

	public void setQueueId(int queueId) 
	{
		this.queueId = queueId;
	}

	public Item(int id) 
	{
		this.id = id;
	}
	
	public double getQueue1ArriveTime() 
	{
		return queue1ArriveTime;
	}

	public void setQueue1ArriveTime(double queue1ArriveTime) 
	{
		this.queue1ArriveTime = queue1ArriveTime;
	}

	public double getQueue2ArriveTime() 
	{
		return queue2ArriveTime;
	}

	public void setQueue2ArriveTime(double queue2ArriveTime) 
	{
		this.queue2ArriveTime = queue2ArriveTime;
	}

	public double getTimeInService1() 
	{
		return timeInService1;
	}

	public void setTimeInService1(double timeInService1) 
	{
		this.timeInService1 = timeInService1;
	}

	public double getTimeInService2() 
	{
		return timeInService2;
	}

	public void setTimeInService2(double timeInService2) 
	{
		this.timeInService2 = timeInService2;
	}
	
	public void addTimeInService2(double timeInService2) 
	{
		this.timeInService2 += timeInService2;
	}

	public double getW1() 
	{
		return W1;
	}

	public void setW1(double waitingTimeQueue1) 
	{
		this.W1 = waitingTimeQueue1;
	}

	public double getW2() 
	{
		return W2;
	}

	public void setW2(double waitingTimeQueue2) 
	{
		this.W2 = waitingTimeQueue2;
	}

	public void addW2(double waitingTimeQueue2) 
	{
		this.W2 += waitingTimeQueue2;
	}

	public int getId() 
	{
		return id;
	}

	public int getN1() 
	{
		return n1;
	}

	public void setN1(int n1) 
	{
		this.n1 = n1;
	}

	public int getNq1() 
	{
		return nq1;
	}

	public void setNq1(int nq1) 
	{
		this.nq1 = nq1;
	}

	public double getN2() 
	{
		return n2;
	}

	public void setN2(double n22) 
	{
		this.n2 = n22;
	}

	public int getNq2() 
	{
		return nq2;
	}

	public void setNq2(int nq2) 
	{
		this.nq2 = nq2;
	}

	public void setServico2ArriveTime(double servico2ArriveTime) 
	{
		this.service2ArriveTime = servico2ArriveTime;
	}

	public double getServico2ArriveTime() 
	{
		return service2ArriveTime;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder(300);
		sb.append("ID = ");
		sb.append(getId());
		sb.append(" Arrive time at first queue = ");
		sb.append(getQueue1ArriveTime());
		sb.append(" Nq1 = ");
		sb.append(getNq1());
		sb.append(" N1 = ");
		sb.append(getN1());
		sb.append(" Waiting time in fisrt queue = ");
		sb.append(getW1());
		sb.append(" Time in fisrt service = ");
		sb.append(getTimeInService1());
		sb.append(" Arrive time at second queue = ");
		sb.append(getQueue2ArriveTime());
		sb.append(" N2 = ");
		sb.append(getN2());
		sb.append(" Nq2 = ");
		sb.append(getNq2());
		sb.append(" Waiting time in second queue = ");
		sb.append(getW2());
		sb.append(" Time in second service = ");
		sb.append(getTimeInService2());
		
		return sb.toString();
	}

	public void setQueue2FirstArrival(double currentTime) {
		this.queue2FirstArrival = currentTime;
		
	}

	public double getQueue2FirstArrival() {
		return queue2FirstArrival;
	}
}
