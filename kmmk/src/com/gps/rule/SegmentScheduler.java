/**
 * 
 */
package com.gps.rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gps.datacap.Message;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.orm.Task;
import com.gps.orm.TaskSegment;
import com.gps.service.ServiceLocator;
import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class SegmentScheduler {

	private Task currentTask;
	private List<Segment> segmentList = new ArrayList<Segment>();
	
	private Segment currentSeg = null;
	
	public SegmentScheduler(Task task) {
		
		this.currentTask = task;
		Set<TaskSegment> segments = this.currentTask.getTaskSegments();
		Iterator<TaskSegment> it = segments.iterator();
		
		while(it.hasNext()){
			
			TaskSegment tempSegRef = it.next();
			Segment segmentDef = tempSegRef.getSegment();
			
			segmentList.add(segmentDef);
			
		}
	}
	
	
	public Segment getCurrentSegment(Message msg){
		
//		Segment result = null;
		
		if(currentSeg == null){
			
			currentSeg = findSegByMsg(msg);
		}
		
		
		
		return currentSeg;		
	}


	private Segment findSegByMsg(Message msg) {
		Segment result = null;
		
		if(this.segmentList.size() <= 0){
			
			return result;
		}
		
		double[] distance = new double[this.segmentList.size()];
		int i = 0;
		for(Segment seg: this.segmentList){
			
			long endId = seg.getEndDetailId();
			SegmentDetail endPoint = ServiceLocator.getInstance().getSegmentDetailService().findById(endId);
			distance[i] = Util.CalculateLatLng2Distance(msg.getLatitude(), msg.getLongitude(), endPoint.getLatValue(), endPoint.getLongValue());
			i++;
		}

		int nearestI = 0;
		double tempValue = distance[0];
	
		for(i = 0; i <this.segmentList.size();i++ ){
			
			if(distance[i] < tempValue){
				
				tempValue = distance[i];
				nearestI = i;
			}
		}
		
		result = this.segmentList.get(nearestI);
		
		return result;
	}

	
	
}
