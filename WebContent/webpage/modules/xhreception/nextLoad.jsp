<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>确认订单</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/qrorder.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript">
		    pushHistory();
		    window.addEventListener("popstate", function (e) {
		        // alert("我监听到了浏览器的返回按钮事件啦"); //根据自己的需求实现自己的功能 
		        window.history.go(-1);
		       /*  pushHistory(); //注，此处调用，可以用户一直停留着这个页面 */
		    }, false);
		
		    function pushHistory() {
		        var state = {
		            title: "title",
		            url: "#"
		        };
		        window.history.pushState(state, "title", "#");
		    }
		</script>
	</head>
	<script type="text/javascript">
		$(function(){
			$(".tbcont").children(".box").eq(1).hide();
			$(".tbbox").find("ul li").eq(0).click(function(){
				/*var index=$(this).index();*/
				$(this).addClass("select");
				$(this).children("img").attr("src","static/xhreception/images/ps2.png");
				$(this).siblings("li").children("img").attr("src","static/xhreception/images/zt1.png");
				$(this).siblings("li").removeClass("select");
				$(".tbcont").children(".box").eq(0).show();
				/* $(".tbcont").children(".box").eq(1).hide(); */
			})
			/* $(".tbbox").find("ul li").eq(1).click(function(){
				/*var index=$(this).index();*/
				/* $(this).addClass("select");
				$(this).siblings("li").removeClass("select");
				$(this).children("img").attr("src","static/xhreception/images/zt2.png");
				$(this).siblings("li").children("img").attr("src","static/xhreception/images/ps1.png");
				
				$(".tbcont").children(".box").eq(1).show();
				$(".tbcont").children(".box").eq(0).hide();
			}) */ 
		})
	</script>
	<body>
		<!--选择收货方式-->
		<div class="top">
			<div class="tbbox">
				<ul>
					<li class="psway select"><img src="static/xhreception/images/ps2.png" alt="" />商家配送</li>
					<!-- <li class="ztway"  onclick="alert('暂未开放');"><img src="static/xhreception/images/zt1.png" />到店自提</li> -->
				</ul>
			</div>
			<div class="tbcont">
				<div class="box">
					<!--无地址的时候，显示新建地址-->
					<c:if test="${recAddr == null}">
					<div class="tow">
					<a href="addressEdit">
					<img src="static/xhreception/images/tj.png"/>新建收货地址<m><img src="static/xhreception/images/zhankai.png" /></m>
					</a>
					</div>
					</c:if>
					<!--有地址的时候，默认显示第一个地址-->
					<c:if test="${recAddr != null}">
					 <div class="one">
						<a href="addressEdit">
						<input hidden="hidden" id="addrId" value="${recAddr.id}">
						<p>收货人：<span class="shpname">${recAddr.recUser}</span><span class="shptel">${recAddr.recPhone}</span></p>
						<p>收货地址：<span>${recAddr.province}${recAddr.city}${recAddr.area}${recAddr.address}</span></p>
						<m><img src="static/xhreception/images/zhankai.png" /></m>
						</a>
					</div> 
					</c:if>
				</div>
				<div class="box">
					<p><span>提货人</span><m>></m><a href="tihuorenedit.html">选择提货人</a></p>
					<p><span>提货地址</span><m>></m><a href="tihuodizhiselt.html">选择提货地址</a></p>
					<p><span>提货时间</span><m>></m><a href="tihuotimeselt.html">选择提货时间</a></p>
				</div>
			</div>
		</div>
		
		
		<!--订单商品信息-->
		<div class="gdinfo">
			<p class="sc">鲜花商城</p>
			<div class="gdlist">
				<div class="dbimg">
					<img class="img-responsive" src="${xhGoods.uploadFile.relaPath}" alt="" />
				</div>
				<div class="det">
					<h5>${xhGoods.name}</h5>
					<p>${nextLoad.remarks}</p>
					<p class="ppri"><sm>￥</sm><span class="price">${nextLoad.price}</span><span class="num">&times;${nextLoad.num}</span></p>
				</div>
			</div>
			
			<!-- <div class="gdlist">
				<div class="dbimg">
					<img class="img-responsive" src="images/gb5.png" alt="" />
				</div>
				<div class="det">
					<h5>小雏菊鲜花 家庭插花 厂家直发</h5>
					<p>希望、美满、团结、忠贞、永恒</p>
					<p class="ppri"><sm>￥</sm><span class="price">136.00</span><span class="num">&times;1</span></p>
				</div>
			</div> -->
		</div>
		<!--优惠券-->
		<div></div>
		<!--配送方式-->
		<p class="ptm">配送方式<span>普通快递 免运费</span></p>
		<p class="ptm">买家留言<input type="text" id="remarks"  placeholder="建议留言前与商家沟通确认"/></p>
		<!--商品金额、运费-->
		<p class="spje">商品金额<span>￥<m>${nextLoad.amount}</m></span></p>
		 <p class="yf">运费<span>+￥<m>0.00</m></span></p>
		<p class="hj">合计：<b>￥<m>${nextLoad.amount}</m></b></p>
		<!--bottom-->
		<div class="bottom">
			<p>合计：<b>￥<m id="amount">${nextLoad.amount}</m></b></p>
			<button id="payOrder" onclick="saveOrder()">提交订单</button>
			<button id="wxpay" onclick="wxpay()">支付</button>
			<input type="text" hidden="hidden" id="oid" />
			
		</div>
	</body>
	<script type="text/javascript">
		$(document).ready(function (){
			$("#wxpay").hide();
			$("#payOrder").show();
		});
		
		
		function saveOrder(){
			debugger;
			/* var province = ${recAddr.province};
			if(province != '银川'){
				alert("当前支持区域仅限银川,请选择正确地址后下单");
				return false;
			} */
			var recId = $("#addrId").val();
			if(recId === null || recId === '' || recId === undefined){
				alert("请添加地址");
				return false;
			}
			var amount = $("#amount").text();
			var remarks = $("#remarks").val();
			
			$.post("addOrder",{
			"recId":recId,
			"amount":amount,
			"remarks":remarks
			},
			function(message){
				if(message.code == -1){
					alert(message.message);
					window.location.href = "index";
				}else if(message.code == 1){
					var oid = message.oid;
					$("#oid").val(oid);
					gowxpay();
					}
				}
			)
		}
		
		
		function gowxpay(){
			$("#wxpay").show();
			$("#payOrder").hide();
		}
		
		function wxpay(){
			var oid = $("#oid").val();
			 window.location.href="wxpay/wxpay?oid="+oid; 
			/* $.post("wxpay/wxpay",{
				"oid" : oid
			}); */
		}
	</script>
</html>
