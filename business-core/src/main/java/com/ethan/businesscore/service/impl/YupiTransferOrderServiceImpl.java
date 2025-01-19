package com.ethan.businesscore.service.impl;

import com.ethan.businesscore.service.dto.YupiOrder;
import com.ethan.businesscore.template.AbstractProcessOrder;

public class YupiTransferOrderServiceImpl extends AbstractProcessOrder {
    @Override
    public void doProcess(YupiOrder yupiOrder) {
        System.out.println("处理调拨操作...");
    }
}


