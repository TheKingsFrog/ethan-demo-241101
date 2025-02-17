package com.ethan.businesscore.designmodule.strategy.impl;

import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;
import com.ethan.businesscore.designmodule.strategy.strategyinterface.PayStrategy;

public class WeChatPayStrategyImpl implements PayStrategy {

    @Override
    public String payment(PayDTO payDTO) {
        // do WeChat pay ...
        return "WeChatPayServiceImpl payment" + payDTO.toString();
    }

}
