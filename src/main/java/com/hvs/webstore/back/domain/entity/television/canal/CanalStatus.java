package com.hvs.webstore.back.domain.entity.television.canal;

public enum CanalStatus {

    ACTIVE("AT", "Active"),
    INACTIVE("IN", "Inactive"),
    DELETED("DE", "Deleted");

    private final String code;
    private final String desc;

    CanalStatus(String aCode, String aDesc) {
        this.code = aCode;
        this.desc = aDesc;
    }

    public static CanalStatus findByCode(String aCode) {
        if (aCode != null) {
            for (CanalStatus status : values()) {
                if (aCode.equals(status.getCode())) {
                    return status;
                }
            }
        }
        return null;
    }

    public static CanalStatus findByDesc(String aDesc) {
        if (aDesc != null) {
            for (CanalStatus status : values()) {
                if (aDesc.equals(status.getDesc())) {
                    return status;
                }
            }
        }
        return null;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
