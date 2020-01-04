<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>已退款订单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#createDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        laydate({
	            elem: '#updateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>订单信息列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="xhOrder" action="${ctx}/xhorder/xhOrder/refunded" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>订单号：</span>
				<form:input path="orderNo" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/>
			 <span>开始时间：</span>
				<input id="createDate" name="createDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${xhOrder.createDate}" pattern="yyyy-MM-dd"/>"/> 
			 <span>结束时间：</span>
				<input id="updateDate" name="updateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${xhOrder.updateDate}" pattern="yyyy-MM-dd"/>"/> 
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%-- <shiro:hasPermission name="xhorder:xhOrder:add">
				<table:addRow url="${ctx}/xhorder/xhOrder/form" title="订单信息"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhorder:xhOrder:edit">
			    <table:editRow url="${ctx}/xhorder/xhOrder/form" title="订单信息" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhorder:xhOrder:del">
				<table:delRow url="${ctx}/xhorder/xhOrder/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission> --%>
			<%-- <shiro:hasPermission name="xhorder:xhOrder:import">
				<table:importExcel url="${ctx}/xhorder/xhOrder/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhorder:xhOrder:export">
	       		<table:exportExcel url="${ctx}/xhorder/xhOrder/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission> --%>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>订单编号</th>
				<th>下单用户</th>
				<th>接收人</th>
				<th>接收人电话</th>
				<th>下单地址</th>
				<th>订单金额</th>
				<th>支付方式</th>
				<th>支付状态</th>
				<th>订单状态</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhOrder">
			<tr>
				<td> <input type="checkbox" id="${xhOrder.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看订单详情', '${ctx}/orderdetail/orderDetail/list?oid=${xhOrder.id}','1000px', '600px')">
					${xhOrder.orderNo}
					</a></td>
				<td>
					${xhOrder.xhUser.username}
				</td>
				<td>
					${xhOrder.recUser}
				</td>
				<td>
					${xhOrder.recPhone}
				</td>
				<td>
					${xhOrder.recAddr}
				</td>
				<td>
					${xhOrder.amount}
				</td>
				<td>
					${fns:getDictLabel(xhOrder.payMethod, 'pay_method', '')}
				</td>
				<td>
					${fns:getDictLabel(xhOrder.payStatus, 'pay_status', '')}
				</td>
				<td>
					${fns:getDictLabel(xhOrder.orderStatus, 'order_status', '')}
				</td>
				<td>
					${xhOrder.remarks}
				</td>
				<td>
					<shiro:hasPermission name="xhorder:xhOrder:view">
						<a href="#" onclick="openDialogView('查看订单详情', '${ctx}/orderdetail/orderDetail/list?oid=${xhOrder.id}','1000px', '600px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i>订单详情</a>
					</shiro:hasPermission>
					<%-- <shiro:hasPermission name="xhorder:xhOrder:edit">
    					<a href="#" onclick="openDialog('修改订单信息', '${ctx}/xhorder/xhOrder/form?id=${xhOrder.id}','1000px', '600px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="xhorder:xhOrder:del">
						<a href="${ctx}/xhorder/xhOrder/delete?id=${xhOrder.id}" onclick="return confirmx('确认要删除该订单信息吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission> --%>
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