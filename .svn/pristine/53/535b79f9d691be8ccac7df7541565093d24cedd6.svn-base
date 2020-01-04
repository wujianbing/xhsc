<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>商品管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="xhGoods" action="${ctx}/xhgoods/xhGoods/list" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>商品名称：</span>
				<form:select path="id"  class="form-control m-b">
				<form:option value="" label="">---请选择商品---</form:option>
					<c:forEach items="${goods}" var="goods">
							<form:option value="${goods.id}" label="">${goods.name}</form:option>
							</c:forEach>
							</form:select>
			<span>英文缩写：</span>
				<form:input path="initial" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/>
			<span>商品上下架：</span>
				<form:radiobuttons class="i-checks" path="status" items="${fns:getDictList('xh_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="xhgoods:xhGoods:add">
				<table:addRow url="${ctx}/xhgoods/xhGoods/form" title="商品管理" width="1000px" height="600px"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhgoods:xhGoods:del">
				<table:delRow url="${ctx}/xhgoods/xhGoods/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="xhgoods:xhGoods:import">
				<table:importExcel url="${ctx}/xhgoods/xhGoods/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhgoods:xhGoods:export">
	       		<table:exportExcel url="${ctx}/xhgoods/xhGoods/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission> --%>
	       <!-- <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> -->
		
			</div>
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
				<th  >商品种类</th>
				<th  >商品名称</th>
				<th  >英文缩写</th>
				<th  >商品单位</th>
				<th  >商品最低价格</th>
				<th  >商品最高价格</th>
				<th  >商品积分</th>
				<th  class="sort-column status">商品上下架</th>
				<!-- <th  class="sort-column inventory">商品库存</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhGoods">
			<tr>
				<td> <input type="checkbox" id="${xhGoods.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看商品管理', '${ctx}/xhgoods/xhGoods/form?id=${xhGoods.id}','1000px', '600px')">
					${xhGoods.xhCategory.name}
				</a></td>
				<td>
					${xhGoods.name}
				</td>
				<td>
					${xhGoods.initial}
				</td>
				<td>
					${fns:getDictLabel(xhGoods.bigUnit, 'bigunit', '')}
				</td>
				<td>
					${xhGoods.price}
				</td>
				<td>
					${xhGoods.maxPrice}
				</td>
				
				<td>
					${xhGoods.integral}
				</td>
				<td>
					${fns:getDictLabel(xhGoods.status, 'xh_status', '')}
				</td>
				<%-- <td>
					${xhGoods.xhInventory}
				</td> --%>
				<td>
					<shiro:hasPermission name="xhgoods:xhGoods:view">
						<a href="#" onclick="openDialogView('查看商品管理', '${ctx}/xhgoods/xhGoods/form?id=${xhGoods.id}','1000px', '600px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="xhgoods:xhGoods:edit">
    					<a href="#" onclick="openDialog('修改商品管理', '${ctx}/xhgoods/xhGoods/form?id=${xhGoods.id}','1000px', '600px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="xhgoods:xhGoods:del">
						<a href="${ctx}/xhgoods/xhGoods/delete?id=${xhGoods.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
					<%-- <button id="special" onclick="special('${xhGoods.id}')" style="background-color: red;border-color:red;color: white; border-radius: 3px;border: 1px solid transparent;">${xhGoods.special eq '0'?"设为特卖":"取消特卖"}</button> --%>
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
	<!-- <script type="text/javascript">
		function special(id){		
			var special = document.getElementById("special");
			if(special.textContent== "设为特卖"){
			special.innerHTML="取消特卖";
			window.location.href='${ctx}/xhgoods/xhGoods/special?id='+id+'&special=1';
			alert('设置成功');
			}else{
			special.innerHTML="设为特卖";
			window.location.href='${ctx}/xhgoods/xhGoods/special?id='+id+'&special=0';
			alert('取消成功');
			} 
	}
	</script> -->
</body>
</html>