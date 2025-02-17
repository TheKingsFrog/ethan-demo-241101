package com.ethan.businesscore.designmodule.factory.impl;

import com.ethan.businesscore.designmodule.factory.PayFactory;
import com.ethan.businesscore.designmodule.strategy.impl.AliPayStrategyImpl;
import com.ethan.businesscore.designmodule.strategy.impl.WeChatPayStrategyImpl;
import com.ethan.businesscore.designmodule.strategy.strategyinterface.PayStrategy;

import java.util.HashMap;
import java.util.Map;

public class PayFactoryImpl implements PayFactory {

    private static final Map<String, PayStrategy> payStrategyMap = new HashMap<>();

    static {
        payStrategyMap.put("ALIPAY", new AliPayStrategyImpl());
        payStrategyMap.put("WECHATPAY", new WeChatPayStrategyImpl());
    }

    @Override
    public PayStrategy getPayment(String payType) {
        return payStrategyMap.get(payType);
    }
}
