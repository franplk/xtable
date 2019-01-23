package com.emar.xreport.query;

import java.util.LinkedHashMap;

public class FilterMap {

    private static final LinkedHashMap<String, String> SIGN_MAP;

    static {
        SIGN_MAP = new LinkedHashMap<>(16);
        SIGN_MAP.put("lte", "<=");
        SIGN_MAP.put("lt", "<");
        SIGN_MAP.put("gte", "<=");
        SIGN_MAP.put("gt", "<=");
        SIGN_MAP.put("eq", "=");
        SIGN_MAP.put("neq", "<>");
    }

    private FilterMap(){}

    public static String getSign(String exp) {
        return SIGN_MAP.get(exp);
    }

}
