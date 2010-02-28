package com.gps.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.CheckPoints;
import com.gps.orm.QualifiedCoordArea;
import com.gps.orm.RealtimeTrack;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.orm.Vehicle;

public class CheckPointsService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public static short CheckPoint_STATE_POSITIVE = 1;
	public static short CheckPoint_STATE_NEGETIVE = 0;
	
	public static double OFFSET = 0.00000001;

	
	public void addCheckPoints(CheckPoints c){		
		try {
			beginTransaction();
			getDAOLocator().getCheckPointsHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteQualifiedCoordArea(CheckPoints o){		
		try {
			beginTransaction();
			getDAOLocator().getCheckPointsHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateQualifiedCoordArea(CheckPoints o){		
		try {
			beginTransaction();
			getDAOLocator().getCheckPointsHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public CheckPoints findById(int id){
		try {
//			beginTransaction();
			CheckPoints o = getDAOLocator().getCheckPointsHome().findById(id);
//			commitTransaction();
			return o;
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
		
		result.setLongVal(point.getLatValue());
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
