package com.bank.app.banking.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String transactionId;  // Unique transaction identifier

    @Column(unique = true, nullable = false)
    private String referenceNumber;  // External reference number

    @Column(unique = true, nullable = true)
    private String paymentId;  // Payment system identifier

    @Enumerated(EnumType.STRING)
    private FundingMethod fundingMethod;  // ENUM: Bank Transfer, Credit Card, Debit Card, Cash, Other

    private String accountId;  // Unique Account Identifier

    private String clientId;  // Unique Client Identifier

    private Double amount;  // Transaction amount

    private LocalDateTime transactionDate;  // Timestamp of transaction

    private LocalDateTime settlementDate;  // Settlement date if applicable

    private String bankClearanceId;  // Clearance reference from bank

    private String uniqueReference;  // Unique identifier for reconciliation

    @Enumerated(EnumType.STRING)
    private Status status;  // ENUM: 'Complete', 'Pending', 'Rejected', etc.

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;  // ENUM: 'ACH', 'Wire'

    @Enumerated(EnumType.STRING)
    private AccountingEntry accountingEntry;  // ENUM: 'Credit', 'Debit'

    @Enumerated(EnumType.STRING)
    private Origin origin;  // ENUM: 'Internally Initiated', 'Externally Initiated'

    @Enumerated(EnumType.STRING)
    private BankType bankType;  // ENUM: 'Domestic', 'International'

    private LocalDateTime enteredAt;  // When the transaction was recorded

    private LocalDateTime lastUpdated;  // Last update timestamp

    public enum Status {
        COMPLETE, PENDING, REJECTED, RECEIVED, SENT_TO_BANK, RETURNED_FROM_BANK, CANCELLED
    }

    public enum PaymentType {
        ACH, WIRE
    }

    public enum AccountingEntry {
        CREDIT, DEBIT
    }

    public enum Origin {
        INTERNALLY_INITIATED, EXTERNALLY_INITIATED
    }

    public enum BankType {
        DOMESTIC, INTERNATIONAL
    }

    public enum FundingMethod {
        BANK_TRANSFER, CREDIT_CARD, DEBIT_CARD, CASH, OTHER
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public FundingMethod getFundingMethod() {
		return fundingMethod;
	}

	public void setFundingMethod(FundingMethod fundingMethod) {
		this.fundingMethod = fundingMethod;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public LocalDateTime getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDateTime settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getBankClearanceId() {
		return bankClearanceId;
	}

	public void setBankClearanceId(String bankClearanceId) {
		this.bankClearanceId = bankClearanceId;
	}

	public String getUniqueReference() {
		return uniqueReference;
	}

	public void setUniqueReference(String uniqueReference) {
		this.uniqueReference = uniqueReference;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public AccountingEntry getAccountingEntry() {
		return accountingEntry;
	}

	public void setAccountingEntry(AccountingEntry accountingEntry) {
		this.accountingEntry = accountingEntry;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public BankType getBankType() {
		return bankType;
	}

	public void setBankType(BankType bankType) {
		this.bankType = bankType;
	}

	public LocalDateTime getEnteredAt() {
		return enteredAt;
	}

	public void setEnteredAt(LocalDateTime enteredAt) {
		this.enteredAt = enteredAt;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Transaction() {
		super();
	}

	public Transaction(Long id, String transactionId, String referenceNumber, String paymentId,
			FundingMethod fundingMethod, String accountId, String clientId, Double amount,
			LocalDateTime transactionDate, LocalDateTime settlementDate, String bankClearanceId, String uniqueReference,
			Status status, PaymentType paymentType, AccountingEntry accountingEntry, Origin origin, BankType bankType,
			LocalDateTime enteredAt, LocalDateTime lastUpdated) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.referenceNumber = referenceNumber;
		this.paymentId = paymentId;
		this.fundingMethod = fundingMethod;
		this.accountId = accountId;
		this.clientId = clientId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.settlementDate = settlementDate;
		this.bankClearanceId = bankClearanceId;
		this.uniqueReference = uniqueReference;
		this.status = status;
		this.paymentType = paymentType;
		this.accountingEntry = accountingEntry;
		this.origin = origin;
		this.bankType = bankType;
		this.enteredAt = enteredAt;
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountingEntry, amount, bankClearanceId, bankType, clientId, enteredAt,
				fundingMethod, id, lastUpdated, origin, paymentId, paymentType, referenceNumber, settlementDate, status,
				transactionDate, transactionId, uniqueReference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(accountId, other.accountId) && accountingEntry == other.accountingEntry
				&& Objects.equals(amount, other.amount) && Objects.equals(bankClearanceId, other.bankClearanceId)
				&& bankType == other.bankType && Objects.equals(clientId, other.clientId)
				&& Objects.equals(enteredAt, other.enteredAt) && fundingMethod == other.fundingMethod
				&& Objects.equals(id, other.id) && Objects.equals(lastUpdated, other.lastUpdated)
				&& origin == other.origin && Objects.equals(paymentId, other.paymentId)
				&& paymentType == other.paymentType && Objects.equals(referenceNumber, other.referenceNumber)
				&& Objects.equals(settlementDate, other.settlementDate) && status == other.status
				&& Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(transactionId, other.transactionId)
				&& Objects.equals(uniqueReference, other.uniqueReference);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [id=");
		builder.append(id);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", referenceNumber=");
		builder.append(referenceNumber);
		builder.append(", paymentId=");
		builder.append(paymentId);
		builder.append(", fundingMethod=");
		builder.append(fundingMethod);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionDate=");
		builder.append(transactionDate);
		builder.append(", settlementDate=");
		builder.append(settlementDate);
		builder.append(", bankClearanceId=");
		builder.append(bankClearanceId);
		builder.append(", uniqueReference=");
		builder.append(uniqueReference);
		builder.append(", status=");
		builder.append(status);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", accountingEntry=");
		builder.append(accountingEntry);
		builder.append(", origin=");
		builder.append(origin);
		builder.append(", bankType=");
		builder.append(bankType);
		builder.append(", enteredAt=");
		builder.append(enteredAt);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}
	
}