<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>订单详情管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content" style="padding-top: 5px;padding-bottom: 5px">
		<h3 style="margin-left: 5px;">订单信息 </h3>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-10 active"><label class="pull-right">订单编号：</label></td>
					<td class="width-15">
					${order.orderNo}
					</td>
					<td class="width-10 active"><label class="pull-right">下单用户：</label></td>
					<td class="width-15">
					${order.xhUser.username}
					</td>
					<td class="width-10 active"><label class="pull-right">接收人：</label></td>
					<td class="width-15">
					${order.recUser}
					</td>
					<td class="width-10 active"><label class="pull-right">电话：</label></td>
					<td class="width-15">${order.recPhone}
					</td>
				</tr>
				<tr>
					<td class="width-10 active"><label class="pull-right">下单地址：</label></td>
					<td class="width-15">${order.recAddr}
					</td>
					<td class="width-10 active"><label class="pull-right">下单时间：</label></td>
					<td class="width-15"><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="width-10 active"><label class="pull-right">订单金额：</label></td>
					<td class="width-15">${order.amount}
					</td>
					<td class="width-10 active"><label class="pull-right">支付方式：</label></td>
					<td class="width-15">${fns:getDictLabel(order.payMethod, 'pay_method', '')}
					</td>
				</tr>
				<tr>
					<td class="width-10 active"><label class="pull-right">支付状态：</label></td>
					<td class="width-15">${fns:getDictLabel(order.payStatus, 'pay_status', '')}
					</td>
					<td class="width-10 active"><label class="pull-right">订单状态：</label></td>
					<td class="width-15">${fns:getDictLabel(order.orderStatus, 'order_status', '')}
					</td>
					<td class="width-10 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-15">${order.remarks}
					</td>
					<td class="width-10 active"><label class="pull-right">订单评价：</label></td>
					<td class="width-15">${order.comment}
					</td>
				</tr>
		 	</tbody>
		</table>
	</div>
	<div class="wrapper wrapper-content" style="padding-top: 0px">
	<div class="ibox">
	<div class="ibox-title">
		<h5>订单详情列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>商品数量</th>
				<th>商品单价</th>
				<th>商品详情</th>
				<th>包月时长</th>
				<th>到期时间</th>
				<th>商品类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderDetail">
			<tr>
				<td>
					${orderDetail.gName}
				</td>
				<td>
					${orderDetail.num}
				</td>
				<td>
					${orderDetail.price}
				</td>
				<td>
					${orderDetail.info}
				</td>
				<td>
					${orderDetail.mTime}
				</td>
				<td>
					<fmt:formatDate value="${orderDetail.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(orderDetail.status, 'orderDetail', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>