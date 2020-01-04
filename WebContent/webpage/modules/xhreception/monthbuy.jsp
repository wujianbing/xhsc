<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>包月鲜花</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/base/base.css"/>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/byitem.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
		<script type="text/javascript" src="static/xhreception/js/layer/layer.js"></script>
	</head>
	<body>
		<header>
			<%@ include file="fansbase.jsp" %>
		</header>
		<nav>
			<ul>
				<c:forEach items="${xhFloors}" var="xhFloors">
					<li><a href="${xhFloors.url}">${xhFloors.name}</a></li>
				</c:forEach>
			</ul>
		</nav>
		<section>
			<div class="byitemlist">
			<ul>
				<c:forEach items="${xhMonthbuys}" var="xm">
				<li>
					<img class="img-responsive" src="${xm.xhGoods.uploadFile5.relaPath}"/>
					<div class="box">
						<div class="bleft">
							<img src="${xm.xhGoods.uploadFile1.relaPath}"/>
							<img src="${xm.xhGoods.uploadFile2.relaPath}"/>
							<img src="${xm.xhGoods.uploadFile3.relaPath}"/>
							<img src="${xm.xhGoods.uploadFile4.relaPath}"/>
						</div>
						<div class="bright">
							<h1>${xm.xhGoods.name}</h1>
							<p class="ill">${xm.mTitle }<br />${xm.lTitle}</p>
							<p class="price"><span>￥</span><span>${xm.mPrice}</span><!-- <m>（1月4束）</m> --></p>
							<p class="bby"><a href="monthDetail?id=${xm.id}&gid=${xm.xhGoods.id}">立即购买</a></p>
						</div>
					</div>
				</li>
				</c:forEach>
				<!-- <li>
					<img class="byitbg img-responsive" src="static/xhreception/images/byitbg2.png"/>
					<div class="box">
						<div class="bleft">
							<img src="static/xhreception/images/byit5.png"/>
							<img src="static/xhreception/images/byit6.png"/>
							<img src="static/xhreception/images/byit7.png"/>
							<img src="static/xhreception/images/byit8.png"/>
						</div>
						<div class="bright">
							<h1>缤纷物语</h1>
							<p class="ill">5-6种花材精心搭配<br />新用户送花瓶</p>
							<p class="price"><span>￥</span><span>169</span><m>（1月4束）</m></p>
							<p class="bby"><a href="order1.html">立即购买</a></p>
						</div>
					</div>
				</li> -->
			</ul>
			</div>
		</section>
		<footer>
			<ul>
				<%@ include file="webBase.jsp" %>
			</ul>
		</footer>
		<script type="text/javascript" src="static/xhreception/js/base.js"></script>
	</body>
</html>
