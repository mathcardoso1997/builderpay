package javaproject.builderpay.model.dto;

public class BillsPaymentDto {

	private double originalAmount;
	
	private double amount;
	
	private String dueDate;
	
	private String paymentDate;
	
	private double interestAmountCalculated;
	
	private double fineAmountCalculated;
	
	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getInterestAmountCalculated() {
		return interestAmountCalculated;
	}

	public void setInterestAmountCalculated(double interestAmountCalculated) {
		this.interestAmountCalculated = interestAmountCalculated;
	}

	public double getFineAmountCalculated() {
		return fineAmountCalculated;
	}

	public void setFineAmountCalculated(double fineAmountCalculated) {
		this.fineAmountCalculated = fineAmountCalculated;
	}
}
