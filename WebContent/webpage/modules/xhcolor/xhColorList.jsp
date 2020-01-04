<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>商品颜色管理</title>
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
		<h5>商品颜色列表 </h5>
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
	<form:form id="searchForm" modelAttribute="xhColor" action="${ctx}/xhcolor/xhColor/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>商品信息：</span>
				<form:select path="xhGoods"  class="form-control m-b">
					<form:option value="" label="">---请选择商品---</form:option>
					<c:forEach items="${xlist}" var="xlist">
							<form:option value="${xlist.id}" label="">${xlist.name}</form:option>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</c:forEach>
				</form:select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="xhcolor:xhColor:add">
				<table:addRow url="${ctx}/xhcolor/xhColor/form" title="商品颜色" width="500px" height="300px"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhcolor:xhColor:del">
				<table:delRow url="${ctx}/xhcolor/xhColor/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
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
				<th  >商品信息</th>
				<th  >鲜花颜色</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhColor">
			<tr>
				<td> <input type="checkbox" id="${xhColor.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看商品颜色', '${ctx}/xhcolor/xhColor/form?id=${xhColor.id}','500px', '300px')">
					${xhColor.xhGoods.name}
				</a></td>
				<td>
					${xhColor.color}
				</td>
				<td>
					<shiro:hasPermission name="xhcolor:xhColor:view">
						<a href="#" onclick="openDialogView('查看商品颜色', '${ctx}/xhcolor/xhColor/form?id=${xhColor.id}','500px', '300px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="xhcolor:xhColor:edit">
    					<a href="#" onclick="openDialog('修改商品颜色', '${ctx}/xhcolor/xhColor/form?id=${xhColor.id}','500px', '300px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="xhcolor:xhColor:del">
						<a href="${ctx}/xhcolor/xhColor/delete?id=${xhColor.id}" onclick="return confirmx('确认要删除该商品颜色吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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