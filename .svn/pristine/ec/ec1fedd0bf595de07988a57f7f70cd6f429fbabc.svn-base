package com.jeeplus.modules.xhreception.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxRefund {
	public static Logger LOGGER = LoggerFactory.getLogger(WxRefund.class);
	private String appid;
	private String mch_id;
	private String nonce_str;
	
	private String out_trade_no;
	private String out_refund_no;
	private String sign;
	private String sign_type;
	private int total_fee;
	private int refund_fee;
	private String refund_fee_type;

	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public String  makeStringa() {
		StringBuilder builder = new StringBuilder();
		builder.append("appid=");
		builder.append(appid);
		builder.append("&mch_id=");
		builder.append(mch_id);
		builder.append("&nonce_str=");
		builder.append(nonce_str);
		builder.append("&out_refund_no=");
		builder.append(out_refund_no);
		builder.append("&out_trade_no=");
		builder.append(out_trade_no);
		builder.append("&refund_fee=");
		builder.append(refund_fee);
		builder.append("&refund_fee_type=");
		builder.append(refund_fee_type);
		builder.append("&total_fee=");
		builder.append(total_fee);
		return builder.toString();
	}
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("<xml><appid><![CDATA[");
		builder.append(appid);
		builder.append("]]></appid><mch_id><![CDATA[");
		builder.append(mch_id);
		builder.append("]]></mch_id><nonce_str><![CDATA[");
		builder.append(nonce_str);
		builder.append("]]></nonce_str><out_refund_no><![CDATA[");
		builder.append(out_refund_no);
		builder.append("]]></out_refund_no><out_trade_no><![CDATA[");
		builder.append(out_trade_no);
		builder.append("]]></out_trade_no><refund_fee><![CDATA[");
		builder.append(refund_fee);
		builder.append("]]></refund_fee><refund_fee_type><![CDATA[");
		builder.append(refund_fee_type);
		builder.append("]]></refund_fee_type><total_fee><![CDATA[");
		builder.append(total_fee);
		builder.append("]]></total_fee><sign><![CDATA[");
		builder.append(sign);
		builder.append("]]></sign>");
		builder.append("</xml>");
		LOGGER.info("stringSignTemp={}",builder.toString());
		return builder.toString();
	}
	
	public void sign(){
		nonce_str(0);
		String stringSignTemp = makeStringa()+"&key="+PayUtils.KEY;
		LOGGER.info("stringSignTemp={}",stringSignTemp);
		String signValue = BaseUtil.md5(stringSignTemp).toUpperCase();
		LOGGER.info("signValue={}",signValue);
		sign = signValue;
	}
	private void nonce_str(int length){
//		StringBuilder builder = new StringBuilder(length);  
//		for (int i = 0; i < length; i++) {
//			builder.append(RandomStringUtils.randomNumeric(1));
//		}
//		nonce_str = builder.toString();
		nonce_str = "123456";
	}
}
