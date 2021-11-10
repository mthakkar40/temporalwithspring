package com.example.service;

import com.example.domain.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional                                     ❶
    public void transferMoney(long idSender,
                              long idReceiver,
                              BigDecimal amount) {
        Account sender =                                 ❷
        accountRepository.findAccountById(idSender);   ❷
        Account receiver =                               ❷
        accountRepository.findAccountById(idReceiver); ❷

        BigDecimal senderNewAmount =                     ❸
        sender.getAmount().subtract(amount);           ❸
        BigDecimal receiverNewAmount =                   ❹
        receiver.getAmount().add(amount);              ❹

        accountRepository.changeAmount(idSender, senderNewAmount);

        accountRepository.changeAmount(idReceiver, receiverNewAmount);                                ❻
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}

