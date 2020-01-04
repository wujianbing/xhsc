<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="表单名称"%>
<%@ attribute name="auto" type="java.lang.Boolean" required="false" description="是否允许自动上传"%>
<%@ attribute name="readonly" type="java.lang.String" required="false" description="是否只读"%>
<%@ attribute name="size" type="java.lang.Integer" required="false" description="允许的文件数量"%>
<%@ attribute name="singleSize" type="java.lang.Integer" required="false" description="允许单文件大小"%>
<%@ attribute name="allowImage" type="java.lang.Boolean" required="false" description="允许图片文件"%>
<%@ attribute name="allowDoc" type="java.lang.Boolean" required="false" description="允许Office和其他文档文件"%>
<%@ attribute name="allowAll" type="java.lang.Boolean" required="false" description="允许所有文件"%>
<!-- 
<script type="text/javascript" src="static/jquery/jquery-1.9.1.js"></script>
 -->
<script type="text/javascript">include('webuploader_lib','${ctxStatic}/webuploader-0.1.5/',['webuploader.css','webuploader.js']);</script>
<div id="${id }" class="wu-example">
    <div class="btns">
        <div id="${id }picker" class="btn btn-success">选择文件</div>
        <c:if test="${empty auto?true:!auto}">
        <button id="${id }UploadBtn" class="btn btn-primary">开始上传</button>
        </c:if>
    </div>
    <div id="${id }FileList" class="uploader-list"></div>
</div>

<c:set var="inputName" value="${not empty name?name:'file'}"></c:set>
<c:set var="server" value="${pageContext.request.contextPath}/file"></c:set>
<script type="text/javascript">
	var processing${id}=false;
    var uploader${id} = WebUploader.create({
        swf: '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
        auto:${empty auto?false:auto},
        server: '${not empty url?url:server}/upload',
        pick: '#${id }picker',
        fileNumLimit:'${empty size?999:size}',
        fileSingleSizeLimit:'${empty singleSize?10240000:singleSize}',
        resize: false,//<c:choose><c:when test="${empty allowImage?false:allowImage}">
        accept:{
			title: '图片',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        }//</c:when><c:when test="${empty allowDoc?false:allowDoc}">
        accept:{
			title: '文档',
            extensions: 'doc,docx,xls,xlsx,ppt,pptx,txt,pdf',
            mimeTypes: 'application/msexcel,application/msword,application/vnd.ms-powerpoint,application/pdf,text/plain'
        }//</c:when></c:choose>
    }).on('fileQueued', function (file) {
        $("#${id }FileList").append('<div id="${id}' + file.id + '" class="item">' +
                '<span class="info">' + file.name + '</span>' +
                //'<span class="state"><span >等待上传...</span></span>' +
                '<input type="text" id="${id}'+file.id+'Id" name="${inputName}" >'+
                '<button onclick="${id}deleteFile(\''+file.id+'\')" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 移除</button>'+
                '</div>');
    }).on('uploadProgress', function (file, percentage) {
        var $li = $('#${id}' + file.id), $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('span.state').html('<span class="orange">'+file.name+'</span>');

        $percent.css('width', percentage * 100 + '%');
    }).on( 'uploadSuccess', function( file,data ) {
        $( '#${id}'+file.id ).find('span.info').html('<span class="green">'+file.name+'</span>');
        $( '#${id}'+file.id +'Id').val(data.id);
    }).on( 'uploadError', function( file ) {
        $( '#${id}'+file.id ).find('span.info').html('<span class="red">'+file.name+'</span>');
    }).on( 'uploadComplete', function( file ) {
        $( '#${id}'+file.id ).find('.progress').fadeOut();
    });

    $("#${id }UploadBtn").click(function(e){
        uploader${id}.upload();
    });
    function ${id}deleteFile(id){
    	var fileId=$("#${id}"+id+"Id").val();
    	if(!processing${id}){
		    	processing${id}=true;
	    	if(fileId){
		    	$.ajax({
		    		url:'${not empty url?url:server}/delete?id='+fileId,
		    		method:'post',
		    		dataType:'json',
		    		success:function(data){
		    			if(data){
		    				$('#${id}'+id ).remove();
							uploader${id}.removeFile(id);
		    			}
		    			processing${id}=false;
		    		},
		    		error:function(data){
		    			processing${id}=false;
		    		}
		    	});
	    	}else{
	    		$('#${id}'+id ).remove();
				uploader${id}.removeFile(id);
    			processing${id}=false;
	    	}
    	}
		
    }
</script>

