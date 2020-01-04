<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入url --%>
<button id="btnExport" class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" title="数据读取"><i class="fa fa-file-excel-o"></i> 数据读取</button>
<script type="text/javascript">
$(document).ready(function() {

	$("#btnExport").click(function(){
		top.layer.confirm('确认要同步PDM数据到系统吗?', {icon: 3, title:'系统提示'}, function(index){
	    	window.location.href = "${url}";
		    top.layer.close(index);
		});
	});
});


</script>