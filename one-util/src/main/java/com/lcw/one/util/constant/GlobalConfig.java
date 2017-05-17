package com.lcw.one.util.constant;

public class GlobalConfig {

	private static String adminPath;
	private static String restApiPath;

	public static String getAdminPath() {
		return adminPath;
	}

	public static void setAdminPath(String adminPath) {
		GlobalConfig.adminPath = adminPath;
	}

	public static String getRestApiPath() {
		return restApiPath;
	}

	public static void setRestApiPath(String restApiPath) {
		GlobalConfig.restApiPath = restApiPath;
	}

}
