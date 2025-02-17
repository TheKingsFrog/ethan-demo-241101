package com.ethan.businesscore.designmodule.strategy.impl;

import com.ethan.businesscore.designmodule.strategy.context.PayContext;
import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;
import com.ethan.businesscore.designmodule.strategy.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Override
    public String pay(PayDTO payDTO) {

        PayContext payContext = new PayContext(payDTO);
        return payContext.execute();

    }

}
