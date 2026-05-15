package com.kamylaarci.servico_auditor_dlq.domain.services;

import com.kamylaarci.servico_auditor_dlq.domain.model.OrderEvent;
import com.kamylaarci.servico_auditor_dlq.domain.model.OrderItem;
import com.kamylaarci.servico_auditor_dlq.domain.model.Severity;

public class SeverityService {

    public Severity calculate(OrderEvent event){
         double totalProductAmount;

        totalProductAmount = event.getOrderItems()
                .stream()
                .mapToInt(OrderItem::getAmount)
                .sum();

        if(totalProductAmount > 100){
            return Severity.HIGH;
        } else if (totalProductAmount >= 50) {
            return Severity.MEDIUM;
        } else {
            return Severity.LOW;
        }
    }
}
