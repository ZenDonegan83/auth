package com.inkd.auth.constants;

import org.apache.commons.lang3.StringUtils;

public class AppsConstants {

    public enum ResponseStatus {
        SUCCESS, FAILED;

        public static ResponseStatus resolveStatus(String statusStr) {
            ResponseStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ResponseStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum YesNo {
        Y("Yes"),
        N("No");

        private String strVal;

        YesNo(String strVal) {
            this.strVal = strVal;
        }

        public static YesNo resolver(String val) {
            YesNo matchingVal = null;
            if (val != null) {
                matchingVal = YesNo.valueOf(val);
            }
            return matchingVal;
        }

        public String getStrVal() {
            return strVal;
        }
    }

    public enum Role {
        USER,
        MANAGER,
        ADMIN
    }
}
