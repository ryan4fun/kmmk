package com.gps.bean;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gps.action.Action;
import com.gps.orm.HibernateUtil;
import com.gps.util.Util;

public abstract class AbstractBean extends Action{
	public static int DEFAULT_ROWS_PER_PAGE = 15;
	
	protected boolean isPagination = false;
	protected int pageNumber;
	protected int rowsPerPage = DEFAULT_ROWS_PER_PAGE;
	protected Integer maxRecord;
	
	public AbstractBean() {
	}

	public AbstractBean(HttpServletRequest request) {
		super.setRequest(request);
		generateAllSimpleProp(this);
		isPagination = true;
		if (rowsPerPage < 1)
			rowsPerPage = DEFAULT_ROWS_PER_PAGE;
		if (pageNumber < 0)
			pageNumber = 0;
	}	
	
	protected Criteria addPagination(Criteria crit) {
		if(isPagination){
			if(isPagination){
				if (rowsPerPage < 1)
					rowsPerPage = DEFAULT_ROWS_PER_PAGE;
				crit.setMaxResults(rowsPerPage);
				if (pageNumber>0)
					crit.setFirstResult(pageNumber * rowsPerPage);
			}
		}
		return crit;
	}
	
	protected Criteria getTotalCount(Criteria crit) {
		if(isPagination){
			crit.setFirstResult(0);
			this.maxRecord = (Integer)crit.setProjection(Projections.rowCount()).uniqueResult();
			crit.setProjection(null);
		}
		return crit;
	}
	
	protected Criteria generateStringPropCriteria(Class ormClass,Object valueSource){
		Criteria crit = HibernateUtil.getSession().createCriteria(ormClass);
		
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(ormClass);
		for(PropertyDescriptor pd:pds){
			try {
				Object tmps = PropertyUtils.getProperty(valueSource, pd.getName());
				if (tmps instanceof String && ((String)tmps).length()>0) {
					Class c = pd.getPropertyType();
					if (c.getName().equals("java.lang.String")) {
						crit.add(Restrictions.like(pd.getName(), "%"+(String)tmps+"%"));
					} else if (c.getName().equals("java.lang.Byte")
							|| c.getName().equals("byte")) {
						crit.add(Restrictions.eq(pd.getName(), Byte.parseByte((String)tmps)));
					} else if (c.getName().equals("java.lang.Short")
							|| c.getName().equals("short")) {
						crit.add(Restrictions.eq(pd.getName(), Short.parseShort((String)tmps)));
					} else if (c.getName().equals("java.lang.Integer")
							|| c.getName().equals("int")) {
						crit.add(Restrictions.eq(pd.getName(), Integer.parseInt((String)tmps)));
					} else if (c.getName().equals("java.lang.Long")
							|| c.getName().equals("long")) {
						crit.add(Restrictions.eq(pd.getName(), Long.parseLong((String)tmps)));
					} else if (c.getName().equals("java.lang.Float")
							|| c.getName().equals("float")) {
						crit.add(Restrictions.eq(pd.getName(), Float.parseFloat((String)tmps)));
					} else if (c.getName().equals("java.lang.Double")
							|| c.getName().equals("double")) {
						crit.add(Restrictions.eq(pd.getName(), Double.parseDouble((String)tmps)));
					} else if (c.getName().equals("java.util.Date")) {
						crit.add(Restrictions.eq(pd.getName(), Util.parseDate((String)tmps)));
//					} else if (c.getName().equals("java.math.BigDecimal")) {
//						crit.add(Restrictions.eq(pd.getName(), new BigDecimal(tmps)));
					}
				}
			} catch (Exception e) {

			}
		}
		return crit;
	}
	
	public void doAction() throws Exception{
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Integer getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(Integer maxRecord) {
		this.maxRecord = maxRecord;
	}

	public boolean isPagination() {
		return isPagination;
	}

	public void setPagination(boolean isPagination) {
		this.isPagination = isPagination;
	}
	
}
