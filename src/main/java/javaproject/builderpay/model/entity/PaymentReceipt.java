package javaproject.builderpay.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_payment_receipt")
public class PaymentReceipt {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "code")
	private String code;
	
	@Column(name= "originalAmount")
	private double originalAmount;
	
	@Column(name= "fineAmount")
	private double fineAmount;
	
	@Column(name= "interestAmount")
	private double interestAmount;
	
	@Column(name= "totalAmount")
	private double totalAmount;
	
	@Column(name= "type")
	private String type;
	
	@Column(name= "recipientName")
	private String recipientName;
	
	@Column(name= "recipientDocument")
	private String recipientDocument;
	
	@Column(name= "paymentDate")
	private LocalDate paymentDate;
	
	@Column(name= "dueDate")
	private LocalDate duetDate;
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientDocument() {
		return recipientDocument;
	}

	public void setRecipientDocument(String recipientDocument) {
		this.recipientDocument = recipientDocument;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDate getDuetDate() {
		return duetDate;
	}

	public void setDuetDate(LocalDate duetDate) {
		this.duetDate = duetDate;
	}

}
