package com.jeeplus.modules.xhreception.pay;

public class WxRefundData {
	private String return_code;
	public String return_msg;
	

	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxReturnData [return_code=");
		builder.append(return_code);
		builder.append(",return_msg=");
		builder.append(return_msg);
		builder.append("]");
		return builder.toString();
	}
	
}
