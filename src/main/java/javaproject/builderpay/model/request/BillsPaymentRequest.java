package javaproject.builderpay.model.request;

public class BillsPaymentRequest  {

	private String barCode;
	
	private String paymentDate;
	
	public String getBarCode() {
		return barCode;
	}
	
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	public String getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}
