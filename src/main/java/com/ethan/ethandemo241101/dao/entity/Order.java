package com.ethan.ethandemo241101.dao.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;
import java.util.function.Consumer;

@Data
public class Order {

    public static final String FIELD_TOTAL_AMOUNT = "totalAmount";

    private String orderId;
    private String userId;
    private double totalAmount;
    private String userCreditRating; // e.g., "优秀", "良好", "一般"
    private String status; // e.g., "已完成", "待支付", "已取消"

    // Getters, Setters, Constructors (省略)

}