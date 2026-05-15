package com.kamylaarci.servico_auditor_dlq.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEvent {
    private String zipCode;
    private Integer customerId;
    private List<OrderItem> orderItems;
    private String origin;
    private LocalDateTime occurredAt;

    public OrderEvent(String zipCode,
                      Integer customerId,
                      List<OrderItem> orderItems,
                      String origin,
                      LocalDateTime occurredAt
    ) {
        this.zipCode = zipCode;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.origin = origin;
        this.occurredAt = occurredAt;
    }

    public OrderEvent() {

    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getOrigin() {
        return origin;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
