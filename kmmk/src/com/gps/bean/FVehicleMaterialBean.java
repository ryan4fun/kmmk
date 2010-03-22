package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FVehicleMaterial;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Users;

public class FVehicleMaterialBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FVehicleMaterialBean.class);
	
	private Integer materialId;
	private Integer vehicleId;
	private Users users;
	private String name;
	private String lastKeeper;
	private Date lastChangeDate;
	private Short state;
	
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
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			crit.addOrder(Order.desc("occurDate"));
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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
}
