package com.bank.app.banking.specification;

import com.bank.app.banking.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> filterTransactions(
            String transactionId, String referenceNumber, String paymentId, Transaction.FundingMethod fundingMethod,
            String accountId, String clientId, Double minAmount, Double maxAmount,
            LocalDateTime enteredFrom, LocalDateTime enteredTo, LocalDateTime lastUpdatedFrom, LocalDateTime lastUpdatedTo,
            LocalDateTime settlementDate, String bankClearanceId, String uniqueReference,
            Transaction.Status status, Transaction.PaymentType paymentType, Transaction.AccountingEntry accountingEntry,
            Transaction.Origin origin, Transaction.BankType bankType) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (transactionId != null) {
                predicates.add(criteriaBuilder.equal(root.get("transactionId"), transactionId));
            }
            if (referenceNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("referenceNumber"), referenceNumber));
            }
            if (paymentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("paymentId"), paymentId));
            }
            if (fundingMethod != null) {
                predicates.add(criteriaBuilder.equal(root.get("fundingMethod"), fundingMethod));
            }
            if (accountId != null) {
                predicates.add(criteriaBuilder.equal(root.get("accountId"), accountId));
            }
            if (clientId != null) {
                predicates.add(criteriaBuilder.equal(root.get("clientId"), clientId));
            }
            if (minAmount != null && maxAmount != null) {
                predicates.add(criteriaBuilder.between(root.get("amount"), minAmount, maxAmount));
            }
            if (enteredFrom != null && enteredTo != null) {
                predicates.add(criteriaBuilder.between(root.get("enteredAt"), enteredFrom, enteredTo));
            }
            if (lastUpdatedFrom != null && lastUpdatedTo != null) {
                predicates.add(criteriaBuilder.between(root.get("lastUpdated"), lastUpdatedFrom, lastUpdatedTo));
            }
            if (settlementDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("settlementDate"), settlementDate));
            }
            if (bankClearanceId != null) {
                predicates.add(criteriaBuilder.equal(root.get("bankClearanceId"), bankClearanceId));
            }
            if (uniqueReference != null) {
                predicates.add(criteriaBuilder.equal(root.get("uniqueReference"), uniqueReference));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (paymentType != null) {
                predicates.add(criteriaBuilder.equal(root.get("paymentType"), paymentType));
            }
            if (accountingEntry != null) {
                predicates.add(criteriaBuilder.equal(root.get("accountingEntry"), accountingEntry));
            }
            if (origin != null) {
                predicates.add(criteriaBuilder.equal(root.get("origin"), origin));
            }
            if (bankType != null) {
                predicates.add(criteriaBuilder.equal(root.get("bankType"), bankType));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}