package com.gps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.RealtimeTrack;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.orm.Vehicle;


public class SegmentService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short SEGMENT_NORM_STATE = 1;
	public final static short SWGMENT_DEL_STATE = -1;
	
	public static short CheckPoint_STATE_POSITIVE = 1;
	public static short CheckPoint_STATE_NEGETIVE = 0;
	
	public static double OFFSET = 0.01;

	public void addSegment(Segment u){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentHome().persist(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void deleteSegment(Segment u){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentHome().delete(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void updateSegment(Segment u){		
		try {
			beginTransaction();
//			getDAOLocator().getSegmentHome().attachDirty(u);
			getDAOLocator().getSegmentHome().merge(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public Segment findById(int id){
		try {
//			beginTransaction();
			Segment u = getDAOLocator().getSegmentHome().findById(id);
//			commitTransaction();
			return u;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	
	
	public List<GPSPoint> checkSegment(int vehicleId, int segmentId, Date startDate, Date endDate){
		
		List<GPSPoint> result = new ArrayList<GPSPoint>();
		
		Vehicle v = ServiceLocator.getInstance().getVehicleService().findById(vehicleId);
		Segment seg = ServiceLocator.getInstance().getSegmentService().findById(segmentId);
		
		if(v == null || seg == null){
			return new ArrayList<GPSPoint>();
		}
		
		List<SegmentDetail> checkPoints = ServiceLocator.getInstance().getSegmentDetailService().findSegmentCheckPoints(seg);
		for(SegmentDetail point:checkPoints){
			GPSPoint instance = createGPSPoint(point);
			if(v != null &&  startDate != null && endDate!=null){
					
				RealtimeTrack trackPoint = ServiceLocator.getInstance().getRealtimeTrackService().findByVehicleTrackPointAndDates(v, instance.getLongVal(), instance.getLatVal(), OFFSET, startDate, endDate);
				if(trackPoint != null){
						
					instance.setCheckedState(CheckPoint_STATE_POSITIVE);
					instance.setReachTime(trackPoint.getRecieveTime());
				}
				
			}
			result.add(instance);
		}
		
		return result;
		
	}

	
	private GPSPoint createGPSPoint(SegmentDetail point) {
		GPSPoint result = new GPSPoint();
		
		result.setLongVal(point.getLongValue());
		result.setLatVal(point.getLatValue());
		result.setCheckedState(CheckPoint_STATE_NEGETIVE);
		
		return result;
	}


	public class GPSPoint {
		
		private double longVal;
		private double latVal;
		private Date reachTime;
		private short checkedState;
		
		public void setLongVal(double longVal) {
			this.longVal = longVal;
		}
		
		public double getLongVal() {
			return longVal;
		}
		public void setLatVal(double latVal) {
			this.latVal = latVal;
		}
		public double getLatVal() {
			return latVal;
		}
		public void setReachTime(Date reachTime) {
			this.reachTime = reachTime;
		}
		public Date getReachTime() {
			return reachTime;
		}
		public void setCheckedState(short checkedState) {
			this.checkedState = checkedState;
		}
		public short getCheckedState() {
			return checkedState;
		}
		
	}
	
}
