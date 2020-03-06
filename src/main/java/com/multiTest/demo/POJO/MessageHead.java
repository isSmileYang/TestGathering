package com.multiTest.demo.POJO;

import lombok.Data;

@Data
public class MessageHead {

    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 状态信息
     */
    private String statusMessage;

}
