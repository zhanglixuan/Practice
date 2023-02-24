package com.example.exception;

/**
 * @author 张丽璇
 * @date 2023/2/23
 */
public class BusinessException extends RuntimeException{
	private Integer code;

	public BusinessException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
