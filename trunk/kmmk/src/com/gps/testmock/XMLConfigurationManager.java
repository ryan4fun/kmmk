/**
 * 
 */
package com.gps.testmock;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Ryan
 *
 */
public class XMLConfigurationManager {

	static Logger logger = Logger.getLogger(XMLConfigurationManager.class);
	
	public static final String TAG_NAME_SERVER = "server";
	
	public static final String TAG_NAME_MOCKER = "mocker";
	public static final String TAG_NAME_PROPERTY = "property";
	public static final String TAG_NAME_PROPERTYNAME = "name";
	public static final String TAG_NAME_PROPERTYVALUE = "value";
	public static final String TAG_NAME_TYPE = "type";
	
	public static MockConfiguration load(String configFilePath) {
	
		MockConfiguration result = null;
		Document doc = loadDocument(configFilePath);
		
		if(doc != null){
			
			result = buildConfig(doc);
		}
		return result;
	}

	
	private static MockConfiguration buildConfig(Document doc) {
		
		MockConfiguration mockConfig = new MockConfiguration();
		ServerDef server = null;
		List elements1 = doc.getRootElement().elements(TAG_NAME_SERVER);
		for(Object obj : elements1) {
			Element element = (Element)obj;
			server = buildServer(element);
			
		}
		
		List elements = doc.getRootElement().elements(TAG_NAME_MOCKER);
		for(Object obj : elements) {
			Element element = (Element)obj;
			MockerDef mocker = buildMocker(element);
			mocker.setServerDef(server);
			mockConfig.addMocker(mocker);
		}
		

		
		return mockConfig;
	}


	private static ServerDef buildServer(Element element) {
		
		ServerDef serverDef = new ServerDef();
		
		List elements = element.elements(TAG_NAME_PROPERTY);
		for(Object obj : elements) {
			Element element1 = (Element)obj;
			String proName = element1.attributeValue(TAG_NAME_PROPERTYNAME);
			String proValue = element1.attributeValue(TAG_NAME_PROPERTYVALUE);
			
			setPropertyValue(serverDef,proName,proValue);
			
		}
		
		return serverDef;
	}


	private static MockerDef buildMocker(Element mockerElement) {
		
		MockerDef mocker = new MockerDef();
		String mockerType = mockerElement.attributeValue(TAG_NAME_TYPE);
		mocker.setMockerType(mockerType);
		
		List elements = mockerElement.elements(TAG_NAME_PROPERTY);
		for(Object obj : elements) {
			Element element = (Element)obj;
			String proName = element.attributeValue(TAG_NAME_PROPERTYNAME);
			String proValue = element.attributeValue(TAG_NAME_PROPERTYVALUE);
			
			setPropertyValue(mocker,proName,proValue);
			
		}
		return mocker;
	}


	@SuppressWarnings("unchecked")
	private static void setPropertyValue(Object mocker, String proName,String proValue) {
		
		try {
			PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(mocker, proName);
			System.out.println("Trying to process: " + mocker + " PropName = " + proName);
			Class c = desc.getPropertyType();
			
			Object tmps = null;
			if (c.getName().equals("java.lang.String")) {
				tmps = proValue;
			} else if (c.getName().equals("java.lang.Byte") || c.getName().equals("byte")) {
				tmps = MockPropUtil.getByte(proValue);
			} else if (c.getName().equals("java.lang.Short") || c.getName().equals("short")) {
				tmps = MockPropUtil.getShort(proValue);
			}else if (c.getName().equals("java.lang.Integer") || c.getName().equals("int")) {
				tmps = MockPropUtil.getInteger(proValue);
			} else if (c.getName().equals("java.lang.Long") || c.getName().equals("long")) {
				tmps = MockPropUtil.getLong(proValue);
			}else if (c.getName().equals("java.lang.Float") || c.getName().equals("float")) {
				tmps = MockPropUtil.getFloat(proValue);
			} else if (c.getName().equals("java.lang.Double") || c.getName().equals("double")) {
				tmps = MockPropUtil.getDouble(proValue);
			} 
			
//			else if (c.getName().equals("java.util.Date")) {
//				tmps = new Date();
//			} else if (c.getName().equals("java.math.BigDecimal")) {
//				tmps = new BigDecimal("0");
//			}
			if (tmps == null)
				System.out.println("Set value failed: " + mocker + " PropName=" + desc.getName());
			try {
				PropertyUtils.setProperty(mocker, desc.getName(), tmps);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	private static Document loadDocument(String configFilePath) {
		
		final SAXReader saxReader = new SAXReader();
		Document result;
		try {

			result = saxReader.read(new File(configFilePath));
			
		} catch (final DocumentException e) {
			logger.error("config file can't load successfully", e); //$NON-NLS-1$
			e.printStackTrace();
			throw new MockException(e);
		}
		
		return result;
	}

	
	public static void main(String args[]){
		
		MockConfiguration mockConfig = XMLConfigurationManager.load("E:/work/GPS/wookspace/mkgps/test/mock.xml");
		
	}
}
