package com.ethan.ethandemo241101.dao.entity.dto;

import lombok.Data;

@Data
public class OrderStatusResDTO {

    private String status;

    private Long count;

    public OrderStatusResDTO(String status, long count) {
        this.status = status;
        this.count = count;
    }
}
