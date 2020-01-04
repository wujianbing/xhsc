<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>编辑收货地址</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/shouhuodzadd.css"/>
		
	</head>
	<body>
		<div class="box">
			<form action="addressSave" method="get" id="myForm">
				<ul>
					<li>姓名<input type="text"  placeholder="收货人姓名" name="recName" value=""/></li>
					<li>电话<input type="text"  placeholder="收货人电话" name="recPhone"/></li>
					<li>地区<input type="text" id="picker" name="picker" placeholder="选择省/市/区"/></li>
					<li>详细地址<input type="text" name="address"  placeholder="街道门牌、楼层房间号"/></li>
				</ul>
				<input onclick="onLoad()" type="button" value="保存并使用"/>
				<input class="delete" type="button" value="删除收货地址"/>
			</form>
		</div>
		<script src="static/xhreception/js/picker.min.js"></script>
		<script type="text/javascript" src="static/xhreception/js/city.js"></script>
		<script type="text/javascript" src="static/xhreception/js/addres.js"></script>
		<script type="text/javascript">
			function onLoad(){
				document.getElementById("myForm").submit();
			}
			
		</script>
	</body>
</html>
