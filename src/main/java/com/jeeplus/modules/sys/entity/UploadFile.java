package com.jeeplus.modules.sys.entity;

import com.jeeplus.common.persistence.DataEntity;

//Office文件上传实体类
public class UploadFile extends DataEntity<UploadFile>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name; //文件名称
	private String relaPath; //文件相对路径
	private String absoPath; //文件绝对路径
	private String swfRelaPath; //swf文件相对路径
	private String swfAbsoPath; //swf文件绝对路劲
	private String uploadDate; //上传人
	private String uploader; //上传时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelaPath() {
		return relaPath;
	}
	public void setRelaPath(String relaPath) {
		this.relaPath = relaPath;
	}
	public String getAbsoPath() {
		return absoPath;
	}
	public void setAbsoPath(String absoPath) {
		this.absoPath = absoPath;
	}
	public String getSwfRelaPath() {
		return swfRelaPath;
	}
	public void setSwfRelaPath(String swfRelaPath) {
		this.swfRelaPath = swfRelaPath;
	}
	public String getSwfAbsoPath() {
		return swfAbsoPath;
	}
	public void setSwfAbsoPath(String swfAbsoPath) {
		this.swfAbsoPath = swfAbsoPath;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
}
