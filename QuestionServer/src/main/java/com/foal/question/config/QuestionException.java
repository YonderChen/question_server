package com.foal.question.config;

import com.foal.question.jersey.resource.tools.APIConstants.RetCode;

public class QuestionException extends IllegalStateException {

	private static final long serialVersionUID = -6274605634030627362L;
	private int statusCode;
	private String statusMsg;

	public QuestionException(int statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;
		this.statusMsg = msg;
	}

	public QuestionException(String msg) {
		super(msg);
		this.statusMsg = msg;
	}

	public QuestionException(int statusCode) {
		this(statusCode, "");
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * @param statusMsg
	 *            the statusMsg to set
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	/**
	 * 返回报文的状态码常量 1000以上为固定意义的常量
	 */
	public static final int UnKnowError = RetCode.Faild;// 未知错误

}
