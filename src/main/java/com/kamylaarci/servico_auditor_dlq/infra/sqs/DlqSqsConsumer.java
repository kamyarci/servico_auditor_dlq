package com.kamylaarci.servico_auditor_dlq.infra.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamylaarci.servico_auditor_dlq.application.dtos.request.OrderEventDTO;
import com.kamylaarci.servico_auditor_dlq.application.usecases.ProcessDlqMessageUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DlqSqsConsumer {
    private final ProcessDlqMessageUseCase processDlqMessageUseCase;
    private final ObjectMapper objectMapper;

    @Autowired
    public DlqSqsConsumer(ProcessDlqMessageUseCase processDlqMessageUseCase, ObjectMapper objectMapper) {
        this.processDlqMessageUseCase = processDlqMessageUseCase;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${queue.dlq}")
    public void listen(String rawPayload) {

        try {
            OrderEventDTO dto = objectMapper.readValue(rawPayload, OrderEventDTO.class);
            processDlqMessageUseCase.execute(dto, rawPayload);

            System.out.println("Mensagem consumida com sucesso.");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar mensagem da DLQ", e);
        }
    }
}
