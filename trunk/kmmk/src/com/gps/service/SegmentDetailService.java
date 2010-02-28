package com.gps.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;

public class SegmentDetailService extends AbstractService {
	static Logger logger = Logger.getLogger(SegmentDetailService.class);
	
	public final static byte SEGMENT_DETAIL_TYPE_ROAD_POINT = 1;
	public final static byte SEGMENT_DETAIL_TYPE_CHECK_POINT = 2;
	
	public void addSegmentDetail(SegmentDetail es){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentDetailHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteSegmentDetail(SegmentDetail es){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentDetailHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateSegmentDetail(SegmentDetail es){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentDetailHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public SegmentDetail findById(long id){
		try {
//			beginTransaction();
			SegmentDetail es = getDAOLocator().getSegmentDetailHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public List<SegmentDetail> findSegmentCheckPoints(Segment seg) {
		
		if(seg != null){
			
			SegmentDetail example = new SegmentDetail();
			example.setTag(SEGMENT_DETAIL_TYPE_CHECK_POINT);
			example.setSegment(seg);
			return getDAOLocator().getSegmentDetailHome().findByExample(example);
			
		}
		return new ArrayList<SegmentDetail>();
	}
	
}
