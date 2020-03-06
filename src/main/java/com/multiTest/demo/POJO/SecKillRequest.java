package com.multiTest.demo.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecKillRequest {

    private Integer userId;

    private Integer productId;
}
