package com.ethan.ethandemo241101.service.impl;

import com.ethan.ethandemo241101.dao.entity.Order;
import com.ethan.ethandemo241101.dao.entity.dto.OrderStatusResDTO;
import com.ethan.ethandemo241101.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
public class IOrderServiceImpl implements IOrderService {

    @Override
    public String filterOrder(List<Order> orders, Predicate<Order> predicate) {
        return Arrays.toString(orders.stream().filter(predicate).toArray());
    }

    @Override
    public Double countOrder(List<Order> orders, Function<Order, Double> orderFunction) {

        if(CollectionUtils.isEmpty(orders)){
            return 0d;
        }
        // 123
        return orders.stream().mapToDouble(orderFunction::apply).sum();

    }

    @Override
    public List<OrderStatusResDTO> groupOrder(List<Order> orders, Function<Order, String> orderFunction) {
        return orders.stream()
                .collect(Collectors.groupingBy(orderFunction))
                .entrySet().stream()
                .map((a) -> new OrderStatusResDTO(a.getKey(), a.getValue().size()))
                .collect(Collectors.toList());
    }

}
