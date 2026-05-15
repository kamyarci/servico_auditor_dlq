package com.kamylaarci.servico_auditor_dlq.domain.model;

public class OrderItem {
    private Integer sku;
    private Integer amount;

    public OrderItem(Integer sku, Integer amount) {
        this.sku = sku;
        this.amount = amount;
    }

    public OrderItem() {

    }

    public Integer getSku() {
        return sku;
    }

    public Integer getAmount() {
        return amount;
    }
}
