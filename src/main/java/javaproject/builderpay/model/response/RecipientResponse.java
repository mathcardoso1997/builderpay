package javaproject.builderpay.model.response;

public class RecipientResponse {

	private String code;
	
	private String dueDate;
	
	private double amount;
	
	private String recipientName;
	
	private String recipientDocument;
	
	private String type;

	public String getCode() {
		return code;
	}

	public String getDueDate() {
		return dueDate;
	}

	public double getAmount() {
		return amount;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public String getRecipientDocument() {
		return recipientDocument;
	}


	public String getType() {
		return type;
	}
	
}
