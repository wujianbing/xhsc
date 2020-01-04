<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>去凑团</title>
		<link rel="stylesheet" type="text/css"	href="static/xhreception/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/ptgo.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/arguments.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript" src="static/xhreception/js/bootstrap.js"></script>
	</head>
	<body >
		<div class="box">
			<img src="static/xhreception/images/byit6.png"/>
			<div class="bright">
				<p class="name">${xhBuyer.xhGoods.name}<span>${xhBuyer.gbPrice}</span>元#<span>拼团</span>#</p>
				<p class="price">￥<span>${xhBuyer.xhGroups.lPrice}</span>起<s>￥<span>${xhBuyer.xhGoods.price}</span></s></p>
			</div>
		</div>
		<p class="ptgz"><a href="groupRule">拼团规则：邀请${xhGroups.maxNum}人参团，人数不足自动退团</a></p>
		
		<!--拼团剩余时间-->
		<div class="pttime">
			<div class="tile">
				<p><span>——</span> 拼团剩余时间  <span>——</span></p>
				<p class="time" id="lefttime_0" time="${xhBuyer.sTime}"><span id="hour"></span>:<span id="mun"></span>:<span id="sm"></span></p>
				<div class="people">
					<c:forEach items="${xhUsers}" var="xhUsers">
					<p class="tz"><img src="${xhUsers.pic}" alt="" /></p>
					</c:forEach>
					<p class="ty"></p>
				</div>
				<p class="snum">仅剩<span>${xhGroups.maxNum-xhBuyer.hasNum}</span>人，快来加入我的团吧！</p>
				
	<p class="ct">
		<button id="kt" data-toggle="modal" data-target="#div1">
			<span>下单凑团</span>
		</button>
	</p>
		</div>
		</div>
	<!------开团弹出窗口---------------------------------------------------------------------------------  -->	
<!--弹出窗立即购买  -->
		<!-- <div class="back">
		<div class="overfl">
		<div id="close">
					X
		</div>
		<div id="div1"> -->
		<div class="div1 modal fade" id="div1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				
			</div>
		<div class="gdinfo">
			<img src="${xhGoods.uploadFile.relaPath}"/>
			<div class="gdname">
				<h5 id="${xhGoods.id}">${xhGoods.name}</h5>
				<p class="iteminfo_price"><m>￥</m><span class="sys_item_price">${xhGoods.price}-${xhGoods.maxPrice}</span></p>
			</div>
		</div>
		<div class="iteminfo_buying">
		<!--规格属性-->
			<div class="sys_item_spec">
				<form action="groupNextLoad" method="post" id="form1" onsubmit="return cc()">
				 <input hidden="hidden" value="${xhGoods.id}" name="gid">
				 <input hidden="hidden" value="${xhBuyer.groupNo}" name = "groupNo"> 
				<c:if test="${!empty xhRules}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>规格选择：</dt>
					<dd>
						<c:forEach items="${xhRules}" var="xhRules">
						<label><input name="rid" price="${xhRules.unitPrice}" type="radio" value="${xhRules.id}" class="r1" />${xhRules.rule}</label>
						</c:forEach>
					</dd>
				</dl>
				</c:if>
				<c:if test="${!empty xhColors}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>颜色偏好：</dt>
					<dd>
						<c:forEach items="${xhColors}" var="xhColors">
						<label><input price="0" name="sid" type="radio" value="${xhColors.id}" />${xhColors.color}</label>
						</c:forEach>
					</dd>
				</dl>
				</c:if>
				<c:if test="${!empty xhTimes}">
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt>收花时间：</dt>
					<dd>
						<c:forEach items="${xhTimes}" var="xhTimes">
						<label><input name="tid" price="0" type="radio" value="${xhTimes.id}"  class=""/>${xhTimes.xhTime}</label>
						</c:forEach>
					</dd>
				</dl>
				</c:if>
				<dl class="clearfix iteminfo_parameter sys_item_specpara">
					<dt class="gnum">购买数量：</dt>
					<dd class="gopnm">
						<div class="optnum">
						<div class="optnumdec left">-</div>
						<input id="num0" type="text" value="1" class="shang" name="num" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
						<div class="optnumadd left">+</div>
					</div>
					</dd>
				</dl>
				<!-- <dl class="clearfix iteminfo_parameter sys_item_specpara mb-50">
					<dt class="bzhu ">卡片留言：</dt>
					<dd class="bzhucotn">
						<input id="message" type="text" name="remarks"/>
					</dd>
				</dl> -->
				<input id="kt" type="submit" value="下一步" />
				</form>
				</div>
		      </div>
			</div>
		  </div>
		</div>	
<!------------------------------------------------------------------------------------------------  -->	
</body>
<script type="text/javascript">
	 /*立即開團*/
				/* var btn = document.getElementById('kt');
				
				var close = document.getElementById('close');
				var div = $('.back');
				var form1 = document.getElementById('div1');
				
				
				btn.onclick = function show() {
					
					div.fadeIn(1000);
					form1.style.display="block"; */
					
					var o = 0;
				//商品规格选择
				$(".sys_item_spec .sys_item_specpara").each(function(){
					var i=$(this);
					var p=$(this).find("label");
					p.click(function(){
						var s = 0;
						var m = 0;
						$(this).addClass("selected").siblings("label").removeClass("selected");
						$(this).addClass("selected").siblings("label").children("input");
						s=$(this).children("input").attr("price");
						i.attr("data-val",s);
						if(s != 0 && typeof s!="undefined"){
								o = i.attr("data-val")*100/100;
								$("#div1 ").find(".sys_item_price").text(o);
							}
						})
					});	
				/* } */
				
				 
				 
				
				/* close.onclick = function close() {
					div.fadeOut(1000);
					
				}	
				
				
			$().each(function(){
			}) */
			 
				 /*购买数量增加*/
					$(".optnumadd").click(function(){
						var num=$(this).parent().children("input");
						num.val(parseInt(num.val())+1);
						$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
						
					})
					/*数量减少*/
					$(".optnumdec").click(function(){
						var num=$(this).parent().children("input");
						if(num.val()>0){
							num.val(parseInt(num.val())-1);
							$(this).parent().siblings("p.ppri").children("span.num").text('x'+num.val());
						}
						else{
							num.val(0);
						}
						
					});		
</script>

<script type="text/javascript" language="javascript"> 
		function cc(){
		 
		for(var i = 1;i<2;i++){ 
		var num = $(".r"+i).length; 
		var x = 0; 
		var bool = true; 
		for(var j=0;j<num;j++){ 
		var aa = $(".r"+i+":eq("+j+"):checked").val();    
		if(aa == null){ 
		x++ 
		}    
		} 
		if(x == num ){
		alert("请选择参数"); 
		bool = false; 
		return bool; 
		}    
		
		}
		var shuliang = $("#num0").val();
		 if(shuliang == 0){
		 	alert("请选择参数");
		 	bool=false;
		 	return false;
		 }
		if(bool){
		  
		return true; 
		} 
		} 
</script>

<script type="text/javascript" language="javascript"> 
		function aa(){
		debugger; 
		for(var i = 1;i<2;i++){ 
		var num = $(".r"+i).length; 
		var x = 0; 
		var bool = true; 
		for(var j=0;j<num;j++){ 
		var aa = $(".r"+i+":eq("+j+"):checked").val();    
		if(aa == null){ 
		x++ 
		}    
		} 
		if(x == num ){ 
		alert("请选择参数"); 
		bool = false; 
		return bool; 
		}    
		
		}
		var shuliang = $("#num1").val();
		 if(shuliang == 0){
		 	alert("请选择参数");
		 	bool=false;
		 	return false;
		 } 
		if(bool){
		alert("加入购物车成功");  
		return true; 
		} 
		} 
</script>	

<script type="text/javascript">
	function showTime(tuanid, time_distance) {
		this.tuanid = tuanid;
		//PHP时间是秒，JS时间是微秒 
		this.time_distance = time_distance * 1000;
	}
	showTime.prototype.setTimeShow = function() {
		var timer = $("#lefttime_" + this.tuanid);
		var str_time;
		var int_day,
			int_hour,
			int_minute,
			int_second;
		time_distance = this.time_distance;
		this.time_distance = this.time_distance - 1000;
		if (time_distance > 0) {
			int_day = Math.floor(time_distance / 86400000);
			time_distance -= int_day * 86400000;
			int_hour = Math.floor(time_distance / 3600000);
			time_distance -= int_hour * 3600000;
			int_minute = Math.floor(time_distance / 60000);
			time_distance -= int_minute * 60000;
			int_second = Math.floor(time_distance / 1000);
			if (int_hour < 10)
				int_hour = "0" + int_hour;
			if (int_minute < 10)
				int_minute = "0" + int_minute;
			if (int_second < 10)
				int_second = "0" + int_second;
			/* str_time = int_day + "天" + int_hour + "小时" + int_minute + "分钟" + int_second + "秒"; */
			$("#hour").text(int_hour);
			$("#mun").text(int_minute);
			$("#sm").text(int_second);
		/* 	timer.text(int_second); */
			var self = this;
			setTimeout(function() {
				self.setTimeShow();
			}, 1000); 
		} else {
			timer.text("团购结束");
			return;
		}
	}
</script>
<script type="text/javascript">
	var p = $("#lefttime_0").attr("time");
	var st = new showTime(0, p);
	//st.tuanid = 1; 
	//st.time_distance = 10000; 
	st.setTimeShow();
</script>


</html>
