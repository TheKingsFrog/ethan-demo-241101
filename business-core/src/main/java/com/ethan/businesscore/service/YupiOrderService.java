package com.ethan.businesscore.service;

import com.ethan.businesscore.service.dto.YupiOrder;

public interface YupiOrderService {

    /**
     * 单据处理
     * @param yupiOrder
     */
    void process(YupiOrder yupiOrder);

}
