package br.com.sicredi.bank.service.account;

import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.model.response.account.StatementAccountResponse;
import br.com.sicredi.bank.model.response.transaction.StatementTransactionResponse;
import br.com.sicredi.bank.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountStatementService {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    public ResponseEntity<StatementAccountResponse> statement(Long id) {
        var account = accountService.findById(id);
        var transactions = transactionService.findAllByAccountId(account);
        var sortedList = new ArrayList<StatementTransactionResponse>();

        if (!transactions.isEmpty()) {
            sortedList = (ArrayList<StatementTransactionResponse>) transactions.stream()
                    .map(transaction -> transactionMapper.transactionToStatementTransaction(account, transaction))
                    .sorted(Comparator.comparing(StatementTransactionResponse::getCreatedAt))
                    .collect(Collectors.toList());
        }


        var response = StatementAccountResponse.builder()
                .type(account.getType())
                .agency(account.getAgency())
                .number(account.getNumber())
                .balance(account.getBalance())
                .transactions(sortedList)
                .build();

        return ResponseEntity.ok(response);
    }

}
