package com.jeeplus.modules.xhreception.pay;

import java.math.BigDecimal;
import java.util.Date;



public class PaymentInfo {
		private String id;
		private String oid;
		private String payment_channel;
		private String trade_type;
		private String payment_status;
		private Date complete_time;
		private String trade_no;
		private String client;
		private Date createDate;
		private Date expireDate;
		private BigDecimal amount;//付款金额
		
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getOid() {
			return oid;
		}
		public void setOid(String oid) {
			this.oid = oid;
		}
		public String getPayment_channel() {
			return payment_channel;
		}
		public void setPayment_channel(String payment_channel) {
			this.payment_channel = payment_channel;
		}
		public String getTrade_type() {
			return trade_type;
		}
		public void setTrade_type(String trade_type) {
			this.trade_type = trade_type;
		}
		public String getPayment_status() {
			return payment_status;
		}
		public void setPayment_status(String payment_status) {
			this.payment_status = payment_status;
		}
		public Date getComplete_time() {
			return complete_time;
		}
		public void setComplete_time(Date complete_time) {
			this.complete_time = complete_time;
		}
		public String getTrade_no() {
			return trade_no;
		}
		public void setTrade_no(String trade_no) {
			this.trade_no = trade_no;
		}
		public String getClient() {
			return client;
		}
		public void setClient(String client) {
			this.client = client;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getExpireDate() {
			return expireDate;
		}
		public void setExpireDate(Date expireDate) {
			this.expireDate = expireDate;
		}
		public boolean valid(){
			Date now = new Date();
			return expireDate.after(now);
		}
		
		
}
