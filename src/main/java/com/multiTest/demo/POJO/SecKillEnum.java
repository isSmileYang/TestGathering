package com.multiTest.demo.POJO;

public enum SecKillEnum {
    /**
     * 服务级错误
     */
    SUCCESS(SecKillState.SUCCESS,"秒杀成功"),
    LOW_STOCKS(SecKillState.FAIL, "库存不足"),
    FAIL(SecKillState.FAIL, "秒杀失败"),
    REPEAT(SecKillState.REPEAT, "重复秒杀"),
    SYSTEM_EXCEPTION(SecKillState.SYSTEM_EXCEPTION, "系统错误"),
    ;

    private String code;

    private String message;

    SecKillEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}