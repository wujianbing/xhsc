<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>待评价的订单</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/appra.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				   $('li').on('mouseenter',function ( ) {
				    //需求1:鼠标移入后,当前的li标签和前面的li标签显示红色,后面的li标签显示灰色
				    $(this).css({"background":"#FE5479"}).prevAll().css({"background":"#FE5479"}).end().nextAll().css({"background":"#d0d0d0"});
				   }).on('mouseleave',function ( ) {
				    $('li').css({"background":"#d0d0d0"});
				    //找到索引的li,给这个li和它前面的li设置显示红色
				    $('li[index="dianji"]').css({"background":"#FE5479"}).prevAll().css({"background":"#FE5479"});
				   }).on('click',function ( ) {
				    //给点击的li添加索引,其余的去除索引
				     $(this).attr('index','dianji').siblings().removeAttr('index');
				     /*返回打分*/
				     /*return ($(this).index()+1);*/
				   })
			})
			
		</script>
	</head>
	<body>
		<div class="box">
				<div class="ordlst">
					<div class="tpline">
						<p class="pull-left">订单编号:<span>${xhOrder.orderNo}</span></p>
						<p class="pull-right">买家已付款</p>
					</div>
					<c:forEach items="${details}" var="details">
					<div class="ordinfo">
						<img class="img-responsive pull-left" src="${details.xhGoods.uploadFile.relaPath}"/>
						<div class="gdinfo pull-right">
							<p>${details.gName}</p>
							<p>${details.info}</p>
							<p><b class="pull-left">￥<span>${details.price}</span></b><m class="pull-right">&times;${details.num}</m></p>
						</div>
					</div>
					</c:forEach>
					<div class="pjopt">
						<form action="appraDetail" method="get">
							<div class="star">
							<input hidden="hidden" name="oid" value="${xhOrder.id}">
								<p class="pull-left">请打分</p>
								<ul class="pull-left">
									<li>★</li>
									<li>★</li>
									<li>★</li>
									<li>★</li>
									<li>★</li>
								</ul>
							</div>
							<div class="pjcontent">
								<textarea name="comment" rows="" cols="" placeholder="写下您对商家的评价吧~~"></textarea>
							</div>
							<input type="submit" value="提交"/>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>
