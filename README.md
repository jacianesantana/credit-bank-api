# Credit Bank - API
Instituição Bancária​

## Desafio para empresa Sicredi
Desenvolver uma aplicação que disponibiliza serviços bancários ao associado

## Regras de negócio
- Criar cadastro / conta
  - Somente maiores de idade
  - Não é permitido cadastrar associado com cpf já cadastrado
  - Associado tem que obrigatoriamente ter uma conta
- Assinar contrato  
- Realizar transações
  - Depósito, Saque e Transfrência
  - O saldo não pode ficar negativo
- Visualizar as opções de produtos disponíveis a depender do salário
  - Financiamento (Taxa: 7%; Salário mínimo: 5000; Parcelas: 180, 240, 360; Valores: mínimo 100k, máximo 500k)
  - Pessoal (Taxa: 4%; Salário mínimo: 1500; Parcelas: 6, 12, 18, 24; Valores: mínimo 1k, máximo 20k)
  - Consignado (Taxa: 2%; Salário mínimo: 3000; Parcelas: 6, 12, 18, 24, 36, 48; Valores: mínimo 1k, máximo 40k)
- Atualizar dados cadastrais do associado
  - Só é permitido atualizar contra-cheque (profissão e salário) a cada 3 meses 
  - Pode atualizar contato (telefone e email)
  - Pode atualizar endereço completo
- Buscar dados do Associado
  - Visualizar dados do associado com seus endereços, contas e contratos
  - Visualizar detalhes de um contrato
  - Visualizar saldo de uma conta
  - Visualizar extrato de uma conta
- Deletar cadastro
  - Não é permitido excluir se tiver contrato ativo
  - Excluir endereços, contas e contratos vinculados ao associado
  
## Pré-requisitos

- Java 11
- Spring 2.3.3.RELEASE
- Maven
- PostgreSQL 15
  
## Caminho do banco de dados
- DDL create database: /src/main/resources/db/1-script-create-database.sql
- DDL create tables: /src/main/resources/db/2-script-create-table.sql
