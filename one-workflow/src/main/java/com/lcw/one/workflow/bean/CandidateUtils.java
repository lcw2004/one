package com.lcw.one.workflow.bean;

public class CandidateUtils {

    public static String user(String userId) {
        return "U_" + userId;
    }

    public static String office(String officeId) {
        return "O_" + officeId;
    }

    public static String role(String roleId) {
        return "R_" + roleId;
    }

    public static String officeAndRole(String officeId, String roleId) {
        return "O_" + officeId + ":" + "R_" + roleId;
    }
}
