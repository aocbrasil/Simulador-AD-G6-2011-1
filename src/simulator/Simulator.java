package simulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import simulator.util.ExponentialRatioGenerator;

public class Simulator 
{
	private static int FIRST_QUEUE = 1;
	private static int SECOND_QUEUE = 2;
	
	//initialization parameters
	private final float ro;
	private final float mi;
	private final int transientFaseSize;
	private final int numberOfRounds;
	private final int roundSize;
	private final boolean hideTransientFase;
	private final ItemQueue queue1;
	private final ItemQueue queue2;
	private final Random randon;
	private Item itemInService = null;
	private final ArrayList<Double> w1 = new ArrayList<Double>();
	private final ArrayList<Double> w2 = new ArrayList<Double>();
	
	public Simulator(float ro, float mi, int transientFaseSize, int numberOfRounds, int roundSize, boolean hideTransientFase)
	{
		this.ro = ro;
		this.mi = mi;
		this.transientFaseSize = transientFaseSize;
		this.numberOfRounds = numberOfRounds;
		this.roundSize = roundSize;
		this.hideTransientFase = hideTransientFase;
		this.queue1 = new ItemQueue(FIRST_QUEUE);
		this.queue2 = new ItemQueue(SECOND_QUEUE);
		this.randon = new Random(System.currentTimeMillis());
	}
	
	private double getNextEventTime(double currentTime, double ratio) 
	{
		return currentTime + ExponentialRatioGenerator.generate(randon.nextDouble(), ratio);
	}
	
	/**
	 * @throws IOException
	 */
	public void begin() throws IOException
	{

		PriorityQueue<Event> eventQueue = new PriorityQueue<Event>(10, new EventComparator());
		
		double lambda = ro / 2;
		RoundConfig currentRound = new RoundConfig(0);
		double currentTime = 0;
		
		Item item1 = new Item(0);
		Event firstEvent = new Event(item1, Event.TYPE_ENTER_IN_QUEUE_1, currentTime);
		
		System.out.print(currentTime + ": ");
		System.out.print("Inicializacao do simulador;\n");
		
		eventQueue.add(firstEvent);
		
		while (!eventQueue.isEmpty()) 
		{
			Event currentEvent = eventQueue.remove();
			currentTime = currentEvent.getTime();

			if (currentEvent.getType() == Event.TYPE_ENTER_IN_QUEUE_1) 
			{
				System.out.print(currentTime + ": Item " + currentEvent.getItem().getId() + " entra na fila 1\n");
				if(itemInService != null){
					System.out.print("Item (" + itemInService.getId() + ") em servico\n");
				}
				Item item = currentEvent.getItem();
				item.setQueueId(FIRST_QUEUE);
				item.setQueue1ArriveTime(currentTime);
				item.setNq1(queue1.size());
				item.setNq2(queue2.size());
				if (itemInService != null && itemInService.getQueueId() == FIRST_QUEUE )
				{
					item.setN1(queue1.size() + 1);
				}
				queue1.add(item);
				if (itemInService != null && itemInService.getQueueId() == SECOND_QUEUE ) 
				{
					//item.setN2(item.getN2() + 1);
					eventQueue.remove(new Event(itemInService, 0, 0));
					itemInService.setN2(0);
					System.out.print(currentTime + ": Item " + itemInService.getId() + " interrompido!!!!\n");
					System.out.print("N2(" + itemInService.getId() + ") = " + itemInService.getN2() + "\n");
					itemInService.setQueue2ArriveTime(currentTime);
					itemInService.addTimeInService2(currentTime - itemInService.getServico2ArriveTime());
					queue2.addOnTop(itemInService);
					itemInService = null;
					System.out.print("\ntam fila 2 = " + queue2.size() + "\n\n");
				}
				eventQueue.add(new Event(new Item(item.getId()+1), Event.TYPE_ENTER_IN_QUEUE_1, getNextEventTime(currentTime, lambda)));
				System.out.print("FILA 2 [ ");
				for(int i = 0; i < queue2.size(); i++){
					System.out.print(queue2.get(i).getId() + ",");
				}
				System.out.print(" ] \n");
				System.out.print("FILA 1 [ ");
				for(int i = 0; i < queue1.size(); i++){
					System.out.print(queue1.get(i).getId() + ",");
				}
				System.out.print(" ] \n");
			} 
			else 
			{
				if (currentEvent.getType() == Event.TYPE_ENTER_IN_SERVICE_1) 
				{
					Item item = currentEvent.getItem();
					item.setW1(currentTime - item.getQueue1ArriveTime());
					w1.add(item.getW1());
					itemInService = item;
					
					System.out.print(currentTime + ": Item " + item.getId() + " entra em servico 1\n");
					if(itemInService != null){
						System.out.print("Item (" + itemInService.getId() + ") em servico\n");
					}
					eventQueue.add(new Event(item, Event.TYPE_ENTER_IN_QUEUE_2, getNextEventTime(currentTime, mi)));
				}
				else if (currentEvent.getType() == Event.TYPE_ENTER_IN_QUEUE_2) 
				{
					Item item = currentEvent.getItem();
					if (item.getQueueId() == FIRST_QUEUE) 
					{
						System.out.print(currentTime + ": Item " + item.getId() + " sai do servico 1 e ENTRA FILA 2\n");
//						itemInService.setN2(queue2.size());
//						System.out.print("N2(" + itemInService.getId() + ") = " + itemInService.getN2() + "\n");
						if(itemInService != null && itemInService.getQueueId() == SECOND_QUEUE){
							item.setNq2(queue2.size() + 1);
//							item.setN2(item.getNq2() + 1);
							if(itemInService != null){
								System.out.print("Item (" + itemInService.getId() + ") em servico\n");
							}
						}
						item.setQueueId(SECOND_QUEUE);
						item.setTimeInService1(currentTime - (item.getQueue1ArriveTime() + item.getW1()));
						item.setQueue2FirstArrival(currentTime);
					} 
					else if (item.getQueueId() == SECOND_QUEUE)
					{
						System.out.print(currentTime + ": Item " + item.getId() + " VOLTA PARA FILA 2\n");
						// interruption occured
						item.addTimeInService2(currentTime - item.getServico2ArriveTime());
						System.out.print("TS2 Item " + item.getId() + ": " + item.getTimeInService2() + "\n ");
					}
					item.setQueue2ArriveTime(currentTime);
					itemInService = null;
					queue2.add(item);
					System.out.print("FILA 2 [ ");
					for(int i = 0; i < queue2.size(); i++){
						System.out.print(queue2.get(i).getId() + ",");
					}
					System.out.print(" ] \n");
					System.out.print("FILA 1 [ ");
					for(int i = 0; i < queue1.size(); i++){
						System.out.print(queue1.get(i).getId() + ",");
					}
					System.out.print(" ] \n");
					if(itemInService != null){
						System.out.print("Item (" + itemInService.getId() + ") em servico\n");
					}
				} 
				else if (Event.TYPE_ENTER_IN_SERVICE_2 == currentEvent.getType()) 
				{
					Item item = currentEvent.getItem();
					item.addW2(currentTime - item.getQueue2ArriveTime());
					item.setServico2ArriveTime(currentTime);
					w2.add(item.getW2());
					
					System.out.print(currentTime + ": Item " + item.getId() + " ENTRA NO SERVICO 2\n");
					
					itemInService = item;
					eventQueue.add(new Event(item, Event.TYPE_SYSTEM_EXIT, getNextEventTime(currentTime, mi)));
					if(itemInService != null){
						System.out.print("Item (" + itemInService.getId() + ") em servico\n");
					}
				} 
				else if (Event.TYPE_SYSTEM_EXIT == currentEvent.getType()) 
				{
					Item item = currentEvent.getItem();
					item.addTimeInService2(currentTime - item.getServico2ArriveTime());
					//calculo de N2
					double t2 = currentTime - item.getQueue2FirstArrival();
					double n2 = queue2.size()/t2;
					System.out.print("N2 = " + n2 + "\n");
					item.setN2(n2);
					
					itemInService = null;
					System.out.print(currentTime + ": Item " + item.getId() + " SAI DO SERVICO 2 e sistema\n");
					if ((currentRound.getId() != 0) || (!hideTransientFase))
					{
						//grava estatisticas TESTES DE CORRECAO FIM
						recordSim("===================\nITEM " + String.valueOf(item.getId()),"\nRodada: " + String.valueOf(currentRound.getId()), "\nW1: " + String.valueOf(item.getW1()), 
								"\nTS1 :" + String.valueOf(item.getTimeInService1()), "\nW2: " +String.valueOf(item.getW2()), 
								"\nTS2: " +String.valueOf(item.getTimeInService2()), "\nN1: " +String.valueOf(item.getN1()), "\nNq1: " +String.valueOf(item.getNq1()), 
								"\nN2: " +String.valueOf(item.getN2()), "\nNq2: " +String.valueOf(item.getNq2()) + "\n====================");
//						recordSim(String.valueOf(item.getId()),String.valueOf(currentRound.getId()),String.valueOf(item.getW1()),String.valueOf(item.getTimeInService1()), String.valueOf(item.getW2()), 
//								String.valueOf(item.getTimeInService2()),String.valueOf(item.getN1()),String.valueOf(item.getNq1()), String.valueOf(item.getN2()), String.valueOf(item.getNq2()));
					}
					
					currentRound.incRoundItens();
					if (currentRound.getId() == 0) 
					{
						if (currentRound.getRoundItens() >= transientFaseSize) {
							if (numberOfRounds == 0) break;
							currentRound = new RoundConfig(1);
						}
					} else {
						if (currentRound.getRoundItens() >= roundSize) {
							if (currentRound.getId() < numberOfRounds) {
								currentRound = new RoundConfig(currentRound.getId()+1);
							} else {
								break;
							}
						}
					}
					
				}
			}
			if (itemInService == null) 
			{
				if (!queue1.isEmpty()) 
				{
					Item item = queue1.remove(0);
					eventQueue.add(new Event(item, Event.TYPE_ENTER_IN_SERVICE_1, currentTime));
				} 
				else if (!queue2.isEmpty()) 
				{
					Item item = queue2.remove(0);
					eventQueue.add(new Event(item, Event.TYPE_ENTER_IN_SERVICE_2, currentTime));
				}
			}
		}
	}

	private void recordSim(String... data) throws IOException 
	{
		String record = "";
		for (String dado : data) 
		{
			record += dado + ";";
		}
		
		record = record.substring(0, record.length()-1);
		System.out.println(record);
	}
}
