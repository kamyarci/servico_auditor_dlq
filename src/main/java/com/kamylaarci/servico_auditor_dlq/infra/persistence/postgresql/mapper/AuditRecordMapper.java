package com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.mapper;

import com.kamylaarci.servico_auditor_dlq.domain.model.AuditRecord;
import com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.entity.AuditRecordEntity;

public class AuditRecordMapper {

    public static AuditRecordEntity toEntity(AuditRecord record) {
        return new AuditRecordEntity(
                record.getErrorId(),
                record.getQueueName(),
                record.getPayload(),
                record.getTimestamp(),
                record.getStatus(),
                record.getSeverity()
        );
    }

    public static AuditRecord toDomain(AuditRecordEntity entity) {
        return new AuditRecord(
                entity.getErrorId(),
                entity.getQueueName(),
                entity.getPayload(),
                entity.getTimestamp(),
                entity.getStatus(),
                entity.getSeverity()
        );
    }
}
