package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

/**
 * VehiclePic generated by hbm2java
 */
public class VehiclePic implements java.io.Serializable {

	private int vpId;
	private Vehicle vehicle;
	private VehiclePicTypeDic vehiclePicTypeDic;
	private PictureStore pictureStore;

	public VehiclePic() {
	}

	public VehiclePic(int vpId) {
		this.vpId = vpId;
	}

	public VehiclePic(int vpId, Vehicle vehicle,
			VehiclePicTypeDic vehiclePicTypeDic, PictureStore pictureStore) {
		this.vpId = vpId;
		this.vehicle = vehicle;
		this.vehiclePicTypeDic = vehiclePicTypeDic;
		this.pictureStore = pictureStore;
	}

	public int getVpId() {
		return this.vpId;
	}

	public void setVpId(int vpId) {
		this.vpId = vpId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehiclePicTypeDic getVehiclePicTypeDic() {
		return this.vehiclePicTypeDic;
	}

	public void setVehiclePicTypeDic(VehiclePicTypeDic vehiclePicTypeDic) {
		this.vehiclePicTypeDic = vehiclePicTypeDic;
	}

	public PictureStore getPictureStore() {
		return this.pictureStore;
	}

	public void setPictureStore(PictureStore pictureStore) {
		this.pictureStore = pictureStore;
	}

}
