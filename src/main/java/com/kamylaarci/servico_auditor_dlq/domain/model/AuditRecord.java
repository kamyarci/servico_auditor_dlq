package com.kamylaarci.servico_auditor_dlq.domain.model;

import java.time.LocalDateTime;

public class AuditRecord {
    private String errorId;
    private String queueName;
    private String payload;
    private LocalDateTime timestamp;
    private String status;
    private Severity severity;

    public AuditRecord(String errorId,
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

    public AuditRecord(){

    }

    public String getErrorId() {
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
