package com.kamylaarci.servico_auditor_dlq.domain.contracts;

import com.kamylaarci.servico_auditor_dlq.domain.model.AuditRecord;

public interface IAuditRepository {
    void save(AuditRecord record);
}
