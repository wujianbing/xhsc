package com.jeeplus.modules.xhreception.domain;

import java.io.Serializable;

public class ShopDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String num;
	private String carId;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	
}
