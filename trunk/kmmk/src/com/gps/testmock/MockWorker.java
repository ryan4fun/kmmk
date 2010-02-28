/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public abstract class MockWorker implements Runnable {

	
	protected MockerDef config;
	protected CommunicatAdpater commAdapter;
	protected NetworkAdapter networkAdapter;
	
	protected int messageCount;
	protected int stopCount = 10;
	protected int intervalMS;
	
	public static final short NEXT_COORD_FLAG_NORMAL = 0;
	public static final short NEXT_COORD_FLAG_STOP = 1;
	
	protected String instanceName;
	
	private boolean isStop;
	private int count = 0;

	abstract public double[] getNextCoords(short flag);
	
	public MockWorker(MockerDef def){
		
		this.config = def;
		init();
		
	}
	
	protected void init(){
		
		short deviceType = this.config.getDeviceTypeValue();
		commAdapter = CommunicatAdapterFactory.getAdapter(deviceType);
		commAdapter.init(config);
		networkAdapter = NetworkAdapter.getInstance(config.getServerDef());
		
		this.messageCount = this.config.getMaxMessageCount();
		int intervalSec = this.config.getInterval();
		
		this.intervalMS = intervalSec * 1000;
		
		this.instanceName = this.config.getName() + "_" + this.commAdapter.getDeviceID();
	}
	
	public void run() {
		
		this.count++;
		
		System.out.println(" Mocker Worker starting to Run ... " + this.instanceName);
		while(!this.isStop && this.count <= this.messageCount){
			
			System.out.println(this.instanceName + " Trying to send message count:  " + this.count);
			
			double[] coords = null;
			if(this.count < this.stopCount){
				coords = this.getNextCoords(NEXT_COORD_FLAG_NORMAL);
				
			}else{
				
				coords = this.getNextCoords(NEXT_COORD_FLAG_STOP);
			}
			String msg = this.commAdapter.buildMsg(coords,this);
			System.out.println( this.instanceName + " Sending msg : " + msg);
			this.networkAdapter.send(msg);
			
			this.count++;
			
			try {
				Thread.sleep(this.intervalMS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.networkAdapter.close();
		System.out.println(" Mocker Worker stop to Run ... " + this.instanceName + " total sent msg : " + this.count);
		
		
	}
	
}
