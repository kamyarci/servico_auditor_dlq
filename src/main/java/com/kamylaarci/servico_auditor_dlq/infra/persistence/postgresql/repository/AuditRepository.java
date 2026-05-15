package com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.repository;

import com.kamylaarci.servico_auditor_dlq.domain.contracts.IAuditRepository;
import com.kamylaarci.servico_auditor_dlq.domain.model.AuditRecord;
import com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.entity.AuditRecordEntity;
import com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.mapper.AuditRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditRepository implements IAuditRepository {

    private final AuditJpaRepository auditJpaRepository;

    @Autowired
    public AuditRepository(AuditJpaRepository auditJpaRepository) {
        this.auditJpaRepository = auditJpaRepository;
    }

    @Override
    public void save(AuditRecord record) {
        AuditRecordEntity entity = AuditRecordMapper.toEntity(record);
        auditJpaRepository.save(entity);
    }
}
