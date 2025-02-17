package com.ethan.businesscore.designmodule.strategy;

import com.ethan.businesscore.designmodule.strategy.entity.PayDTO;
import com.ethan.businesscore.designmodule.strategy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payment")
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/pay")
    public String pay(@RequestBody PayDTO payDTO) {
        return payService.pay(payDTO);
    }

}
