# Serviço Auditor DLQ

Serviço independente responsável por consumir mensagens da Dead Letter Queue (DLQ) e persistir os detalhes em um banco de dados de auditoria, classificando a severidade de cada evento.

## Funcionalidades

- Escuta ativamente a fila T02N_KAMYLA_ARCI-DLQ.fifo
- Classifica a severidade do evento com base na quantidade total de produtos
- Persiste o registro de auditoria no PostgreSQL com status PENDING_ANALYSIS

## Regra de Severidade

- Quantidade total de produtos do evento for maior que 100: prioridade ALTA (HIGH)
- Quantidade total de produtos do evento for entre 50 e 100 (inclusive): prioridade MÉDIA (MEDIUM)
- Quantidade total de produtos do evento for menor que 50: prioridade BAIXA (LOW)

## Contrato do Registro no Banco

```json
{
  "errorId": "uuid-gerado-pelo-servico",
  "queueName": "T02N_KAMYLA_ARCI-DLQ.fifo",
  "payload": "{ ...conteúdo bruto da mensagem... }",
  "timestamp": "2026-05-15T16:18:11Z",
  "status": "PENDING_ANALYSIS",
  "severity": "LOW"
}
```

## Decisão Arquitetural: Clean Architecture

Escolhi a **Clean Architecture** por dois motivos principais.

O primeiro é familiaridade: já trabalho com essa arquitetura, o que reduz a curva de aprendizagem e diminui a chance de erros de implementação.

O segundo é adequação ao problema: esse serviço tem uma responsabilidade única e bem definida, que é consumir mensagens de uma fila e persistir no banco. A clean arch protege essa responsabilidade ao isolar as regras de negócio de qualquer detalhe externo, como a fila SQS ou o banco de dados.

### Como foi aplicada

O projeto está organizado em três camadas principais:

**`DOMAIN`**: núcleo da aplicação, sem nenhuma dependência de framework. Contém os modelos, o contrato de persistência e a regra de severidade. Essa camada não sabe que existe SQS, JPA ou Spring.

**`APPLICATION`**: orquestra o fluxo sem conhecer detalhes externos. O use case recebe o evento, chama o serviço de severidade, monta o registro de auditoria e delega a persistência para o repositório. Os DTOs e mappers ficam aqui, fazendo a ponte entre a entrada externa e o domínio.

**`INFRA`**: contém todos os detalhes externos que se adaptam ao domínio:
- `infra.sqs`: o consumer SQS que recebe a mensagem e aciona o use case
- `infra.persistence.postgresql`: a entidade JPA, o mapper e a implementação do repositório
- `infra.config`: configurações técnicas do Spring

### Sobre a captura do erro original

O AWS SQS não transmite o motivo da falha original junto com a mensagem redirecionada para a DLQ, ele apenas move a mensagem que não foi processada com sucesso. Por isso, esse serviço captura e persiste o payload bruto da mensagem para análise posterior pelos desenvolvedores, que poderão investigar o erro original nos logs do serviço de origem. O registro fica com status PENDING_ANALYSIS justamente para sinalizar que a mensagem aguarda essa investigação manual.

### Por que essa estrutura faz sentido para esse serviço

**Desacoplamento real:** se amanhã precisar trocar o PostgreSQL por outro banco, basta criar uma nova implementação da interface IAuditRepository. O domínio e o use case não precisam ser tocados. O mesmo vale para a fila: trocar SQS por RabbitMQ significa reescrever apenas o consumer em infra.sqs.

**Proteção da regra de negócio:** a classificação de severidade vive em domain.services, isolada de qualquer framework. Ela não depende do SQS para existir, nem do banco para funcionar. Isso garante que a regra nunca seja "contaminada" por detalhes de infraestrutura.

**Inversão de dependência:** o use case depende da interface IAuditRepository definida no domínio, não da implementação JPA. Isso significa que o núcleo da aplicação dita o contrato, e a infraestrutura se adapta a ele, e não o contrário.

A dependência flui sempre na direção certa: infra -> application -> domain. O domínio não conhece nada externo.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.5
- Spring Cloud AWS SQS 3.4
- Spring Data JPA
- PostgreSQL
- Jackson

## Variáveis de Ambiente

| Variável            | Descrição                  |
|---------------------|----------------------------|
| `AWS_ACCESS_KEY_ID` | Chave de acesso AWS        |
| `AWS_SECRET_ACCESS_KEY` | Chave secreta AWS      |
| `AWS_DEFAULT_REGION` | Região AWS               |
| `DB_URL`            | URL do banco PostgreSQL    |
| `DB_USERNAME`       | Usuário do banco           |
| `DB_PASSWORD`       | Senha do banco             |
