package com.ethan.businesscore.designmodule.strategy.impl;

import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;
import com.ethan.businesscore.designmodule.strategy.strategyinterface.PayStrategy;

public class AliPayStrategyImpl implements PayStrategy {


    @Override
    public String payment(PayDTO payDTO) {

        // do AliPay ...
        return "AliPayServiceImpl payment" + payDTO.toString();

    }
}
