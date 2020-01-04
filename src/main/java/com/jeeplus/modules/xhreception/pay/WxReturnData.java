package com.jeeplus.modules.xhreception.pay;

/**
 * wx接口返回数据
 * link  ： https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 * @author 
 * @description 
 *	
 */
public class WxReturnData {
		//返回状态码 SUCCESS/FAIL
		public String return_code;
		//返回信息
		public String return_msg;
		//公众账号ID
		public String appid;
		//商户号
		public String mch_id;
		//随机字符串
		public String nonce_str;
		//设备号
		public String device_info;
		//签名
		public String sign;
		//业务结果 SUCCESS/FAIL
		public String result_code;
		//错误代码
		public String err_code;
		//错误代码描述
		public String err_code_des;
		//交易类型
		public String trade_type;
		//预支付交易会话标识
		public String prepay_id;
		//二维码链接
		public String code_url;
		
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
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getNonce_str() {
			return nonce_str;
		}
		public void setNonce_str(String nonce_str) {
			this.nonce_str = nonce_str;
		}
		public String getDevice_info() {
			return device_info;
		}
		public void setDevice_info(String device_info) {
			this.device_info = device_info;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public String getResult_code() {
			return result_code;
		}
		public void setResult_code(String result_code) {
			this.result_code = result_code;
		}
		public String getErr_code() {
			return err_code;
		}
		public void setErr_code(String err_code) {
			this.err_code = err_code;
		}
		public String getErr_code_des() {
			return err_code_des;
		}
		public void setErr_code_des(String err_code_des) {
			this.err_code_des = err_code_des;
		}
		public String getTrade_type() {
			return trade_type;
		}
		public void setTrade_type(String trade_type) {
			this.trade_type = trade_type;
		}
		public String getPrepay_id() {
			return prepay_id;
		}
		public void setPrepay_id(String prepay_id) {
			this.prepay_id = prepay_id;
		}
		public String getCode_url() {
			return code_url;
		}
		public void setCode_url(String code_url) {
			this.code_url = code_url;
		}
		public String getMch_id() {
			return mch_id;
		}
		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("WxReturnData [return_code=");
			builder.append(return_code);
			builder.append(", return_msg=");
			builder.append(return_msg);
			builder.append(", appid=");
			builder.append(appid);
			builder.append(", mch_id=");
			builder.append(mch_id);
			builder.append(", nonce_str=");
			builder.append(nonce_str);
			builder.append(", device_info=");
			builder.append(device_info);
			builder.append(", sign=");
			builder.append(sign);
			builder.append(", result_code=");
			builder.append(result_code);
			builder.append(", err_code=");
			builder.append(err_code);
			builder.append(", err_code_des=");
			builder.append(err_code_des);
			builder.append(", trade_type=");
			builder.append(trade_type);
			builder.append(", prepay_id=");
			builder.append(prepay_id);
			builder.append(", code_url=");
			builder.append(code_url);
			builder.append("]");
			return builder.toString();
		}
		
		
}
