package com.ethan.businesscore.designmodule.strategy.entity;

import lombok.Data;

@Data
public class PayDTO {

    private String orderId;

    private String orderNo;

    private Integer amount;

    private String payType;

}
