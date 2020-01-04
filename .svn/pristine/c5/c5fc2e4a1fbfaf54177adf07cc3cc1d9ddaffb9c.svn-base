<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>个人信息</title>
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/bootstrap.min.css"/>
		<!--日历样式-->
		<link rel="stylesheet" href="static/xhreception/css/calendar.css">
		<link rel="stylesheet" type="text/css" href="static/xhreception/css/personinfo.css"/>
		<script type="text/javascript" src="static/xhreception/js/jquery.js"></script>
	</head>
	<body>
		<h5 class="hed">基本信息</h5>
		<form action="updateUserInfo" method="post">
		<div class="box">
			<ul>
				<li>
					<span>头像</span>
					<img class="tx img-responsive img-circle" src="${xhUser.pic}" alt="" />
				</li>
				<li>
					<span>姓名</span>
					<input type="text" name="username" id="" value="${xhUser.username}" placeholder="请填写"/>
				</li>
				<li class="telnum">
					<span>手机号</span>
					<input type="text" name="phone" id="phone" value="${xhUser.phone}" placeholder="请填写"/>
				</li>
				<li>
					<span>性别</span>
					<select name="sex">
					  <option value="0" style="display:none" ${xhUser.sex==0?"selected":""}>保密</option>
					  <option value="1" ${xhUser.sex==1?"selected":""}>男</option>
					  <option value="2" ${xhUser.sex==2?"selected":""}>女</option>
					</select>
				</li>
				<li>
					<span>生日</span>
					<div class="row">
						<input type="text" class="room" name="birthday" id="startTime" value="${xhUser.birthday}" >
					</div>
				</li>
				<!-- <li>
					<span>地区</span>
					<input type="text" id="picker" placeholder="请选择"/>
				</li> -->
				<li>
					<span>收货地址</span>
					<a href="addressList"><input type="text" disabled="disabled"/></a>
				</li>
			</ul>
		</div>
			<input type="submit" value="确认修改">
		</form>
		
		
<!--日期日历插件-->
<script src="static/xhreception/js/calendar.js"></script>
<!--地区选择-->
		<script src="static/xhreception/js/picker.min.js"></script>
		<script type="text/javascript" src="static/xhreception/js/city.js"></script>
		<script type="text/javascript" src="static/xhreception/js/addres.js"></script>
<!--方法调用-->
<script>
$('.room').datePicker({
	okFunc: function (date) {
		console.log(date);
	}
});
/*手机号验证*/
$(".telnum").children("input").mouseout(function(){
	
	var phone = $(this).val();
    if(!(/^1[34578]\d{9}$/.test(phone))){ 
        alert("手机号码填错了哟");  
        return false; 
    } 
})

    

</script>
	</body>
</html>
