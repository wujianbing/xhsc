<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/echarts-2.2.7/build/dist/echarts-all.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		     WinMove();
		});
	</script>
	<script type="text/javascript"></script>
	<style type="text/css">
	.ibox-content{
	height:250px;
	}
	.h360{
	height:360px;
	}
.ibox-content li{
height:100px;
line-height: 100px;
width:32%;
float:left;
list-style:none;
font-size:20px;
margin:5px;
padding-left: 50px;
color: #fff;
}
.ibox-content li:nth-child(1){
background:#4caf51;
}
.ibox-content li:nth-child(2){
background:#f44437;
}
.ibox-content li:nth-child(3){
background:#00bcd4;
}
.ibox-content li:nth-child(4){
background:#ff9803;
}
.ibox-content li:nth-child(5){
background:#2298f3;
}
.ibox-content li:nth-child(6){
background:#9d27b0;
}
</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		
	</div>
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>平台数据统计</h5><span class="label label-primary">K+</span>
			</div>
			<div class="ibox-content">
				<ul>
					<li>商品总数：<span class="timer count-title" id="count-number" data-to="${a }" data-speed="1500" style="color: #ffffff; font-weight: blod;"></span></li>
					<li>当日销售额：<span data-to="${f }" data-speed="1500" style="color: #ffffff"; font-weight: blod;">${f }</span></li>
					<li>待付款总数：<span class="timer count-title" id="count-number" data-to="${c }" data-speed="1500" style="color: #ffffff; font-weight: blod;"></span></li>
				
					<li>订单总数：<span class="timer count-title" id="count-number" data-to="${b }" data-speed="1500" style="color: #ffffff; font-weight: blod;"></span></li>
					<li>平台总销售额：<span data-to="${e }" data-speed="1500" style="color: #ffffff; font-weight: blod;">${e }</span></li>
					<li>已付款总数：<span class="timer count-title" id="count-number" data-to="${d }" data-speed="1500" style="color: #ffffff; font-weight: blod;"></span></li>
				</ul>
				
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>近七日销售额统计</h5> <span class="label label-primary">K+</span>
			</div>
			<div class="ibox-content h360">
				<div id="meetingBar" style="width: 100%; height: 360px;"></div>
				<script type="text/javascript">
					var myChart = echarts.init(document.getElementById('meetingBar')); 
					option = {
						    /* title : {
						        text: '某地区蒸发量和降水量',
						        subtext: '纯属虚构'
						    }, */
						    tooltip : {
						        trigger: 'axis'
						    },
						    legend: {
						        data:['销售额']
						    },
						    toolbox: {
						        show : true,
						        feature : {
						            mark : {show: true},
						            /* dataView : {show: true, readOnly: false}, */
						            magicType : {show: true, type: ['line', 'bar']},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    calculable : true,
						    xAxis : [
						        {
						            type : 'category',
						            data : ${list1 }/* ,
						            axisLine: {//x轴样式
						                show: true,
						                lineStyle: {
						                  color: "red",
						                  width: 1,
						                  type: "solid"
						                }
						              } */
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value'
						        }
						    ],
						    series : [
						        {
						            name:'销售额',
						            type:'bar',
						            data:${list2 },
						          
						            markLine : {
						                data : [
						                    {type : 'average', name : '平均值'}
						                ]
						            },
						            itemStyle: {//柱样式
						                normal: {
						                  barBorderRadius: [5,5,0,0],
						                  color: '#ea5058',
						                  opacity: 1,
						                }
						              }
						        }
						    ]
						};
					myChart.setOption(option); 
				</script>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/number/jquery.js"></script>
	<script src="${ctxStatic}/number/index.js"></script>
	<!-- data-speed 属性为时间长短 -->
</body>
</html>