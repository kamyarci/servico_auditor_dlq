package com.kamylaarci.servico_auditor_dlq.application.usecases;

import com.kamylaarci.servico_auditor_dlq.application.dtos.request.OrderEventDTO;
import com.kamylaarci.servico_auditor_dlq.application.mapper.OrderEventMapper;
import com.kamylaarci.servico_auditor_dlq.domain.contracts.IAuditRepository;
import com.kamylaarci.servico_auditor_dlq.domain.exceptions.DomainException;
import com.kamylaarci.servico_auditor_dlq.domain.model.AuditRecord;
import com.kamylaarci.servico_auditor_dlq.domain.model.OrderEvent;
import com.kamylaarci.servico_auditor_dlq.domain.model.Severity;
import com.kamylaarci.servico_auditor_dlq.domain.services.SeverityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProcessDlqMessageUseCase {

    private final SeverityService severityService;
    private final IAuditRepository auditRepository;

    @Autowired
    public ProcessDlqMessageUseCase(SeverityService severityService,
                                    IAuditRepository auditRepository) {
        this.severityService = severityService;
        this.auditRepository = auditRepository;
    }

    public void execute(OrderEventDTO orderEventDTO, String rawPayload) {
        OrderEvent event = OrderEventMapper.toDomain(orderEventDTO);
        Severity severity = severityService.calculate(event);

        AuditRecord record = new AuditRecord(UUID.randomUUID(),
                "T02N_KAMYLA_ARCI-DLQ.fifo",
                rawPayload, LocalDateTime.now(),
                "PENDING_ANALYSIS", severity);

        auditRepository.save(record);

    }
}
