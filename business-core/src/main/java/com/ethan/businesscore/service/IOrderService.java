package com.ethan.businesscore.service;

import com.ethan.businesscore.dao.entity.Order;
import com.ethan.businesscore.dao.entity.dto.OrderStatusResDTO;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IOrderService {

    /**
     * 过滤总金额大于d的订单
     * @param d
     * @return
     */
    default Predicate<Order> orderTotalAmountPredicate(Double d) {
        return (i) -> ObjectUtils.isNotEmpty(i.getTotalAmount()) && i.getTotalAmount() > d;
    }

    /**
     * 过滤用户信用评级为rating的订单
     * @param rating
     * @return
     */
    default Predicate<Order> orderUserCreditRatingPredicate(String rating) {
        return (i) -> rating.equals(i.getUserCreditRating());
    }

    /**
     * 过滤订单
     * @param orders
     * @return
     */
    String filterOrder(List<Order> orders, Predicate<Order> predicate);

    /**
     * 统计订单数字字段
     * @param orders
     * @param orderFunction
     * @return
     */
    Double countOrder(List<Order> orders, Function<Order, Double> orderFunction);

    List<OrderStatusResDTO> groupOrder(List<Order> orders, Function<Order, String> orderFunction);

}
