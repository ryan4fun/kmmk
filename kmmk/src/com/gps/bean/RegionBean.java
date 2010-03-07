package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Region;
import com.gps.util.Util;

public class RegionBean extends AbstractBean {
	static Logger logger = Logger.getLogger(RegionBean.class);
	
	private Integer regionId;
	private String name;
	private String description;
	private String centralLong;
	private String centralLat;
	private String radius;
	private String edgeLong;
	private String edgeLat;
	private String figurType;
	
	private Short regionTypeId;
	private Short stateTag;
	private Double queryLat;
	private Double queryLong;
	private Integer queryRadius;
	
	private List<Integer> idList;
	
	public RegionBean(){
	}
			
	public RegionBean(HttpServletRequest request) {
		super(request);
	}

	public List<Region> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(Region.class,this);
			if (regionTypeId != null && regionTypeId>0)
				crit.add(Restrictions.eq("regionTypeDic.regionTypeId", regionTypeId));
//			if (stateTag != null && stateTag>0)
//				crit.add(Restrictions.eq("regionTypeDic.stateTag", stateTag));
			
			if (queryRadius != null && queryRadius > 0 && queryLong != null
					&& queryLong > 0 && queryLat != null && queryLat > 0) {
				crit.add(Restrictions.ge("centralLong", queryLong
						- Util.CalculateDistance2LongGap(queryLong, queryRadius)));
				crit.add(Restrictions.ge("centralLat", queryLat
						- Util.CalculateDistance2LatGap(queryRadius)));

				crit.add(Restrictions.le("centralLong", queryLong
						+ Util.CalculateDistance2LongGap(queryLong, queryRadius)));
				crit.add(Restrictions.le("centralLat", queryLat
						+ Util.CalculateDistance2LatGap(queryRadius)));
			}
			
			if (name != null && name.length()>0)
				crit.add(Restrictions.like("name", name));
//			Criteria crit = HibernateUtil.getSession().createCriteria(Region.class);
//			
//			if (name != null && !name.equals(""))
//				crit.add(Restrictions.eq("name", name));
//			if (description != null && !description.equals(""))
//				crit.add(Restrictions.eq("description", description));
//			if (centralLong != null && this.centralLong>=-180 && this.centralLong<=180)
//				crit.add(Restrictions.eq("centralLong", centralLong));
//			if (centralLat != null && this.centralLat>=-90 && this.centralLat<=90)
//				crit.add(Restrictions.eq("centralLat", centralLat));
//			if (radius != null && this.radius>0)
//				crit.add(Restrictions.eq("radius", radius));
			
			addPagination(crit);
			
			List<Region> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public List<Region> getListByIdList(){
		try {
			Criteria crit = this.generateStringPropCriteria(Region.class,this);
			if (idList != null && idList.size()>0)
				crit.add(Restrictions.in("regionId", idList));
			List<Region> list = crit.list();
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Region findById(){
		if(regionId!=null && regionId.intValue()>0)
			return getServiceLocator().getRegionService().findById(regionId);
		else
			return new Region();
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCentralLong() {
		return centralLong;
	}

	public void setCentralLong(String centralLong) {
		this.centralLong = centralLong;
	}

	public String getCentralLat() {
		return centralLat;
	}

	public void setCentralLat(String centralLat) {
		this.centralLat = centralLat;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getEdgeLong() {
		return edgeLong;
	}

	public void setEdgeLong(String edgeLong) {
		this.edgeLong = edgeLong;
	}

	public String getEdgeLat() {
		return edgeLat;
	}

	public void setEdgeLat(String edgeLat) {
		this.edgeLat = edgeLat;
	}

	public String getFigurType() {
		return figurType;
	}

	public void setFigurType(String figurType) {
		this.figurType = figurType;
	}

	public Short getRegionTypeId() {
		return regionTypeId;
	}

	public void setRegionTypeId(Short regionTypeId) {
		this.regionTypeId = regionTypeId;
	}

	public Short getStateTag() {
		return stateTag;
	}

	public void setStateTag(Short stateTag) {
		this.stateTag = stateTag;
	}

	public Double getQueryLat() {
		return queryLat;
	}

	public void setQueryLat(Double queryLat) {
		this.queryLat = queryLat;
	}

	public Double getQueryLong() {
		return queryLong;
	}

	public void setQueryLong(Double queryLong) {
		this.queryLong = queryLong;
	}

	public Integer getQueryRadius() {
		return queryRadius;
	}

	public void setQueryRadius(Integer queryRadius) {
		this.queryRadius = queryRadius;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

}
