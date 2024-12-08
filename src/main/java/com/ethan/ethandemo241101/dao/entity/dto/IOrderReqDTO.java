package com.ethan.ethandemo241101.dao.entity.dto;

import com.ethan.ethandemo241101.dao.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class IOrderReqDTO {

    private List<Order> orders;

    private String str;

}
