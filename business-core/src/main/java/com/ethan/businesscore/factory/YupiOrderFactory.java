package com.ethan.businesscore.factory;

import com.ethan.businesscore.exception.OrderNotFoundException;
import com.ethan.businesscore.service.YupiOrderService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class YupiOrderFactory {

    private Map<String, YupiOrderService> yupiOrderMap;

    public YupiOrderService getOrder(String orderCode) {
        return Optional.of(yupiOrderMap.get(orderCode)).orElseThrow(OrderNotFoundException::new);
    }

}
