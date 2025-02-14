package com.bank.app.banking.service;

import com.bank.app.banking.entity.Transaction;
import com.bank.app.banking.repository.TransactionRepository;
import com.bank.app.banking.specification.TransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // CREATE: Save a new transaction
    public Transaction createTransaction(Transaction transaction) {
        transaction.setEnteredAt(LocalDateTime.now());
        transaction.setLastUpdated(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    // READ: Get a transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // READ: Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // UPDATE: Update an existing transaction
    public Optional<Transaction> updateTransaction(Long id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setReferenceNumber(transactionDetails.getReferenceNumber());
            transaction.setPaymentId(transactionDetails.getPaymentId());
            transaction.setFundingMethod(transactionDetails.getFundingMethod());
            transaction.setAccountId(transactionDetails.getAccountId());
            transaction.setClientId(transactionDetails.getClientId());
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setSettlementDate(transactionDetails.getSettlementDate());
            transaction.setBankClearanceId(transactionDetails.getBankClearanceId());
            transaction.setUniqueReference(transactionDetails.getUniqueReference());
            transaction.setStatus(transactionDetails.getStatus());
            transaction.setPaymentType(transactionDetails.getPaymentType());
            transaction.setAccountingEntry(transactionDetails.getAccountingEntry());
            transaction.setOrigin(transactionDetails.getOrigin());
            transaction.setBankType(transactionDetails.getBankType());
            transaction.setLastUpdated(LocalDateTime.now());
            return transactionRepository.save(transaction);
        });
    }

    // DELETE: Delete a transaction
    public boolean deleteTransaction(Long id) {
        return transactionRepository.findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return true;
        }).orElse(false);
    }

    // ADVANCED FILTERING: Get transactions with filtering, pagination, and sorting
    public Page<Transaction> getFilteredTransactions(
            String transactionId, String referenceNumber, String paymentId, Transaction.FundingMethod fundingMethod,
            String accountId, String clientId, Double minAmount, Double maxAmount,
            LocalDateTime enteredFrom, LocalDateTime enteredTo, LocalDateTime lastUpdatedFrom, LocalDateTime lastUpdatedTo,
            LocalDateTime settlementDate, String bankClearanceId, String uniqueReference,
            Transaction.Status status, Transaction.PaymentType paymentType, Transaction.AccountingEntry accountingEntry,
            Transaction.Origin origin, Transaction.BankType bankType, Pageable pageable) {

        Specification<Transaction> spec = TransactionSpecification.filterTransactions(
                transactionId, referenceNumber, paymentId, fundingMethod, accountId, clientId, minAmount, maxAmount,
                enteredFrom, enteredTo, lastUpdatedFrom, lastUpdatedTo, settlementDate, bankClearanceId, uniqueReference,
                status, paymentType, accountingEntry, origin, bankType);

        return transactionRepository.findAll(spec, pageable);
    }
}
