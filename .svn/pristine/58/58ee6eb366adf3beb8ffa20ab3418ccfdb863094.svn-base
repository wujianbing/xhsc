<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>积分管理</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/jfmanage.css"/>
	</head>
	<body>
		<div class="top">
			<h4>可用积分</h4>
			<h3>${xhUser.integral}</h3>
		</div>
		
		<div class="tpline">
			<p class="pull-left"><a href="integral">积分商城</a></p>|
			<p class="pull-right">兑换记录</p>
		</div>
		
		<div class="jfjl">
			<div class="tit">兑换记录</div>
			<ul>
			<c:forEach items="${pointInfos}" var="pointInfos">
				<li>
					<p>兑换${pointInfos.pName}成功<span class="pull-right">-${pointInfos.pPoint}</span></p>                                        
					<p><fmt:formatDate value="${pointInfos.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
				</li>
				</c:forEach>
				<!-- <li>
					<p>购买【包月鲜花】获得积分<span class="pull-right">+50</span></p>                                        
					<p>2019-4-25</p>
				</li>
				<li>
					<p>购买【包月鲜花】获得积分<span class="pull-right">+50</span></p>                                        
					<p>2019-4-25</p>
				</li>
				<li>
					<p>购买【包月鲜花】获得积分<span class="pull-right">+50</span></p>                                        
					<p>2019-4-25</p>
				</li>
				<li>
					<p>购买【包月鲜花】获得积分<span class="pull-right">+50</span></p>                                        
					<p>2019-4-25</p>
				</li> -->
			</ul>
		</div>
	</body>
</html>
