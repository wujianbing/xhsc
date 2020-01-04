<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>包月信息管理</title>
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
		<h5>包月信息列表 </h5>
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
	<form:form id="searchForm" modelAttribute="xhMbuyer" action="${ctx}/xhmbuyer/xhMbuyer/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>开始时间：</span>
				<input id="createDate" name="createDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${xhMbuyer.createDate}" pattern="yyyy-MM-dd"/>"/> 
			 <span>结束时间：</span>
				<input id="updateDate" name="updateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${xhMbuyer.updateDate}" pattern="yyyy-MM-dd"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
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
				<th>包月用户</th>
				<th>包月商品</th>
				<th>包月订单</th>
				<th>包月周期</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>包月状态</th>
				<th>配送次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhMbuyer">
			<tr>
				<td> <input type="checkbox" id="${xhMbuyer.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看包月信息', '${ctx}/orderdetail/orderDetail/list?oid=${xhMbuyer.xhOrder.id}','800px', '500px')">
					
					${xhMbuyer.xhUser.username}
				</a></td>
				<td>
					${xhMbuyer.xhGoods.name}
				</td>
				<td>
					${xhMbuyer.xhOrder.orderNo}
				</td>
				<td>
					${xhMbuyer.mtime}
				</td>
				<td>
					<fmt:formatDate value="${xhMbuyer.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xhMbuyer.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(xhMbuyer.mstatus, 'm_status', '')}
				</td>
				<td>
					${xhMbuyer.times}
				</td>
				<td>
					<shiro:hasPermission name="xhmbuyer:xhMbuyer:view">
						<a href="#" onclick="openDialogView('查看包月信息', '${ctx}/orderdetail/orderDetail/list?oid=${xhMbuyer.xhOrder.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<%-- <shiro:hasPermission name="xhmbuyer:xhMbuyer:edit">
    					<a href="#" onclick="openDialog('修改包月信息', '${ctx}/xhmbuyer/xhMbuyer/form?id=${xhMbuyer.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission> --%>
    					<a href="${ctx}/xhmbuyer/xhMbuyer/changeTimes?id=${xhMbuyer.id}&times=${xhMbuyer.times}"  class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改次数</a>
    				<shiro:hasPermission name="xhmbuyer:xhMbuyer:del">
						<a href="${ctx}/xhmbuyer/xhMbuyer/delete?id=${xhMbuyer.id}" onclick="return confirmx('确认要删除该包月信息吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
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