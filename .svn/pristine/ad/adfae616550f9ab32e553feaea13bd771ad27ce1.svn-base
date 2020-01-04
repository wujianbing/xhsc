<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>收货地址管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>收货地址列表 </h5>
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
	
	<%-- <!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="recAddr" action="${ctx}/recaddress/recAddr/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
		 </div>	
	</form:form>
	<br/>
	</div>
	</div> --%>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>用户信息</th>
				<th>省</th>
				<th>市</th>
				<th>区</th>
				<th>详细地址</th>
				<th>收件人</th>
				<th>收件人电话</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recAddr">
			<tr>
				<td> <input type="checkbox" id="${recAddr.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看收货地址', '${ctx}/recaddress/recAddr/form?id=${recAddr.id}','800px', '500px')">
					${recAddr.xhUser.username}
				</a></td>
				<td>
					${recAddr.province}
				</td>
				<td>
					${recAddr.city}
				</td>
				<td>
					${recAddr.area}
				</td>
				<td>
					${recAddr.address}
				</td>
				<td>
					${recAddr.recUser}
				</td>
				<td>
					${recAddr.recPhone}
				</td>
				 <td>
					<shiro:hasPermission name="recaddress:recAddr:view">
						<a href="#" onclick="openDialogView('查看收货地址', '${ctx}/recaddress/recAddr/form?id=${recAddr.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
				<%--	<shiro:hasPermission name="recaddress:recAddr:edit">
    					<a href="#" onclick="openDialog('修改收货地址', '${ctx}/recaddress/recAddr/form?id=${recAddr.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="recaddress:recAddr:del">
						<a href="${ctx}/recaddress/recAddr/delete?id=${recAddr.id}" onclick="return confirmx('确认要删除该收货地址吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
				</td> --%>
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