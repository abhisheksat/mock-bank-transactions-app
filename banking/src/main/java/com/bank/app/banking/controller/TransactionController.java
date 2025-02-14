package com.bank.app.banking.controller;

import com.bank.app.banking.entity.Transaction;
import com.bank.app.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // CREATE a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    // READ a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // READ all transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // UPDATE a transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        Optional<Transaction> updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
        return updatedTransaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // ADVANCED FILTERING with pagination & sorting
    @GetMapping("/filter")
    public ResponseEntity<Page<Transaction>> getFilteredTransactions(
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) String referenceNumber,
            @RequestParam(required = false) String paymentId,
            @RequestParam(required = false) Transaction.FundingMethod fundingMethod,
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String clientId,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) LocalDateTime enteredFrom,
            @RequestParam(required = false) LocalDateTime enteredTo,
            @RequestParam(required = false) LocalDateTime lastUpdatedFrom,
            @RequestParam(required = false) LocalDateTime lastUpdatedTo,
            @RequestParam(required = false) LocalDateTime settlementDate,
            @RequestParam(required = false) String bankClearanceId,
            @RequestParam(required = false) String uniqueReference,
            @RequestParam(required = false) Transaction.Status status,
            @RequestParam(required = false) Transaction.PaymentType paymentType,
            @RequestParam(required = false) Transaction.AccountingEntry accountingEntry,
            @RequestParam(required = false) Transaction.Origin origin,
            @RequestParam(required = false) Transaction.BankType bankType,
            Pageable pageable) {

        Page<Transaction> transactions = transactionService.getFilteredTransactions(
                transactionId, referenceNumber, paymentId, fundingMethod, accountId, clientId, minAmount, maxAmount,
                enteredFrom, enteredTo, lastUpdatedFrom, lastUpdatedTo, settlementDate, bankClearanceId, uniqueReference,
                status, paymentType, accountingEntry, origin, bankType, pageable);
        return ResponseEntity.ok(transactions);
    }
}
