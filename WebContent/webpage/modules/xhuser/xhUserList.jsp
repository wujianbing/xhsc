<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
		<h5>用户信息列表 </h5>
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
	<form:form id="searchForm" modelAttribute="xhUser" action="${ctx}/xhuser/xhUser/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>用户姓名：</span>
				<form:input path="username" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/>
			<%-- <span>用户昵称：</span>
				<form:input path="nickname" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/>
			<span>用户等级：</span>
				<form:input path="level" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/> --%>
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
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>用户姓名</th>
				<th>用户性别</th>
				<th>用户头像</th>
				<th>会员积分</th>
				<th>用户电话</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhUser">
			<tr>
				<td> <input type="checkbox" id="${xhUser.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看用户信息', '${ctx}/xhuser/xhUser/form?id=${xhUser.id}','500px', '400px')">
					${xhUser.username}
				</a></td>
				<td>
					${fns:getDictLabel(xhUser.sex, 'sex', '')}
				</td>
				<td>
					${xhUser.pic}
				</td>
				<td>
					${xhUser.integral}
				</td>
				<td>
					${xhUser.phone}
				</td>
				<td>
					<shiro:hasPermission name="xhuser:xhUser:view">
						<a href="#" onclick="openDialogView('查看用户信息', '${ctx}/xhuser/xhUser/form?id=${xhUser.id}','500px', '400px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
    				<%-- <shiro:hasPermission name="xhuser:xhUser:del">
						<a href="${ctx}/xhuser/xhUser/delete?id=${xhUser.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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