package com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.entity;

import com.kamylaarci.servico_auditor_dlq.domain.model.Severity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_records")
public class AuditRecordEntity {

    @Id
    private UUID errorId;
    private String queueName;
    @Column(columnDefinition = "TEXT")
    private String payload;
    private LocalDateTime timestamp;
    private String status;
    @Enumerated(EnumType.STRING)
    private Severity severity;

    public AuditRecordEntity(
            UUID errorId,
            String queueName,
            String payload,
            LocalDateTime timestamp,
            String status,
            Severity severity
    ) {
        this.errorId = errorId;
        this.queueName = queueName;
        this.payload = payload;
        this.timestamp = timestamp;
        this.status = status;
        this.severity = severity;
    }

    public AuditRecordEntity() {

    }

    public UUID getErrorId() {
        return errorId;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getPayload() {
        return payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }
}
