package com.multiTest.demo.POJO.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaskOrder{
    /**
     * id
     */
    private Integer id;
    /**
     * 用户
     */
    private Purchaser user;
    /**
     * 产品
     */
    private Masks masks;
    /**
     * 1秒杀成功,0秒杀失败,-1重复秒杀,-2系统异常
     */
    private String state;
    /**
     * 状态的明文标识
     */
    private String stateInfo;
    /**
     * 创建时间
     */
    private Date createTime;
}
