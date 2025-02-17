package com.ethan.businesscore.designmodule.strategy.context;

import com.ethan.businesscore.designmodule.factory.PayFactory;
import com.ethan.businesscore.designmodule.factory.impl.PayFactoryImpl;
import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;
import com.ethan.businesscore.designmodule.strategy.strategyinterface.PayStrategy;

public class PayContext {

    private PayStrategy payStrategy;

    private PayDTO payDTO;

    public PayContext(PayDTO payDTO) {

        this.payDTO = payDTO;

        PayFactory payFactory = new PayFactoryImpl();

        this.payStrategy = payFactory.getPayment(payDTO.getPayType());

    }

    public String execute() {
        return payStrategy.payment(payDTO);
    }

}
