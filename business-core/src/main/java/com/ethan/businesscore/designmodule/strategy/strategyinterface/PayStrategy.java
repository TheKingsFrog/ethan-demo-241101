package com.ethan.businesscore.designmodule.strategy.strategyinterface;

import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;

public interface PayStrategy {

    /**
     * 支付接口
     */
    public String payment(PayDTO payDTO);

}
