package com.kamylaarci.servico_auditor_dlq.application.dtos.request;

public class OrderItemDTO {
    private Integer sku;
    private Integer amount;

    public OrderItemDTO(Integer sku, Integer amount) {
        this.sku = sku;
        this.amount = amount;
    }

    public OrderItemDTO() {

    }

    public Integer getSku() {
        return sku;
    }

    public Integer getAmount() {
        return amount;
    }
}
