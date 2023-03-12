package com.example.result;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
public class Result {
	private Integer code;
	private Object data;
	private String msg;

	public Result() {
	}

	public Result(Integer code, String msg) {
		this(code, msg,null);
	}


	public Result(Integer code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
