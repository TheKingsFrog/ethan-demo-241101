package com.ethan.businesscore.dao.entity.dto;

import com.ethan.businesscore.dao.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class IOrderReqDTO {

    private List<Order> orders;

    private String str;

}
