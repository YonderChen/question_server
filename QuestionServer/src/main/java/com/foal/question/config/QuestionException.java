package com.foal.question.config;


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
	 * 返回报文异常码
	 */
	public static final int UnKnowError = -1;// 未知错误
	public static final int CommandNotFound = -2;// 没有找到命令
	public static final int SignError = -3;// 数字签名错误
	public static final int LoginInfoError = -4;// 登录信息异常
	public static final int NickNameError = -5;// 昵称不符合规范
	public static final int ContentIsEmpty = -6;// 内容为空
	public static final int ContentHasRiskWord = -7;// 内容含有敏感词汇
	public static final int AccountIsSilenced = -8;// 帐号被禁言
	public static final int AccountIsFreeze = -9;// 帐号被封
	public static final int UsernameError = -10;// 用户名不规范
	public static final int UserNotExist = -11;// 用户不存在
	public static final int PasswordError = -12;// 密码错误
	public static final int PasswordIsEmpty = -13;// 密码为空
	public static final int RecordNotExist = -14;// 记录不存在
	public static final int RecordIsNotYours = -15;// 记录不属于你
}
