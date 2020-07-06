package com.qifenqian.bms.common.bean;

import java.io.Serializable;

public class FileInfo implements Serializable {

	private static final long serialVersionUID = 2380376860352474451L;

	private String path;

	public FileInfo() {

	}

	public FileInfo(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
