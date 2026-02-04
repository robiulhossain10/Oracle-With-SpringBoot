package com.oracle.oraclewithspringboot.service;

import com.oracle.oraclewithspringboot.dtos.TransactionRequestDTO;
import com.oracle.oraclewithspringboot.entity.Account;
import com.oracle.oraclewithspringboot.entity.BankTransaction;
import com.oracle.oraclewithspringboot.repository.AccountRepository;
import com.oracle.oraclewithspringboot.repository.TransactionRepository;
import com.oracle.oraclewithspringboot.repository.TransactionView;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankingService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // ------------------------------
    // Account CRUD
    // ------------------------------

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Account getAccount(String accNo){
        return accountRepository.findById(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account updateAccount(Account updatedAccount){
        Account existing = accountRepository.findById(updatedAccount.getAccNo())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existing.setBalance(updatedAccount.getBalance());
        return accountRepository.save(existing);
    }

    public void deleteAccount(String accNo){
        accountRepository.deleteById(accNo);
    }

    // ------------------------------
    // Transaction CRUD
    // ------------------------------

    public List<TransactionView> getAllTransactions(){
        return transactionRepository.findAllProjectedBy();
    }

    public BankTransaction getTransaction(Long txnId){
        return transactionRepository.findById(txnId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteTransaction(Long txnId){
        transactionRepository.deleteById(txnId);
    }

    // ------------------------------
    // Real banking transfer
    // ------------------------------
    @Transactional
    public String transfer(TransactionRequestDTO dto){

        // Load accounts
        Account fromAcc = accountRepository
                .findById(dto.getFromAccount())
                .orElseThrow(() -> new RuntimeException("From Account Not Found"));

        Account toAcc = accountRepository
                .findById(dto.getToAccount())
                .orElseThrow(() -> new RuntimeException("To Account Not Found"));

        // Check balance
        if (fromAcc.getBalance() < dto.getAmount()){
            throw new RuntimeException("Insufficient Balance");
        }

        // Debit + Credit
        fromAcc.setBalance(fromAcc.getBalance() - dto.getAmount());
        toAcc.setBalance(toAcc.getBalance() + dto.getAmount());

        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);

        // Record transaction
        BankTransaction txn = new BankTransaction();
        txn.setTxnId("TXN-" + System.currentTimeMillis()); // or sequence
        txn.setFromAccount(dto.getFromAccount());
        txn.setToAccount(dto.getToAccount());
        txn.setAmount(dto.getAmount());
        txn.setTxnDate(LocalDateTime.now());
        txn.setStatus("SUCCESS");

        transactionRepository.save(txn);

        return txn.getTxnId();
    }
}
