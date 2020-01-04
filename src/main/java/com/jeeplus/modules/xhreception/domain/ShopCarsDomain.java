package com.jeeplus.modules.xhreception.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.jeeplus.modules.xhshoper.entity.XhShopcar;

public class ShopCarsDomain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal amount;
	private List<XhShopcar> objects;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public List<XhShopcar> getObjects() {
		return objects;
	}
	public void setObjects(List<XhShopcar> xhShopcars) {
		this.objects = xhShopcars;
	}
}
