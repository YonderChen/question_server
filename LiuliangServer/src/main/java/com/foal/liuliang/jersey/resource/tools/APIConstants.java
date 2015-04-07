/**
 * 
 */
package com.foal.liuliang.jersey.resource.tools;

/**
 * 返回报文常用 的key值常量
 * @author jackyli515
 *
 */
public class APIConstants {
	public class DataKeys {
		public static final String Result = "ret";
		public static final String Message = "msg";
	}
    
	public class RetCode {
		public static final int Success = 0;
		public static final int Faild = -1;
	}
}
