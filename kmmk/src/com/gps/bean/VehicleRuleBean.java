package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.VehicleRule;
import com.gps.service.RulesService;

public class VehicleRuleBean extends AbstractBean {
	static Logger logger = Logger.getLogger(VehicleRuleBean.class);
	
	private Integer id;
	private Integer vehicleId;
	private Integer ruleId;
	
	public VehicleRuleBean(){
	}
			
	public VehicleRuleBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<VehicleRule> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(VehicleRule.class);
			if (vehicleId != null && vehicleId>0)
				crit.add(Restrictions.eq("vehicle.vehicleId", vehicleId));
			if (ruleId != null && ruleId>0)
				crit.add(Restrictions.eq("rules.ruleId", ruleId));
			crit.add(Restrictions.ne("rules.ruleState", RulesService.RULE_NORM_STATE));
			
			addPagination(crit);
			
			List<VehicleRule> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public VehicleRule findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getVehicleRuleService().findById(id);
		else
			return new VehicleRule();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
}
