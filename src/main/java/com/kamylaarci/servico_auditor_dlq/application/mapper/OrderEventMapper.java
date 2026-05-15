package com.kamylaarci.servico_auditor_dlq.application.mapper;

import com.kamylaarci.servico_auditor_dlq.application.dtos.request.OrderEventDTO;
import com.kamylaarci.servico_auditor_dlq.application.dtos.request.OrderItemDTO;
import com.kamylaarci.servico_auditor_dlq.domain.model.OrderEvent;
import com.kamylaarci.servico_auditor_dlq.domain.model.OrderItem;

public class OrderEventMapper {
    public static OrderEvent toDomain(OrderEventDTO dto) {
        return new OrderEvent(
                dto.getZipCode(),
                dto.getCustomerId(),
                dto.getOrderItems().stream()
                        .map(item -> new OrderItem(item.getSku(), item.getAmount()))
                        .toList(),
                dto.getOrigin(),
                dto.getOccurredAt()
        );
    }

    public static OrderEventDTO toDto(OrderEvent event) {
        return new OrderEventDTO(
                event.getZipCode(),
                event.getCustomerId(),
                event.getOrderItems().stream()
                        .map(item -> new OrderItemDTO(item.getSku(), item.getAmount()))
                        .toList(),
                event.getOrigin(),
                event.getOccurredAt()
        );
    }
}
