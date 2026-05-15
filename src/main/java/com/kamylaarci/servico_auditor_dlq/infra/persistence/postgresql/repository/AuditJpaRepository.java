package com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.repository;

import com.kamylaarci.servico_auditor_dlq.infra.persistence.postgresql.entity.AuditRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditJpaRepository extends JpaRepository<AuditRecordEntity, UUID> {
}
