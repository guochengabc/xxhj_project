package com.kongtiaoapp.xxhj.net;

@SuppressWarnings("serial")
public class CPNException extends Exception {

	private String code;

	public CPNException(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
