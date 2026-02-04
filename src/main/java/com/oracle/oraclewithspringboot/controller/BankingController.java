package com.oracle.oraclewithspringboot.controller;

import com.oracle.oraclewithspringboot.dtos.TransactionRequestDTO;
import com.oracle.oraclewithspringboot.entity.Account;
import com.oracle.oraclewithspringboot.entity.BankTransaction;
import com.oracle.oraclewithspringboot.repository.TransactionView;
import com.oracle.oraclewithspringboot.service.BankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banking")
@RequiredArgsConstructor
public class BankingController {

    private final BankingService bankingService;

    // ----------------------------
    // Account CRUD
    // ----------------------------

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        return ResponseEntity.ok(bankingService.createAccount(account));
    }

    @GetMapping("/accounts/{accNo}")
    public ResponseEntity<Account> getAccount(@PathVariable String accNo){
        return ResponseEntity.ok(bankingService.getAccount(accNo));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(bankingService.getAllAccounts());
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account){
        return ResponseEntity.ok(bankingService.updateAccount(account));
    }

    @DeleteMapping("/accounts/{accNo}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accNo){
        bankingService.deleteAccount(accNo);
        return ResponseEntity.ok("Account deleted successfully");
    }

    // ----------------------------
    // Transaction CRUD
    // ----------------------------

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, Object>> getAllTransactions() {
        List<TransactionView> transactions = bankingService.getAllTransactions();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Data Fetched Successfully");
        response.put("data", transactions);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/transactions/{txnId}")
    public ResponseEntity<BankTransaction> getTransaction(@PathVariable Long txnId){
        return ResponseEntity.ok(bankingService.getTransaction(txnId));
    }

    @DeleteMapping("/transactions/{txnId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long txnId){
        try {
            bankingService.deleteTransaction(txnId);
            return ResponseEntity.ok("Transaction deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("ssdsgdsgdsyd");
        }
    }

    // ----------------------------
    // Money Transfer
    // ----------------------------

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@Valid @RequestBody TransactionRequestDTO dto){
        String txnId = bankingService.transfer(dto);
        return ResponseEntity.ok("Transaction successful. TXN ID: " + txnId);
    }
}
