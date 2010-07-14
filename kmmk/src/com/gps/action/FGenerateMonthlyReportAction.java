package com.gps.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Criteria;
import org.hibernate.connection.ConnectionProviderFactory;
import org.hibernate.criterion.Restrictions;

import com.gps.Message;
import com.gps.bean.LoginInfo;
import com.gps.orm.FUser;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.rptbean.DataSourceFactory;
import com.gps.service.FUserService;
import com.gps.service.ServiceLocator;
import com.gps.servlet.MKgpsServlet;
import com.gps.util.Util;

public class FGenerateMonthlyReportAction extends Action{

	public static final String basePath = MKgpsServlet.WEB_ROOT_ABSOLUTE_PATH + "WEB-INF"+File.separator+"reports" +File.separator;
	  
	@Override
	public void doAction() throws Exception{
		
//		String rptName = get("reportName");
		String vehicleId = get("vehicleId");
		String yearMonth = get("yearMonth");
		
		if(vehicleId != null && yearMonth != null){

			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			Date startDate = df.parse(yearMonth);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, 1);		
			Date endDate = cal.getTime();
			
			Vehicle v = ServiceLocator.getInstance().getVehicleService().findById(Integer.parseInt(vehicleId));
			JRDataSource subChart = DataSourceFactory.buildMonthlyCostDataSource(v, startDate, endDate);
			
			File reportXMLFile = new File(basePath+"vehicle_monthly.jrxml");  
			File reportFile = new File(basePath+"vehicle_monthly.jasper");  
//			System.out.println("Start Report export : " + basePath+"vehicle_monthly.jasper");
			
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String conStr =  "jdbc:sqlserver://localhost:1433;databaseName=mkgps1";
			Connection connection = DriverManager.getConnection(conStr,"sa","1234");
		
			Map parameters = new HashMap();  
			
			parameters.put("vehicleId", Integer.parseInt(vehicleId));
			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
			
			parameters.put("SUBREPORT_DIR",basePath);
			parameters.put("costDs", subChart);
			
//			 JasperDesign jasperDesign = JRXmlLoader.load(reportXMLFile);
//	         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	           
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath()); 
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,connection);  
			response.setContentType("application/pdf");
			
			
			response.setHeader("Content-Disposition", "inline; filename=\""+yearMonth+".pdf\"");   
			
//			FileOutputStream of = new FileOutputStream(basePath+"test.pdf");
//			JasperExportManager.exportReportToPdfStream(jasperPrint,of);
//			of.flush();
//			of.close();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
//			byte[] generatedPDF =  JasperExportManager.exportReportToPdf(jasperPrint);
			
			FileOutputStream  os = new FileOutputStream(basePath+"monthlyReport.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, os);

			os.flush();
			os.close();
			 
//			 if (jasperPrint != null) {  
//		            response.setContentType("application/pdf");  
//		            OutputStream ouputStream = response.getOutputStream();  
//		            ouputStream.write(generatedPDF);
//
//		            ouputStream.flush();  
//		            ouputStream.close();  
//			 }
		}
	       
	}
}
