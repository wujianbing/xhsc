<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>团购模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
					laydate({
			            elem: '#beginTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
					laydate({
			            elem: '#endTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="xhGroups" action="${ctx}/xhgroups/xhGroups/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">商品：</label></td>
					<td class="width-35">
						<form:select path="xhGoods" class="form-control ">
							<form:option value="" label="">---请选择开团商品---</form:option>
							<c:forEach items="${xlist}" var="xlist">
							<form:option value="${xlist.id}" label="">${xlist.name}</form:option>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</c:forEach>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">团号：</label></td>
					<td class="width-35">
						<form:input path="groupNo" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标题：</label></td>
					<td class="width-35">
						<form:input path="groupTitle" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">小标题：</label></td>
					<td class="width-35">
						<form:input path="lTitle" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">团购周期：</label></td>
					<td class="width-35">
						<form:input path="groupCycle" htmlEscape="false"    class="form-control " onkeyup="this.value=this.value.replace(/\D/g, '')"/>
					</td>
					<td class="width-15 active"><label class="pull-right">开团人数：</label></td>
					<td class="width-35">
						<form:input path="maxNum" htmlEscape="false"    class="form-control" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">开团时间：</label></td>
					<td class="width-35">
						<input id="beginTime" name="beginTime" type="text" maxlength="20" class="laydate-icon form-control layer-date "
							value="<fmt:formatDate value="${xhGroups.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
					<td class="width-15 active"><label class="pull-right">截止时间：</label></td>
					<td class="width-35">
						<input id="endTime" name="endTime" type="text" maxlength="20" class="laydate-icon form-control layer-date "
							value="<fmt:formatDate value="${xhGroups.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				</tr>
				
				
				<tr>
				<td class="width-15 active"><label class="pull-right">团购最低金额：</label></td>
					<td class="width-35">
						<form:input path="lPrice" htmlEscape="false"    class="form-control "/>
					</td>
				<td class="width-15 active"><label class="pull-right">团购最高金额：</label></td>
					<td class="width-35">
						<form:input path="maxPrice" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
					<tr>
					<td class="width-15 active"><label class="pull-right">团购状态：</label></td>
					<td class="width-35">
						<form:select path="groupStatus" class="form-control ">
							<form:option value="" label="">---请选择---</form:option>
							<form:options items="${fns:getDictList('groupStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="1"    class="form-control "/>
					<%-- </td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35">
						<form:input path="status" htmlEscape="false"    class="form-control "/>
					</td> --%>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>