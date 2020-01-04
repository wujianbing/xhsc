<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>选择收货地址</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/shouhuodzselt.css"/>
	</head>
	<body>
		<div class="box">
			<form action="">
				<ul>
					<c:forEach items="${recAddres}" var="recAddres">
					<li>
						<a><input type="radio" name="addres" id="" value="" /><span>${recAddres.recUser }</span>，<span>${recAddres.recPhone }</span><br />
						<span class="addres">${recAddres.province}${recAddres.city}${recAddres.area}${recAddres.address}</span></a>
						<a href="addressAdd?id="${recAddres.id}></a>
					</li>
					</c:forEach>
					<!-- <li>
						<input type="radio" name="addres" id="" value="" /><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span>
						<a href="shouhuodzadd.html"></a>
					</li>
					<li>
						<input type="radio" name="addres" id="" value="" /><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span>
						<a href="shouhuodzadd.html"></a>
					</li>
					<li>
						<input type="radio" name="addres" id="" value="" /><span>田花花</span>，<span>18569523541</span><br />
						<span class="addres">宁夏银川市金风区IBI育成中心1号楼1403</span>
						<a href="shouhuodzadd.html"></a>
					</li> -->
					
				</ul>
				<input type="button" value="新增地址" onclick="javascrtpt:window.location.href='addressAdd'" />
			</form>
			
			
		</div>
	</body>
</html>
