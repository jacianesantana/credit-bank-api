package br.com.sicredi.bank.service.transaction;

import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.TransactionEntity;
import br.com.sicredi.bank.model.request.transaction.DepositTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.TransferTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.WithdrawTransactionRequest;
import br.com.sicredi.bank.model.response.transaction.TransactionResponse;
import br.com.sicredi.bank.repository.TransactionRepository;
import br.com.sicredi.bank.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.sicredi.bank.model.enums.TransactionType.*;
import static br.com.sicredi.bank.utils.Message.TRANSACTION_BUSINESS_BALANCE_ERROR;
import static br.com.sicredi.bank.utils.Message.TRANSACTION_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public ResponseEntity<TransactionResponse> deposit(DepositTransactionRequest request) {
        var account = accountService.findByAgencyAndNumber(request.getCreditAccount().getAgency(),
                request.getCreditAccount().getNumber());
        var newBalance = account.getBalance().add(request.getValue());
        account.setBalance(newBalance);

        try {
            accountService.save(account);

            var transaction = transactionMapper.toTransaction(null, account,
                    DEPOSIT, request.getValue());

            transactionRepository.save(transaction);

            var response = TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(account))
                    .newBalance(account.getBalance())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new SaveEntityException(TRANSACTION_SAVE_ERROR);
        }
    }

    public ResponseEntity<TransactionResponse> withdraw(WithdrawTransactionRequest request) {
        var account = accountService.findByAgencyAndNumber(request.getDebitAccount().getAgency(),
                request.getDebitAccount().getNumber());

        if (account.getBalance().compareTo(request.getValue()) >= 0) {
            var newBalance = account.getBalance().subtract(request.getValue());
            account.setBalance(newBalance);

            try {
                accountService.save(account);

                var transaction = transactionMapper.toTransaction(account, null,
                        WITHDRAW, request.getValue());

                transactionRepository.save(transaction);
            } catch (Exception e) {
                throw new SaveEntityException(TRANSACTION_SAVE_ERROR);
            }

            var response = TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(account))
                    .newBalance(account.getBalance())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            throw new InsufficientBalanceException(TRANSACTION_BUSINESS_BALANCE_ERROR);
        }
    }

    public ResponseEntity<TransactionResponse> transfer(TransferTransactionRequest request) {
        var debitAccount = accountService.findByAgencyAndNumber(request.getDebitAccount().getAgency(),
                request.getDebitAccount().getNumber());
        var creditAccount = accountService.findByAgencyAndNumber(request.getCreditAccount().getAgency(),
                request.getCreditAccount().getNumber());

        if (debitAccount.getBalance().compareTo(request.getValue()) >= 0) {
            var newDebitAccountBalance = debitAccount.getBalance().subtract(request.getValue());
            debitAccount.setBalance(newDebitAccountBalance);

            var newCreditAccountBalance = creditAccount.getBalance().add(request.getValue());
            creditAccount.setBalance(newCreditAccountBalance);

            try {
                accountService.save(debitAccount);
                accountService.save(creditAccount);

                var transaction = transactionMapper.toTransaction(debitAccount, creditAccount,
                        TRANSFER, request.getValue());

                transactionRepository.save(transaction);
            } catch (Exception e) {
                throw new SaveEntityException(TRANSACTION_SAVE_ERROR);
            }

            var response = TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(debitAccount))
                    .newBalance(debitAccount.getBalance())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            throw new InsufficientBalanceException(TRANSACTION_BUSINESS_BALANCE_ERROR);
        }
    }

    public List<TransactionEntity> findAllByAccountId(AccountEntity account) {
        var debits = transactionRepository.findByDebitAccount(account);
        var credits = transactionRepository.findByCreditAccount(account);
        debits.addAll(credits);

        return debits;
    }

}
