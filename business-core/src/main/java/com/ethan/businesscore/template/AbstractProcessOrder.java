package com.ethan.businesscore.template;

import com.ethan.businesscore.service.YupiOrderService;
import com.ethan.businesscore.service.dto.YupiOrder;

public abstract class AbstractProcessOrder implements YupiOrderService {

    @Override
    public void process(YupiOrder yupiOrder) {
        // 基础处理
        basicValid();
        // 基础查询
        basicQuery();
        // 处理
        doProcess(yupiOrder);
    }

    public abstract void doProcess(YupiOrder yupiOrder);

    private void basicQuery() {
        System.out.println("基础查询...");
    }

    private void basicValid() {
        System.out.println("基础校验...");
    }

}
