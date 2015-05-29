/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foal.question.config;



/**
 * 版本号
 * @author jackyli515
 */
public class Version {
	
	/**
	 * 版本1.0
	 */
	public static final int V_1_0 = 100;
	
	/**
	 * 当前版本
	 */
	public static int CurrentVersion = V_1_0;

	public static class DeviceFlag {
		public static final int IOS = 1;
		public static final int Andriod = 2;
	}
	
	public enum ClientVersion {
		//IOS
		IOS_1_0_1("1.01", DeviceFlag.IOS, V_1_0),
		IOS_1_0_2("1.02", DeviceFlag.IOS, V_1_0),
		IOS_1_0_3("1.03", DeviceFlag.IOS, V_1_0),
		IOS_1_0_4("1.04", DeviceFlag.IOS, V_1_0),
		IOS_1_0_5("1.05", DeviceFlag.IOS, V_1_0),
		
		//Android
		Andriod_1_0_1("1.01", DeviceFlag.Andriod, V_1_0),
		Andriod_1_0_2("1.02", DeviceFlag.Andriod, V_1_0),
		Andriod_1_0_3("1.03", DeviceFlag.Andriod, V_1_0),
		Andriod_1_0_4("1.04", DeviceFlag.Andriod, V_1_0),
		Andriod_1_0_5("1.05", DeviceFlag.Andriod, V_1_0);
		
		private String version;
		private int deviceFlag;
		private int serverVersion;
		
		private ClientVersion(String version, int deviceFlag, int serverVersion) {
			this.version = version;
			this.deviceFlag = deviceFlag;
			this.serverVersion = serverVersion;
		}
		
		public String getVersion() {
			return version;
		}

		public int getDeviceFlag() {
			return deviceFlag;
		}

		public int getServerVersion() {
			return serverVersion;
		}
	}

	public static int getServerVersion(int deviceFlag, String version) {
		for (ClientVersion cv : ClientVersion.values()) {
			if (cv.getDeviceFlag() == deviceFlag && cv.getVersion().equals(version)) {
				return cv.getServerVersion();
			}
		}
		return CurrentVersion;
	}
}