package com.gps.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gps.Message;
import com.gps.orm.FVehicleBasic;
import com.gps.orm.Vehicle;
import com.gps.util.Util;

public class VehicleBasicUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			Map<String, FVehicleBasic> map = new HashMap<String, FVehicleBasic>();
			for(FVehicleBasic fvb : v.getFVehicleBasics()){
				map.put(fvb.getFeeName(), fvb);
			}
			String[] names = getArray("feeName");
			String[] feeExpireDates = getArray("feeExpireDate");
			String[] amounts = getArray("amount");
			String[] comments = getArray("comment");
			if (names != null && feeExpireDates!=null && amounts!=null && comments!=null 
					&& names.length==feeExpireDates.length 
					&& names.length==amounts.length
					&& names.length==comments.length ) {
				for (int i=0;i<names.length;i++){
					String name = names[i];
					if(name!=null && !name.equals("")){
						FVehicleBasic fvb = map.get(name);
						if(fvb != null){
//							fvb.setFeeName(name);
							if(amounts[i].length()>0)
								fvb.setAmount(Double.parseDouble(amounts[i]));
							if(feeExpireDates[i].length()>0)
								fvb.setFeeExpireDate(Util.parseDate(feeExpireDates[i]));
							if(comments[i].length()>0)
								fvb.setComment(comments[i]);
//							fvb.setVehicle(v);
//							set.add(fvb);
						}
					}
				}
			}
			getServiceLocator().getVehicleService().updateVehicle(v);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("vehicleId", String.valueOf(v.getVehicleId()));
	}
}
