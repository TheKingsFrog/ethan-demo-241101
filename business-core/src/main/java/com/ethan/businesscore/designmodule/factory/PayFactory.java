package com.ethan.businesscore.designmodule.factory;

import com.ethan.businesscore.designmodule.strategy.strategyinterface.PayStrategy;

public interface PayFactory {

    PayStrategy getPayment(String payType);

}
