package com.gps.rule;

import java.util.Date;

import org.hibernate.HibernateException;

import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.service.AlertHistoryService;
import com.gps.service.ServiceLocator;

/**
 * @author Ryan
 *
 */
public class CheckerThread implements Runnable{

	
	
	private AbstractTimingRuleChecker checker;
//	private Thread workingThread;
	
	private boolean isStop = false;
	
	public CheckerThread(AbstractTimingRuleChecker timingChecker){
		
		this.checker = timingChecker;
	}
	
	
	@Override
	public void run() {
		
		try {
			while(!this.isStop || checker.getRuleState() != RuleManager.RULE_STATE_FINISHED){
		
				
					if(checker.doCheck(null)){
						//trigger alert
						
						AlertTypeDic alertDic = checker.getAlertTypeDic();
						AlertHistory alert =  new AlertHistory();
						alert.setVehicle(this.checker.vehicle);
						alert.setAlertTypeDic(alertDic);
						alert.setOccurDate(new Date());
						alert.setTag(AlertHistoryService.FROM_TASK_RULE);
						ServiceLocator.getInstance().getAlertHistoryService().addAlertHistory(alert);
						
						this.checker.getManager().notiryUI(alert);
						this.checker.getManager().postUIChanges();
					}
						
					HibernateUtil.commitTransaction();
					
					synchronized(this) {
		                 
		                this.wait(this.checker.getInterval());
					}
			}			

			
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch (HibernateException e){
        	HibernateUtil.rollbackTransaction();
        	e.printStackTrace();
        }finally{
        	HibernateUtil.closeSession();
        } 
	}
	
	
//	public void setThread(Thread workingThread) {
//		
//		this.workingThread = workingThread;
//		
//	}

	
	public synchronized void stopRun() {
		
		isStop = true;
		this.notifyAll();
	}

}
