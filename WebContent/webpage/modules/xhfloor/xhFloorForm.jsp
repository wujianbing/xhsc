<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>楼层选择管理</title>
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
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="xhFloor" action="${ctx}/xhfloor/xhFloor/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">楼层名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">链接地址：</label></td>
					<td class="width-35">
						<form:input path="url" htmlEscape="false"   readonly="true"  class="form-control "/>
					</td>
					</tr>
					<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">楼层图片：</label></td>
					<td class="width-35" colspan="3">
						<span id="td3">
							<img id="oldImg" src="${xhFloor.uploadFile.relaPath}" width="150px" height="150px" />
							<input id="reInput" type="file" name="imgNews" />
						</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">楼层序号：</label></td>
					<td class="width-35">
						<form:select path="floorNum" class="form-control ">
							<form:options items="${fns:getDictList('floorNum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">楼层物语：</label></td>
					<td class="width-35">
						<form:input path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>