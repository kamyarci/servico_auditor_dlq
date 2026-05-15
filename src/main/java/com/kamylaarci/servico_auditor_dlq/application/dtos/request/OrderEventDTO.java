package com.kamylaarci.servico_auditor_dlq.application.dtos.request;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEventDTO {

    private String zipCode;
    private Integer customerId;
    private List<OrderItemDTO> orderItems;
    private String origin;
    private String occurredAt;

    public OrderEventDTO(String zipCode,
                         Integer customerId,
                         List<OrderItemDTO> orderItems,
                         String origin,
                         String occurredAt
    ) {
        this.zipCode = zipCode;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.origin = origin;
        this.occurredAt = occurredAt;
    }

    public OrderEventDTO() {

    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOccurredAt() {
        return occurredAt;
    }
}
