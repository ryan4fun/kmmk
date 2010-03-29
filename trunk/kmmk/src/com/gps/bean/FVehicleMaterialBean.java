package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FVehicleMaterial;
import com.gps.orm.HibernateUtil;

public class FVehicleMaterialBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FVehicleMaterialBean.class);
	
	private Integer materialId;
	private String name;
	private String lastKeeper;
	private Date lastChangeDate;
	private Short state;
	
	private Integer vehicleId;
	private String licensPadNumber;
	private Date lastChangeDateStart;
	private Date lastChangeDateEnd;
	
	public FVehicleMaterialBean(){
	}
			
	public FVehicleMaterialBean(HttpServletRequest request) {
		super(request);
	}

	public List<FVehicleMaterial> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FVehicleMaterial.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FVehicleMaterial.class);			
			_crit.createAlias("vehicle", "v");
			
			if (vehicleId != null && vehicleId>0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.name != null && name.length()>0){
				crit.add(Restrictions.like("name", "%"+name+"%"));
				_crit.add(Restrictions.like("name", "%"+name+"%"));
			}
			
			if (this.lastKeeper != null && lastKeeper.length()>0){
				crit.add(Restrictions.like("lastKeeper", "%"+lastKeeper+"%"));
				_crit.add(Restrictions.like("lastKeeper", "%"+lastKeeper+"%"));
			}
			
			if (this.lastChangeDateStart != null){
				crit.add(Restrictions.ge("lastChangeDate", this.lastChangeDateStart));
				_crit.add(Restrictions.ge("lastChangeDate", this.lastChangeDateStart));
			}
				
			if (this.lastChangeDateEnd != null){
				crit.add(Restrictions.le("lastChangeDate", this.lastChangeDateEnd));
				_crit.add(Restrictions.le("lastChangeDate", this.lastChangeDateEnd));
			}
			
//			crit.addOrder(Order.desc("occurDate"));
			addPagination(crit);
			List<FVehicleMaterial> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FVehicleMaterial findById(){
		if(materialId!=null && materialId.intValue()>0)
			return getServiceLocator().getFVehicleMaterialService().findById(materialId);
		else
			return new FVehicleMaterial();
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastKeeper() {
		return lastKeeper;
	}

	public void setLastKeeper(String lastKeeper) {
		this.lastKeeper = lastKeeper;
	}

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Date getLastChangeDateStart() {
		return lastChangeDateStart;
	}

	public void setLastChangeDateStart(Date lastChangeDateStart) {
		this.lastChangeDateStart = lastChangeDateStart;
	}

	public Date getLastChangeDateEnd() {
		return lastChangeDateEnd;
	}

	public void setLastChangeDateEnd(Date lastChangeDateEnd) {
		this.lastChangeDateEnd = lastChangeDateEnd;
	}
	
}
