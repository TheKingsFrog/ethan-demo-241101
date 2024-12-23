package com.ethan.businesscore.web;


import com.ethan.businesscore.dao.entity.Order;
import com.ethan.businesscore.dao.entity.dto.IOrderReqDTO;
import com.ethan.businesscore.dao.entity.dto.OrderStatusResDTO;
import com.ethan.businesscore.service.IOrderService;
import com.ethan.businesscore.utils.annotates.ValidateParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

import static com.ethan.businesscore.dao.constants.CommonConstant.DOUBLE_500;
import static com.ethan.businesscore.dao.constants.CommonConstant.UserCreditRating.RATING_HIGH;


@RestController
@RequestMapping("/order")
public class IOrderController {

    @Autowired
    private IOrderService iOrderService;

    /**
     * 过滤出总金额大于500的订单
     * @param orders
     * @return
     */
    @PostMapping("/filter")
    @ValidateParameters
    public String filterOrder(@RequestBody List<Order> orders) {
        return iOrderService.filterOrder(orders, iOrderService.orderTotalAmountPredicate(DOUBLE_500));
    }

    /**
     * 过滤出用户信用评级为“优秀”的订单
     * @param orders
     * @return
     */
    @PostMapping("/user-rating")
    @ValidateParameters
    public String userRating(@RequestBody List<Order> orders) {
        return iOrderService.filterOrder(orders, iOrderService.orderUserCreditRatingPredicate(RATING_HIGH));
    }

    /**
     * 计算所有订单的总金额
     * @param orders
     * @return
     */
    @PostMapping("/count-total-amount")
    @ValidateParameters
    public Double countTotalAmount(@RequestBody List<Order> orders) {
        System.out.println(RequestContextHolder.getRequestAttributes());
        return iOrderService.countOrder(orders, Order::getTotalAmount);
    }

    /**
     * 统计每种订单状态（如“已完成”、“待支付”）的订单数量
     * @param orders
     * @return
     */
    @PostMapping("/group-order-status")
    @ValidateParameters
    public List<OrderStatusResDTO> groupOrderStatus(@RequestBody List<Order> orders, String account) {
        return iOrderService.groupOrder(orders, Order::getStatus);
    }

    /**
     * 测试全局捕获异常
     * @param iOrderReqDTO
     * @return
     */
    @PostMapping("/test-1")
    @ValidateParameters
    public String test1(@RequestBody IOrderReqDTO iOrderReqDTO) {
        return iOrderReqDTO.toString();
    }

}
