package com.example.exception;

/**
 * @author 张丽璇
 * @date 2023/3/7
 */
public class AuthenticationException extends RuntimeException{
	private Integer code;

	public AuthenticationException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public AuthenticationException(String message, Throwable cause, Integer code) {
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
