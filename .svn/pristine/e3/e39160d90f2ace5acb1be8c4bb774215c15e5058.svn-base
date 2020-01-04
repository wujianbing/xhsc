<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>鲜花资讯管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctxStatic}/ueditor/ueditor.all.js"></script>
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
			var ue = UE.getEditor('xhUE');
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="xhNews" action="${ctx}/xhnews/xhNews/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">栏目：</label></td>
					<td class="width-35">
						<sys:gridselect url="${ctx}/xhnews/xhNews/selectxhColumn" id="xhColumn" name="xhColumn.id"  value="${xhNews.xhColumn.id}"  title="选择商品种类" labelName="xhColumn.columnName" 
						 labelValue="${xhNews.xhColumn.columnName}" cssClass="form-control required" fieldLabels="columnName" fieldKeys="columnName" searchLabel="columnName" searchKey="columnName" ></sys:gridselect>
					</td>
					</td>
					<td class="width-15 active"><label class="pull-right">标题：</label></td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label id="td4" class="pull-right">图片：</label></td>
					<td class="width-35" colspan="3">
						<span id="td3">
							<img id="oldImg" src="" width="150px" height="150px" />
							<input id="reInput" type="file" name="imgNews" />
						</span>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">内容：</label></td>
					<td colspan="3" class="width-35">
						<div>
							<form:textarea path="content"  id="xhUE" />
                        </div>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>